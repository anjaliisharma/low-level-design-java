package com.amazonlocker.exception;

public class InvalidAccessCodeException extends RuntimeException {
    public InvalidAccessCodeException(String message) {
        super(message);
    }
}
