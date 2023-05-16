package com.agl.hiberus.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MissingHeaderInfoException extends RuntimeException {
    public MissingHeaderInfoException(String message) {
        super(message);
    }
}