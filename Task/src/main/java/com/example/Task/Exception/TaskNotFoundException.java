package com.example.Task.Exception;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(String message) {

        super(message);
    }
}
