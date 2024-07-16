package com.tfl.haltrial.web.v1.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMessageRequestDto {

    @JsonProperty("to_client")
    @NotBlank
    private String client;

    @NotBlank
    private String body;


}
