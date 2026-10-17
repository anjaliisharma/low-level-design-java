package com.amazonlocker.exception;

public class NoLockerAvailableException extends RuntimeException {
    public NoLockerAvailableException(String message) {
        super(message);
    }
}
