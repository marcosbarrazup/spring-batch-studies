package com.example.springbatch.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Transaction {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private Double value;

    @ManyToOne
    private Customer receiver;
    @ManyToOne
    private Customer sender;
    @ManyToOne
    private BankStatement bankStatement;

}
