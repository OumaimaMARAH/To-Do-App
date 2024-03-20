package com.example.User.Exception;

public class UserInputNotValidException extends RuntimeException{
    public UserInputNotValidException(String message) {
        super(message);
    }
}
