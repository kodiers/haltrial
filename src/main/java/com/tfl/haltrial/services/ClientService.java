package com.tfl.haltrial.services;

import com.tfl.haltrial.domain.dto.ClientDto;
import com.tfl.haltrial.domain.entity.ClientEntity;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface ClientService {

    ClientDto createClient(String login, String firstName, String lastName, String patronymic, LocalDate birthday);

    boolean clientExists(String login);

    List<ClientEntity> getClientsByLogins(Collection<String> logins);

}
