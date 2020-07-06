package com.example.springbatch.dto;

import com.example.springbatch.domain.Campaign;
import com.example.springbatch.domain.CampaignType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CampaignDTO {

    private Long id;

    private Double minimumTransactionAmount;
    private Double maximumTransactionAmount;
    private Double cashbackPercentage;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate startDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate endDate;
    private CampaignType type;
    private Double maximumValueAllowedByCustomer;
    private Long cashbacksByCustomer;
    private Boolean hasValueLimit;
    private Double maximumValueAllowedByCampaign;
    private Double balance;


    public CampaignDTO(Campaign campaign) {
        this.id = campaign.getId();
        this.minimumTransactionAmount = campaign.getMinimumTransactionValue();
        this.maximumTransactionAmount = campaign.getMaximumTransactionValue();
        this.cashbackPercentage = campaign.getCashbackPercentage();
        this.startDate = campaign.getStartDate();
        this.endDate = campaign.getEndDate();
        this.type = campaign.getType();
        this.maximumValueAllowedByCustomer = campaign.getMaximumValueAllowedByCustomer();
        this.cashbacksByCustomer = campaign.getCashbacksByCustomer();
        this.hasValueLimit = campaign.getHasValueLimit();
        this.maximumValueAllowedByCampaign = campaign.getMaximumValueAllowedByCampaign();
        this.balance = campaign.getBalance();
    }

}
