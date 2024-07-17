package com.tfl.haltrial.services;

import com.tfl.haltrial.domain.dto.ClientDto;
import com.tfl.haltrial.domain.entity.ClientEntity;
import com.tfl.haltrial.domain.mapper.ClientMapper;
import com.tfl.haltrial.repositories.ClientRepository;
import com.tfl.haltrial.services.exceptions.InvalidRequestDataException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Transactional
    @Override
    public ClientDto createClient(String login, String firstName, String lastName, String patronymic,
                                  LocalDate birthday) {
        if (clientRepository.existsByLogin(login)) {
            throw new InvalidRequestDataException("Login already exists");
        }
        ClientEntity client = ClientEntity
                .builder()
                .login(login)
                .firstName(firstName)
                .lastName(lastName)
                .patronymic(patronymic)
                .birthday(birthday)
                .build();
        return clientMapper.entityToDto(clientRepository.save(client));
    }

    @Transactional(readOnly = true)
    @Override
    public boolean clientExists(String login) {
        return clientRepository.existsByLogin(login);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ClientEntity> getClientsByLogins(Collection<String> logins) {
        return clientRepository.findAllByLoginIn(logins);
    }
}
