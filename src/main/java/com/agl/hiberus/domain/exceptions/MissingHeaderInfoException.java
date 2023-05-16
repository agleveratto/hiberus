package com.agl.hiberus.domain.exceptions;

public class MissingHeaderInfoException extends RuntimeException {
    public MissingHeaderInfoException(String message) {
        super(message);
    }
}