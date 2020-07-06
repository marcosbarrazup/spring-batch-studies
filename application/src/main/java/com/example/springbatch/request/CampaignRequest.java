package com.example.springbatch.request;

import com.example.springbatch.domain.CampaignType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.lang.Double;

import java.time.LocalDate;

@Data
public class CampaignRequest {

    private Double minimumTransactionValue;
    private Double maximumTransactionValue;
    private Double cashbackPercentage;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private String type;
    private Double maximumValueAllowedByCustomer;
    private Long cashbacksByCustomer;
    private Boolean hasValueLimit;
    private Double maximumValueAllowedByCampaign;
    private Double balance;
}
