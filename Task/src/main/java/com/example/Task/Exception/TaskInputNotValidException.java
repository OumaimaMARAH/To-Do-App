package com.example.Task.Exception;

public class TaskInputNotValidException extends RuntimeException{
    public TaskInputNotValidException(String message) {
        super(message);
    }
}
