package com.tfl.haltrial.web.v1.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseDto<T> {

    protected boolean success;
    protected T data;

    public ResponseDto(T data) {
        this.success = true;
        this.data = data;
    }

}
