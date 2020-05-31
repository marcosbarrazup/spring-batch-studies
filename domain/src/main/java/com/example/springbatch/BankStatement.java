package com.example.springbatch;

import lombok.*;
import org.javamoney.moneta.Money;

import javax.persistence.*;
import java.time.Month;
import java.time.Year;
import java.util.List;

@Data
@Entity
public class BankStatement {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private Month month;
    private Year year;
    private Money moneyIn;
    private Money moneyOut;

    @OneToMany(mappedBy = "bankStatement")
    private List<Transaction> transactions;

    @ManyToOne
    private Customer customer;
}
