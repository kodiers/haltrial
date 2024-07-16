package com.tfl.haltrial.web.v1.controllers;

import com.tfl.haltrial.services.MessageService;
import com.tfl.haltrial.web.v1.dto.request.CreateMessageRequestDto;
import com.tfl.haltrial.web.v1.dto.response.MessageQueuedData;
import com.tfl.haltrial.web.v1.dto.response.ResponseDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/message")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseDto<MessageQueuedData> sendMessage(@RequestBody @Valid CreateMessageRequestDto messageRequestDto) {
        UUID eventId = messageService.sendMessage(messageRequestDto.getClient(), messageRequestDto.getBody());
        return new ResponseDto<>(new MessageQueuedData(eventId));
    }
}
