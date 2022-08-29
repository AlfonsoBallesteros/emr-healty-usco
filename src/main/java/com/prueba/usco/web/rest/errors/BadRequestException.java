package com.prueba.usco.web.rest.errors;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BadRequestException extends RuntimeException {

    private String code;

    public BadRequestException(String code, String message){
        super(message);
        this.code = code;
    }
}
