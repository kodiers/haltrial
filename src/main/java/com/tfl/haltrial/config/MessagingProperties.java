package com.tfl.haltrial.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "messaging.properties")
@Getter
@Setter
public class MessagingProperties {
    private String queueName;
}
