package com.jwt.jwt.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private Boolean status;
    private String errorType;
    private LocalDateTime timestamp;
    private String message;
    private T data;



    // Static success response builder
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(
            true,
            null,
            LocalDateTime.now(),
            "Success",
            data
        );
    }

    // Optional: static error response builder
    public static <T> ApiResponse<T> error(String errorType, String message) {
        return new ApiResponse<>(
            false,
            errorType,
            LocalDateTime.now(),
            message,
            null
        );
    }
}
