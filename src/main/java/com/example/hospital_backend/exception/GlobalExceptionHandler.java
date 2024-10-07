package com.example.hospital_backend.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Logger 인스턴스 생성
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 특정 예외 처리 (ResourceNotFoundException)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        // 예외 정보 로깅
        logger.error("Resource not found: {}", ex.getMessage());

        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    // 특정 예외 처리 (DuplicateFavoriteException)
    @ExceptionHandler(DuplicateFavoriteException.class)
    public ResponseEntity<ErrorDetails> handleDuplicateFavoriteException(DuplicateFavoriteException ex, WebRequest request) {
        logger.error("Duplicate favorite: {}", ex.getMessage());

        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }

    // 특정 예외 처리 (DuplicateHospitalException)
    @ExceptionHandler(DuplicateHospitalException.class)
    public ResponseEntity<ErrorDetails> handleDuplicateHospitalException(DuplicateHospitalException ex, WebRequest request) {
        logger.error("Duplicate hospital: {}", ex.getMessage());

        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }

    // 특정 예외 처리 (InvalidReviewException)
    @ExceptionHandler(InvalidReviewException.class)
    public ResponseEntity<ErrorDetails> handleInvalidReviewException(InvalidReviewException ex, WebRequest request) {
        logger.error("Invalid review: {}", ex.getMessage());

        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    // 특정 예외 처리 (BadRequestException)
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDetails> handleBadRequestException(BadRequestException ex, WebRequest request) {
        logger.error("Bad request: {}", ex.getMessage());

        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    // 유효성 검사 예외 처리 (MethodArgumentNotValidException)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // 예외 정보 로깅
        logger.error("Validation failed: {}", ex.getMessage());

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // 일반 예외 처리 (Exception)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception ex, WebRequest request) {
        // 예외 정보 로깅 (스택 트레이스 포함)
        logger.error("An unexpected error occurred: {}", ex.getMessage(), ex);

        ErrorDetails errorDetails = new ErrorDetails(new Date(), "An unexpected error occurred. Please try again later.", request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
