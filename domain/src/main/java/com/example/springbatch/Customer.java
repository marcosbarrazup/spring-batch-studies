package com.example.springbatch;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.javamoney.moneta.Money;
import org.springframework.beans.factory.annotation.Autowired;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;


@Data
@NoArgsConstructor
@Entity
public class Customer {

    private static final CurrencyUnit brlCurrency = Monetary.getCurrency("BRL");

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String name;
    private String cpf;
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
    private Money balance;


    public Customer (CustomerCSV customerCSV)  {

        this.name =customerCSV.getName();
        this.cpf = customerCSV.getCpf();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.birthDate = LocalDate.parse(customerCSV.getBirthDate(),formatter);
        this.addresses = new ArrayList<>();
        this.balance = Money.zero(brlCurrency);
    }

    public void deposit(Money amount) {
        if (amount.isGreaterThan(Money.zero(brlCurrency))) {
            this.balance.add(amount);
        } else {
            throw new IllegalArgumentException("Deposit amount should be positive!");
        }
    }

    public void withdraw(Money amount) {
        Money resultBalance = balance.subtract(amount);
        if (resultBalance.isGreaterThan(Money.zero(brlCurrency))) {
            this.balance = resultBalance;
        } else {
            throw new IllegalArgumentException("Insufficient balance!");
        }
    }


}
