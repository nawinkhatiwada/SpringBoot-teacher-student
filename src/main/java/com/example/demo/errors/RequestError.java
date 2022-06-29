package com.example.demo.errors;

public class RequestError extends IllegalStateException {
    private int statusCode;
    private String statusMessage;

    public RequestError(int code, String statusMessage) {
        this.statusCode = code;
        this.statusMessage = statusMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int code) {
        this.statusCode = code;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String message) {
        this.statusMessage = message;
    }
}
