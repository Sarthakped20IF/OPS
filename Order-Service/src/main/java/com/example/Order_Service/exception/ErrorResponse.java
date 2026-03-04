package com.example.Order_Service.exception;

import lombok.Builder;
import lombok.Data;

import java.security.Timestamp;
import java.time.LocalDateTime;

@Data
@Builder
public class ErrorResponse {
    private LocalDateTime timestamp;
    private String message;
    private String error;
    private Integer status;
}
