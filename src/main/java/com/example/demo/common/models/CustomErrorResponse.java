package com.example.demo.common.models;

import java.time.Instant;

import org.springframework.http.HttpStatus;

public class CustomErrorResponse {

    private String message;
    private String uri;
    private String errorCode;
    private HttpStatus httpCode;

    private String date = Instant.now().toString();

    public CustomErrorResponse() {}

    public CustomErrorResponse(String message, String uri, String errorCode, HttpStatus httpCode) {
        this.message = message;
        this.uri = uri;
        this.errorCode = errorCode;
        this.httpCode = httpCode;
    }


    public CustomErrorResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public CustomErrorResponse setUri(String uri) {
        this.uri = uri;
        return this;
    }

    public CustomErrorResponse setHttpCode(HttpStatus httpCode) {
        this.httpCode = httpCode;
        return this;
    }

    public CustomErrorResponse setErrorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public String getMessage() {
        return this.message;
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
