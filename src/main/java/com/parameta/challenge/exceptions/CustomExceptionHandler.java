package com.parameta.challenge.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import java.time.LocalDateTime;


@ControllerAdvice
public class CustomExceptionHandler extends RuntimeException {


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<CustomErrorResponse> handleAllExceptions(Exception ex, WebRequest request) {
        CustomErrorResponse errorResponse = new CustomErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now().toString());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setError("Bad Request in Employee Parameta");
        errorResponse.setMessage("An error occurred: " + ex.getMessage());
        errorResponse.setPath(request.getDescription(false).substring(4));


        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}