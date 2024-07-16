package com.tfl.haltrial.web.v1.controllers;

import com.tfl.haltrial.domain.dto.ClientDto;
import com.tfl.haltrial.services.ClientService;
import com.tfl.haltrial.web.v1.converters.ClientDtoToResponseData;
import com.tfl.haltrial.web.v1.dto.request.CreateClientRequestDto;
import com.tfl.haltrial.web.v1.dto.response.ClientData;
import com.tfl.haltrial.web.v1.dto.response.ResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/client")
public class ClientController {

    private final ClientService clientService;
    private final ClientDtoToResponseData clientDtoToResponseData;

    public ClientController(ClientService clientService, ClientDtoToResponseData clientDtoToResponseData) {
        this.clientService = clientService;
        this.clientDtoToResponseData = clientDtoToResponseData;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto<ClientData> createClient(@RequestBody @Valid CreateClientRequestDto clientRequestDto) {
        ClientDto clientDto = clientService.createClient(clientRequestDto.getLogin(), clientRequestDto.getFirstName(),
                clientRequestDto.getLastName(), clientRequestDto.getPatronymic(), clientRequestDto.getBirthday());
        ClientData clientData = clientDtoToResponseData.convert(clientDto);
        return new ResponseDto<>(clientData);
    }
}
