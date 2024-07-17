package com.tfl.haltrial.services;

import com.tfl.haltrial.domain.dto.MessageDto;
import com.tfl.haltrial.domain.entity.ClientEntity;
import com.tfl.haltrial.domain.entity.MessageEntity;
import com.tfl.haltrial.domain.mapper.MessageMapper;
import com.tfl.haltrial.messaging.HazelcastProducer;
import com.tfl.haltrial.messaging.dto.SendMessageEvent;
import com.tfl.haltrial.messaging.exceptions.QueueFullException;
import com.tfl.haltrial.repositories.MessageRepository;
import com.tfl.haltrial.services.exceptions.CannotSendMessageException;
import com.tfl.haltrial.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    private final ClientService clientService;
    private final HazelcastProducer producer;
    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    public MessageServiceImpl(ClientService clientService, HazelcastProducer producer,
                              MessageRepository messageRepository, MessageMapper messageMapper) {
        this.clientService = clientService;
        this.producer = producer;
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
    }


    @Override
    public UUID sendMessage(String toClient, String body) {
        if (!clientService.clientExists(toClient)) {
            throw new ObjectNotFoundException("Client not found");
        }
        try {
            return producer.putMessage(toClient, body);
        } catch (QueueFullException queueFullException) {
            throw new CannotSendMessageException("Cannot send message", queueFullException);
        }
    }

    @Transactional
    @Override
    public List<MessageDto> createMessagesFromEvents(List<SendMessageEvent> events) {
        List<String> logins = events.stream().map(SendMessageEvent::toLogin).toList();
        Map<String, ClientEntity> clientEntities = clientService.getClientsByLogins(logins)
                .stream()
                .collect(Collectors.toMap(ClientEntity::getLogin, Function.identity()));
        List<MessageEntity> entities = events.stream()
                .filter(e -> clientEntities.containsKey(e.toLogin()))
                .map(e -> {
                    ClientEntity client = clientEntities.get(e.toLogin());
                    return MessageEntity.builder()
                            .client(client)
                            .body(e.body())
                            .build();
                }).toList();
        return messageRepository.saveAll(entities).stream().map(messageMapper::entityToDto).toList();
    }
}
