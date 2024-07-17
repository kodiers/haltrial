package com.tfl.haltrial.config;

import com.tfl.haltrial.domain.dto.MessageDto;
import com.tfl.haltrial.messaging.HazelcastConsumer;
import com.tfl.haltrial.messaging.dto.SendMessageEvent;
import com.tfl.haltrial.services.MessageService;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.LockAssert;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Slf4j
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "10s")
@Configuration
public class ScheduleTasksConfiguration {

    private final HazelcastConsumer consumer;
    private final MessageService messageService;

    public ScheduleTasksConfiguration(HazelcastConsumer consumer, MessageService messageService) {
        this.consumer = consumer;
        this.messageService = messageService;
    }

    @SchedulerLock(name = "createMessagesTask")
    @Scheduled(fixedRate = 5000)
    public void createMessages() {
        LockAssert.assertLocked();
        List<SendMessageEvent> events = consumer.consumeMessages();
        if (events.isEmpty()) {
            log.info("No events in queue");
            return;
        }
        List<MessageDto> messageDtos = messageService.createMessagesFromEvents(events);
        if (events.size() != messageDtos.size()) {
            log.warn("Some messages were not created for {} events. Created messages size {}", events.size(),
                    messageDtos.size());
            return;
        }
        log.info("Created {} messages", messageDtos.size());
    }
}
