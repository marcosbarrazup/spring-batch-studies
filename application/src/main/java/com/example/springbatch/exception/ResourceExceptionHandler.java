package com.example.springbatch.exception;

import com.example.springbatch.exceptions.ExistentAccountException;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> notFound(NotFoundException e, HttpServletRequest request) {

        StandardError err = new StandardError( HttpStatus.BAD_REQUEST.value(),  e.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
    @ExceptionHandler(ExistentAccountException.class)
    public ResponseEntity<StandardError> existentAccount(ExistentAccountException e, HttpServletRequest request) {

        StandardError err = new StandardError( HttpStatus.BAD_REQUEST.value(),  e.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> illegalArgument(IllegalArgumentException e, HttpServletRequest request) {

        StandardError err = new StandardError( HttpStatus.BAD_REQUEST.value(),  e.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);

    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandardError> illegalArgument(ConstraintViolationException e, HttpServletRequest request) {

        StandardError err = new StandardError( HttpStatus.BAD_REQUEST.value(), "Operation value must be higher than 1!", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> illegalArgument(MethodArgumentNotValidException e, HttpServletRequest request) {

        ValidationError err = new ValidationError( HttpStatus.BAD_REQUEST.value(),  "Invalid Argument!", LocalDateTime.now());

        for(FieldError x : e.getBindingResult().getFieldErrors()){
            err.addError(x.getField(), x.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

}