package com.tfl.haltrial.web.v1.dto.response;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorCode {
    UNKNOWN_ERROR(100),
    UNEXPECTED_RESULT(101),
    INVALID_OBJECT_STATE(120),
    OBJECT_NOT_FOUND(200),
    INVALID_REQUEST_DATA(300);

    private final int code;

    ErrorCode(int code) {
        this.code = code;
    }

    @JsonValue
    public int getCode() {
        return code;
    }

}
