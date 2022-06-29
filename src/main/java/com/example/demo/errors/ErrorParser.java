package com.example.demo.errors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;

public class ErrorParser {
    public static HttpStatus getErrorStatusCode(Exception e) {
        if (e instanceof RequestError) {
            return HttpStatus.valueOf(((RequestError) e).getStatusCode());
        } else {
            return HttpStatus.MULTI_STATUS;
        }
    }

    public static String getErrorMessage(Exception e) {
        if (e instanceof RequestError) {
            return ((RequestError) e).getStatusMessage();
        } else if (e instanceof DataIntegrityViolationException) {
            return e.getCause().getCause().getMessage();
        } else {
            return e.getMessage();
        }
    }
}
