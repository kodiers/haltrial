package com.tfl.haltrial.services;

import com.tfl.haltrial.domain.dto.ClientDto;

import java.time.LocalDate;

public interface ClientService {

    ClientDto createClient(String login, String firstName, String lastName, String patronymic, LocalDate birthday);

    boolean clientExists(String login);

}
