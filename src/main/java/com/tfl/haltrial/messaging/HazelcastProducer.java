package com.tfl.haltrial.messaging;

import com.hazelcast.collection.IQueue;
import com.hazelcast.core.HazelcastInstance;
import com.tfl.haltrial.config.MessagingProperties;
import com.tfl.haltrial.messaging.dto.SendMessageEvent;
import com.tfl.haltrial.messaging.exceptions.QueueFullException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class HazelcastProducer {

    private final HazelcastInstance hazelcastInstance;
    private final String queueName;

    public HazelcastProducer(HazelcastInstance hazelcastInstance, MessagingProperties properties) {
        this.hazelcastInstance = hazelcastInstance;
        this.queueName = properties.getQueueName();
    }

    public UUID putMessage(String toClient, String body) {
        log.info("Try to put message to client {}", toClient);
        SendMessageEvent sendMessageEvent = new SendMessageEvent(toClient, body, UUID.randomUUID());
        IQueue<SendMessageEvent> messageQueue = hazelcastInstance.getQueue(queueName);
        boolean result =  messageQueue.offer(sendMessageEvent);
        if (!result) {
            throw new QueueFullException("Cannot put message to queue");
        }
        return sendMessageEvent.messageUuid();
    }
}
