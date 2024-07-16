package com.tfl.haltrial.messaging.dto;

import java.io.Serializable;
import java.util.UUID;

public record SendMessageEvent(String toLogin, String body, UUID messageUuid) implements Serializable {
}
