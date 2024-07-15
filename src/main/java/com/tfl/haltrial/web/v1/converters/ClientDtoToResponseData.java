package com.tfl.haltrial.web.v1.converters;

import com.tfl.haltrial.domain.dto.ClientDto;
import com.tfl.haltrial.web.v1.dto.response.ClientData;
import org.mapstruct.Mapper;

@Mapper
public interface ClientDtoToResponseData {

    ClientData convert(ClientDto clientDto);
}
