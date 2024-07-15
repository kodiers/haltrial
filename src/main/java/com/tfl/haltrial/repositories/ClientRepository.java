package com.tfl.haltrial.repositories;

import com.tfl.haltrial.domain.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    boolean existsByLogin(String login);

}
