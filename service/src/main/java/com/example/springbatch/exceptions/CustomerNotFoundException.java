package com.example.springbatch.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CustomerNotFoundException(String msg){
        super(msg);
    }
    public CustomerNotFoundException(String msg, Throwable cause){
        super(msg, cause);
    }
}
