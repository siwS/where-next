package com.where_next.exceptions;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException() {

    }

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Object... args) {
        super(String.format(message, args));
    }
}