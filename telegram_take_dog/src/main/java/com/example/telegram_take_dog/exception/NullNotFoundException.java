package com.example.telegram_take_dog.exception;

public class NullNotFoundException extends RuntimeException{

    public NullNotFoundException() {
    }

    public NullNotFoundException(String message) {
        super(message);
    }

}
