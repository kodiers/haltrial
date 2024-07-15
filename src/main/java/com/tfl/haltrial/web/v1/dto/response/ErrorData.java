package com.tfl.haltrial.web.v1.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorData {

    private String message;

    @JsonProperty("error_code")
    private ErrorCode errorCode;

    private String error;


}
