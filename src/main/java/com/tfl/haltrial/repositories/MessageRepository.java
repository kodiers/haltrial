package com.tfl.haltrial.repositories;

import com.tfl.haltrial.domain.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
}
