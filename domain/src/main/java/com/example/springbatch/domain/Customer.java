package com.example.springbatch.domain;

import com.example.springbatch.csv.CustomerCSV;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(indexes = {@Index(name = "cpfIndex", columnList = "cpf", unique = true)})
public class Customer {

    private static final CurrencyUnit brlCurrency = Monetary.getCurrency("BRL");

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String name;
    private String cpf;
    private String email;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses;
    @OneToMany(mappedBy = "customer")
    private List<BankStatement> bankStatements;
    @OneToMany(mappedBy = "sender")
    List<Transaction> sentTransactions;
    @OneToMany(mappedBy = "receiver")
    List<Transaction> receivedTransactions;
    private Double balance;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cashback> cashbacks;


    public Customer(CustomerCSV customerCSV) {

        this.name = customerCSV.getName();
        this.cpf = customerCSV.getCpf();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.birthDate = LocalDate.parse(customerCSV.getBirthDate(), formatter);
        this.addresses = new ArrayList<>();
        this.balance = 0.0;
        this.email = formatEmail(customerCSV.getEmail());
    }



    public void deposit(Double amount) {
        if (amount > 0.0) {
            this.balance += amount;
        } else {
            throw new IllegalArgumentException("Deposit amount should be positive!");
        }
    }

    public void withdraw(Double amount) {
        double resultBalance = balance - amount;
        if (resultBalance > 0.0) {
            this.balance = resultBalance;
        } else {
            throw new IllegalArgumentException("Insufficient balance!");
        }
    }


    private String formatEmail(String email) {
        return email.toLowerCase().replaceAll(" ", "-")
                .replaceAll("ç", "c")
                .replaceAll("[áâã]", "a")
                .replaceAll("[éêẽ]", "e")
                .replaceAll("[íîĩ]", "i")
                .replaceAll("[óôõ]", "o")
                .replaceAll("[úûũ]", "u");
    }
}
