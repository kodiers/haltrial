package com.tfl.haltrial.domain.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Value
@Builder
public class ClientDto {

    Long id;

    @NotBlank
    String login;

    @NotBlank
    String firstName;

    @NotBlank
    String lastName;

    @NotBlank
    String patronymic;

    @NotNull
    LocalDate birthday;

    ZonedDateTime createdAt;

    ZonedDateTime updatedAt;
}
