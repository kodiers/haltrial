package com.tfl.haltrial.services;

import com.tfl.haltrial.domain.dto.MessageDto;
import com.tfl.haltrial.messaging.dto.SendMessageEvent;

import java.util.List;
import java.util.UUID;

public interface MessageService {

    UUID sendMessage(String toClient, String body);

    List<MessageDto> createMessagesFromEvents(List<SendMessageEvent> events);
}
