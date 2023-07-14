package com.example.person.exception;

public class NotFoundException extends RuntimeException {
    
    public NotFoundException(Long id) {
        super(String.format("Person with id %d not found.", id));
    }
}
