package com.example.User.Exception;

public class DataNotValidException extends RuntimeException{
    public DataNotValidException(String message) {
        super(message);
    }
}
