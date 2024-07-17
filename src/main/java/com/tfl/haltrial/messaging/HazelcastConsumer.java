package com.tfl.haltrial.messaging;

import com.hazelcast.collection.IQueue;
import com.hazelcast.core.HazelcastInstance;
import com.tfl.haltrial.config.MessagingProperties;
import com.tfl.haltrial.messaging.dto.SendMessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HazelcastConsumer {

    private static final Logger log = LoggerFactory.getLogger(HazelcastConsumer.class);
    private final HazelcastInstance hazelcastInstance;
    private final String queueName;

    public HazelcastConsumer(HazelcastInstance hazelcastInstance, MessagingProperties properties) {
        this.hazelcastInstance = hazelcastInstance;
        this.queueName = properties.getQueueName();
    }

    public List<SendMessageEvent> consumeMessages() {
        IQueue<SendMessageEvent> messageQueue = hazelcastInstance.getQueue(queueName);
        List<SendMessageEvent> eventList = new ArrayList<>();
        int result = messageQueue.drainTo(eventList);
        log.info("Consumed {} messages", result);
        return eventList;
    }
}
