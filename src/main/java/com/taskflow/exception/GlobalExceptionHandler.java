package com.taskflow.exception;

import java.time.LocalDateTime;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.taskflow.dto.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // for the custom task not found error we created
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleTaskNotFoundException(TaskNotFoundException exception, HttpServletRequest request) {

        ApiErrorResponse apiError = ApiErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .errorType("Resource Not Found")
                .errorMessage(exception.getMessage())
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    // here we are handling all the validation errors
    // HttpServletRequest this contains metadata about the HTTP request
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationExceptions(MethodArgumentNotValidException exception, HttpServletRequest request) {
        Map <String, String> errors = new HashMap<>();

        // getAllErrors() will give us all the errors releated to validation
        // getBindingResult() opens up the specific inspection report tracking the data-binding errors
        exception.getBindingResult().getAllErrors().forEach((error) -> {

            // we take the exact field name in the entity that caused the error
            String fieldName = ((FieldError) error).getField();

            String errorMessage = error.getDefaultMessage();

            // we put in the map the field name as a key and the value of it is the errorMessage
            errors.put(fieldName, errorMessage);
        });

        // we build the API error
        ApiErrorResponse apiError = ApiErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .errorType("Validation Failed")
                .errorMessage("The payload sent contains invalid fields.")
                .path(request.getRequestURI())
                .validationErrors(errors)
                .build();

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    // this is a global exception handler if no exceptions above worked this will handle the exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleAllExceptions(Exception exception, HttpServletRequest request) {
        ApiErrorResponse apiError = ApiErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .errorType("Internal Server Error")
                .errorMessage("An unexpected error occurred on our side. Please try again later.")
                .path(request.getRequestURI())
                .build();

        // to show the entire error log to understand what is the error and debug it
        exception.printStackTrace();

        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
