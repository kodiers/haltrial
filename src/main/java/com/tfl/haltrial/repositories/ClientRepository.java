package com.tfl.haltrial.repositories;

import com.tfl.haltrial.domain.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    boolean existsByLogin(String login);

    List<ClientEntity> findAllByLoginIn(Collection<String> logins);
}
