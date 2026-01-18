package com.lastminute.exception;

import com.lastminute.payload.APIResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<APIResponse<String>> handleRuntime(RuntimeException ex) {

        APIResponse<String> response = new APIResponse<>();
        response.setMessage("Registration Failed");
        response.setStatus(400);
        response.setData(ex.getMessage());

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<APIResponse<String>> handleDBException() {

        APIResponse<String> response = new APIResponse<>();
        response.setMessage("Registration Failed");
        response.setStatus(409);
        response.setData("Email or Username already exists");

        return ResponseEntity.status(409).body(response);
    }
}
