package com.example.springbatch.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Month;
import java.time.Year;
import java.util.List;

@Getter
@Setter
@Entity
public class BankStatement {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private Month month;
    private Year year;
    private Double moneyIn;
    private Double moneyOut;

    @OneToMany(mappedBy = "bankStatement")
    private List<Transaction> transactions;

    @ManyToOne
    private Customer customer;
}
