package com.tfl.haltrial.services;

import java.util.UUID;

public interface MessageService {

    UUID sendMessage(String toClient, String body);
}
