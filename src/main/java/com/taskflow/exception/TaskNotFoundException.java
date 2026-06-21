package com.taskflow.exception;

// custom exception if we didn't find the task we are looking for
public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long id) {
        super("Task with ID " + id + " was not found.");
    }
}