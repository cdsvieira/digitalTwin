package com.koerber.digitalTwin.exceptions;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String errorMessage){
        super(errorMessage);
    }
}
