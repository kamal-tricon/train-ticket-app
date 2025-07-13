package com.example.demo.common.models;

import java.time.Instant;
import java.util.Map;

import org.springframework.http.HttpStatus;

public class CustomErrorsResponse {
    private Map<String, String> errors;
    private String uri;
    private String errorCode;
    private HttpStatus httpCode;

    private String date = Instant.now().toString();

    public CustomErrorsResponse() {}

    public CustomErrorsResponse(Map<String, String> errors, String uri, String errorCode, HttpStatus httpCode) {
        this.errors = errors;
        this.uri = uri;
        this.errorCode = errorCode;
        this.httpCode = httpCode;
    }


    public CustomErrorsResponse setErrors(Map<String, String> errors) {
        this.errors = errors;
        return this;
    }

    public CustomErrorsResponse setUri(String uri) {
        this.uri = uri;
        return this;
    }

    public CustomErrorsResponse setHttpCode(HttpStatus httpCode) {
        this.httpCode = httpCode;
        return this;
    }

    public CustomErrorsResponse setErrorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public Map<String, String> getErrors() {
        return this.errors;
    }

    public String getUri() {
        return this.uri;
    }

    public HttpStatus getHttpCode() {
        return this.httpCode;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getDate() {
        return this.date;
    }
}
