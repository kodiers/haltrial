package com.tfl.haltrial.services;

import com.tfl.haltrial.messaging.HazelcastProducer;
import com.tfl.haltrial.messaging.exceptions.QueueFullException;
import com.tfl.haltrial.services.exceptions.CannotSendMessageException;
import com.tfl.haltrial.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MessageServiceImpl implements MessageService {

    private final ClientService clientService;
    private final HazelcastProducer producer;

    public MessageServiceImpl(ClientService clientService, HazelcastProducer producer) {
        this.clientService = clientService;
        this.producer = producer;
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
}
