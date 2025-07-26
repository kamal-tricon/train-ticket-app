package com.example.demo.common.controllers;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.common.models.CustomErrorResponse;
import com.example.demo.common.models.CustomErrorsResponse;
import com.example.demo.common.services.ErrorConverterService;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

    @Autowired
    private ErrorConverterService errorConverterService;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<Map<String, String>> list = errorConverterService.getAllFieldsErrorsAndConvertToArray(ex.getBindingResult());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(list);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        // List<Map<String, String>> list = errorConverterService.getAllFieldsErrorsAndConvertToArray(ex.);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(value = InvalidInputException.class)
    public ResponseEntity<CustomErrorResponse> handleInvalidInput(InvalidInputException ex, WebRequest req) {
        CustomErrorResponse cer = this.errorConverterService.convertToGlobalErrorResponse(ex, req);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cer);
    }

    @ExceptionHandler(value = InvalidInputsException.class)
    public ResponseEntity<CustomErrorsResponse> handleInvalidInputs(InvalidInputsException ex, WebRequest req) {
        CustomErrorsResponse cer = this.errorConverterService.convertToGlobalErrorsResponse(ex, req);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cer);
    }

    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<CustomErrorResponse> handleDuplicateInput(SQLIntegrityConstraintViolationException ex, WebRequest req) {
        CustomErrorResponse cer = this.errorConverterService.convertToGlobalErrorResponse(ex, req);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cer);
    }

}
