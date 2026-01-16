package com.example.jobportal.exception;

import com.example.jobportal.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<String>> handleRuntime(RuntimeException ex) {
        ApiResponse<String> resp = new ApiResponse<>(ex.getMessage(), null, 400, null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String>> handleValidation(MethodArgumentNotValidException ex) {
        String msg = ex.getBindingResult().getFieldErrors().stream().map(e -> e.getField() + ": " + e.getDefaultMessage()).collect(Collectors.joining(", "));
        ApiResponse<String> resp = new ApiResponse<>(msg, null, 400, null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
    }
}
