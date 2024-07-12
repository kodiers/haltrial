package com.tfl.haltrial.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;


import java.time.ZonedDateTime;

@Value
@Builder
public class MessageDto {

    Long id;

    @NotBlank
    String body;

    ZonedDateTime createdAt;

    @NotNull
    ClientDto client;
}
