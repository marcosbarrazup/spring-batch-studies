package com.example.springbatch.exceptions;

public class ExistentAccountException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ExistentAccountException(String msg){
        super(msg);
    }
    public ExistentAccountException(String msg, Throwable cause){
        super(msg, cause);
    }
}
