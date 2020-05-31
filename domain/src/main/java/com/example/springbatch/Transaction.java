package com.example.springbatch;

import lombok.Data;
import org.javamoney.moneta.Money;

import javax.persistence.*;

@Data
@Entity
public class Transaction {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private Money value;

    @ManyToOne
    private Customer receiver;
    @ManyToOne
    private Customer sender;
    @ManyToOne
    private BankStatement bankStatement;

}
