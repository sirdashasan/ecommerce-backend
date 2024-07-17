package com.workintech.ecommerce.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(ApiException apiException){
        log.error("apiexception occured: "+ apiException);
        return new ResponseEntity<>(new ExceptionResponse(apiException.getMessage(),apiException.getHttpStatus().value(),
                LocalDateTime.now()),apiException.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleExcception(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        List<ObjectError> errorList = ex.getBindingResult().getAllErrors();
        errorList.forEach((error)-> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return  new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(Exception exception){
        log.error("apiexception occured: "+ exception);
        return new ResponseEntity<>(new ExceptionResponse(exception.getMessage(), HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }
}
