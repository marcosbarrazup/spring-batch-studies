package com.example.springbatch.domain;


import com.example.springbatch.csv.CashbackCSV;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "cashback")
public class Cashback {
    private static final CurrencyUnit brlCurrency = Monetary.getCurrency("BRL");

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Customer customer;

    private Double transactionValue;

    private Double value;

    @ManyToOne
    private Campaign campaign;

    private LocalDateTime createdAt;


    public Cashback(CashbackCSV cashbackCSV) {

        this.setCustomer(new Customer());
        this.getCustomer().setCpf(cashbackCSV.getCpf());
        this.setTransactionValue(Double.parseDouble(cashbackCSV.getTransactionValue()));
    }
}
