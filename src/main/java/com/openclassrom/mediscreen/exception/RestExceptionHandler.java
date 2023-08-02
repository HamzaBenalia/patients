package com.openclassrom.mediscreen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(PatientNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Error handlePatientNotFoundException(PatientNotFoundException patientNotFoundException, WebRequest request) {
        return new Error(HttpStatus.NOT_FOUND, patientNotFoundException.getMessage());
    }
}
