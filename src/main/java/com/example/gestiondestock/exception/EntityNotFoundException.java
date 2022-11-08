package com.example.gestiondestock.exception;

import lombok.Getter;

import java.util.function.Supplier;

public class EntityNotFoundException extends RuntimeException {

    @Getter
    private ErrorCodes errorCodes;

    public EntityNotFoundException(String message){
        super(message);
    }

    public EntityNotFoundException(String message, Throwable throwable){
        super(message, throwable);
    }

    public EntityNotFoundException(String message, Throwable throwable, ErrorCodes errorCodes){
        super(message, throwable);
        this.errorCodes = errorCodes;
    }

    public EntityNotFoundException(String message, ErrorCodes errorCodes){
        super(message);
        this.errorCodes = errorCodes;
    }

}
