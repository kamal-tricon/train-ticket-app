package com.example.demo.common.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.common.controllers.InvalidInputException;
import com.example.demo.common.controllers.InvalidInputsException;
import com.example.demo.common.models.CustomErrorResponse;
import com.example.demo.common.models.CustomErrorsResponse;

@Service
public class ErrorConverterService {

    public List<Map<String, String>> getAllFieldsErrorsAndConvertToArray(BindingResult bindingResult) {
        List<Map<String, String>> errorList = new ArrayList<>();
        bindingResult.getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            Map<String, String> err = new HashMap<>();
            err.put("fieldName", fieldName);
            err.put("fieldError", error.getDefaultMessage());
            errorList.add(err);
        });
        return errorList;
    }

    public List<Map<String, String>> getAllFieldsErrorsAndConvertToArray(BindingResult bindingResult, Boolean merge) {
        if (!merge) {
            return this.getAllFieldsErrorsAndConvertToArray(bindingResult);
        }
        List<Map<String, String>> errorList = new ArrayList<>();
        bindingResult.getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            Map<String, String> err = new HashMap<>();
            err.put("fieldName", fieldName);
            err.put("fieldError", error.getDefaultMessage());
            errorList.add(err);
        });
        return errorList;
    }

    public CustomErrorResponse convertToGlobalErrorResponse(InvalidInputException ex, WebRequest req) {
        CustomErrorResponse cer = new CustomErrorResponse();
        cer
            .setErrorCode(ex.getErrorCode())
            .setUri(req.getContextPath())
            .setHttpCode(HttpStatus.BAD_REQUEST)
            .setMessage(ex.getMessage());
        return cer;
    }

    public CustomErrorsResponse convertToGlobalErrorsResponse(InvalidInputsException ex, WebRequest req) {
        CustomErrorsResponse cer = new CustomErrorsResponse();
        cer
            .setErrorCode(ex.getErrorCode())
            .setUri(req.getContextPath())
            .setHttpCode(HttpStatus.BAD_REQUEST)
            .setErrors(ex.getErrors());
        return cer;
    }
}
