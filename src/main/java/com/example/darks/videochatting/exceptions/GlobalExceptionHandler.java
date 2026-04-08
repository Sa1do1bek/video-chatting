package com.example.darks.videochatting.exceptions;

import com.example.backend.enums.ErrorType;
import com.example.backend.responses.ApiResponse;
import com.example.backend.services.error.ErrorLoggingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ErrorLoggingService errorLoggingService;

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse> handleBadRequest(
            ResourceNotFoundException ex,
            HttpServletRequest request
    ) {

        errorLoggingService.logError(
                request.getRequestURI(),
                request.getMethod(),
                400,
                ex.getMessage(),
                ErrorType.BAD_REQUEST,
                ""
        );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse(ex.getMessage(), null));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleNotFound(
            ResourceNotFoundException ex,
            HttpServletRequest request
    ) {

        errorLoggingService.logError(
                request.getRequestURI(),
                request.getMethod(),
                404,
                ex.getMessage(),
                ErrorType.NOT_FOUND,
                ""
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse(ex.getMessage(), null));
    }

    @ExceptionHandler(AsyncRequestTimeoutException.class)
    public ResponseEntity<ApiResponse> handleTimeout(
            AsyncRequestTimeoutException ex,
            HttpServletRequest request
    ) {
        errorLoggingService.logError(
                request.getRequestURI(),
                request.getMethod(),
                504,
                ex.getMessage(),
                ErrorType.TIMEOUT,
                ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT)
                .body(new ApiResponse(ex.getMessage(), null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(
            Exception ex,
            HttpServletRequest request
    ) {

        errorLoggingService.logError(
                request.getRequestURI(),
                request.getMethod(),
                500,
                ex.getMessage(),
                ErrorType.INTERNAL_SERVER_ERROR,
                Arrays.toString(ex.getStackTrace())
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse(ex.getMessage(), null));
    }
}