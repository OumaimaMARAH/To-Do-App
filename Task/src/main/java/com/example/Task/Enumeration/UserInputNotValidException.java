package com.example.Task.Enumeration;

public class UserInputNotValidException extends RuntimeException{
    public UserInputNotValidException(String message) {
        super(message);
    }
}
