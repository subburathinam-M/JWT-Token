package com.jwt.jwt.exception;

import com.jwt.jwt.response.ApiResponse;

import io.swagger.v3.oas.annotations.Hidden;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;

@ControllerAdvice
@Hidden
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<Object>> handleCustomException(CustomException ex) {
        ApiResponse<Object> response = new ApiResponse<>(
                false,
                "DuplicateError",
                LocalDateTime.now(),
                ex.getMessage(),
                null
        );
        return new ResponseEntity<>(response, HttpStatus.CONFLICT); // 409 Conflict
    }

    @ExceptionHandler(UnauthorizedException.class)
public ResponseEntity<ApiResponse<Object>> handleUnauthorized(UnauthorizedException ex) {
    ApiResponse<Object> response = new ApiResponse<>(
            false,
            "Unauthorized",
            LocalDateTime.now(),
            ex.getMessage(),
            null
    );
    return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
}


    // Optional: handle all other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleException(Exception ex) {
        ApiResponse<Object> response = new ApiResponse<>(
                false,
                "ServerError",
                LocalDateTime.now(),
                ex.getMessage(),
                null
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
