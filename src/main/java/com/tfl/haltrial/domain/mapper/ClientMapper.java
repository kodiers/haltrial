package com.tfl.haltrial.domain.mapper;

import com.tfl.haltrial.domain.dto.ClientDto;
import com.tfl.haltrial.domain.entity.ClientEntity;
import org.mapstruct.Mapper;

@Mapper
public interface ClientMapper {

    ClientDto entityToDto(ClientEntity client);
}
