package com.tfl.haltrial.domain.mapper;

import com.tfl.haltrial.domain.dto.MessageDto;
import com.tfl.haltrial.domain.entity.MessageEntity;
import org.mapstruct.Mapper;

@Mapper(uses = ClientMapper.class)
public interface MessageMapper {

    MessageDto entityToDto(MessageEntity message);
}
