package com.yash.notifier.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handle(TaskNotFoundException exception) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("Message", exception.getMessage());
        hashMap.put("Status", 404);
        return hashMap;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleValidation(MethodArgumentNotValidException exception) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("status", 400);
        exception.getBindingResult().getFieldErrors().forEach(error ->
            hashMap.put(error.getField(), error.getDefaultMessage()));
        return hashMap;
    }
}
