package com.where_next.exceptions;

public class InvalidInputParametersException extends RuntimeException {

    public InvalidInputParametersException() {

    }

    public InvalidInputParametersException(String message) {
        super(message);
    }

    public InvalidInputParametersException(String message, Object... args) {
        super(String.format(message, args));
    }

}
