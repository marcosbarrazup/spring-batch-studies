package com.example.springbatch;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class CustomerDTO {

    private String name;
    private String cpf;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;
    private List<Address> addresses = new ArrayList<>();
    private BigDecimal balance;

    public CustomerDTO(Customer customer) {
        this.name = customer.getName();
        this.cpf = customer.getCpf();
        this.birthDate = customer.getBirthDate();
        this.balance = customer.getBalance().getNumberStripped();
        if (customer.getAddresses() != null)
            this.addresses.addAll(customer.getAddresses());
    }

}
