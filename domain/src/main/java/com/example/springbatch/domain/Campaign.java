package com.example.springbatch.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Campaign {
    private static final CurrencyUnit brlCurrency = Monetary.getCurrency("BRL");

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;

    private Double minimumTransactionValue;//
    private Double maximumTransactionValue;//
    private Double cashbackPercentage;
    private LocalDate startDate;//
    private LocalDate endDate;//
    private CampaignType type;
    private Double maximumValueAllowedByCustomer;
    private Long cashbacksByCustomer;//
    private Boolean hasValueLimit;
    private Double maximumValueAllowedByCampaign;
    private Double balance;

    @OneToMany(mappedBy = "campaign")
    @JsonIgnore
    private List<Cashback> cashbacks;

    public Campaign(
            Double minimumTransactionValue,
            Double maximumTransactionValue,
            Double cashbackPercentage,
            LocalDate startDate,
            LocalDate endDate,
            String type,
            Double maximumValueAllowedByCustomer,
            Long cashbacksByCustomer,
            Boolean hasValueLimit,
            Double maximumValueAllowedByCampaign
    ) {
       this.minimumTransactionValue= minimumTransactionValue;
       this.maximumTransactionValue= maximumTransactionValue;
       this.cashbackPercentage= cashbackPercentage;
       this.startDate= startDate;
       this.endDate= endDate;
       this.type= CampaignType.valueOf(type);
       this.maximumValueAllowedByCustomer= maximumValueAllowedByCustomer;
       this.cashbacksByCustomer= cashbacksByCustomer;
       this.hasValueLimit= hasValueLimit;
       this.maximumValueAllowedByCampaign= maximumValueAllowedByCampaign;
       this.balance= maximumValueAllowedByCampaign;
    }
}
