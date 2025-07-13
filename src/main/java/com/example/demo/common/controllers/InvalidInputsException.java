package com.example.demo.common.controllers;

import java.util.HashMap;
import java.util.Map;

public class InvalidInputsException extends RuntimeException {

    private Map<String, String> errors = new HashMap<>();
    private String errorCode;

    public InvalidInputsException() {}

    public InvalidInputsException(Map<String, String> errors, String errorCode) {
        this.errors = errors;
        this.errorCode = errorCode;
    }

    public Map<String, String> getErrors() {
        return this.errors;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

}
