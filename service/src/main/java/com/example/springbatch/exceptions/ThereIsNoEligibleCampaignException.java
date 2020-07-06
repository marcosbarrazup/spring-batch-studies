package com.example.springbatch.exceptions;

public class ThereIsNoEligibleCampaignException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ThereIsNoEligibleCampaignException(String msg){
        super(msg);
    }
    public ThereIsNoEligibleCampaignException(String msg, Throwable cause){
        super(msg, cause);
    }
}
