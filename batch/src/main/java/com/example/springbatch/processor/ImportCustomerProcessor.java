package com.example.springbatch.processor;

import com.example.springbatch.CustomerRepository;
import com.example.springbatch.csv.CustomerCSV;
import com.example.springbatch.domain.Address;
import com.example.springbatch.domain.Customer;
import com.example.springbatch.exceptions.ExistentAccountException;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import java.util.Arrays;

@Component(value = "insertCustomerProcessor")
public class ImportCustomerProcessor implements ItemProcessor<CustomerCSV, Customer> {
    private static final CurrencyUnit brlCurrency = Monetary.getCurrency("BRL");

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer process(CustomerCSV customerCSV) {

        Customer customer = new Customer(customerCSV);
        if (customerRepository.findByCpf(customer.getCpf()).isPresent()) {
            throw new ExistentAccountException("O usuário"+ customer.getName() + " já está cadastrado.");
        }

        String[] address1String = customerCSV.getAddress1().split(",");
        Address address1 = new Address(
                address1String[0],
                address1String[1],
                address1String[2],
                address1String[3],
                address1String[4],
                customer
        );

        if(customerCSV.getAddress2() !=null && !customerCSV.getAddress2().isBlank()) {
            String[] address2String = customerCSV.getAddress2().split(",");
            Address address2 = new Address(
                    address2String[0],
                    address2String[1],
                    address2String[2],
                    address2String[3],
                    address2String[4],
                    customer
            );
            customer.getAddresses().addAll(Arrays.asList(address1, address2));
        }
        else {
            customer.getAddresses().add(address1);
        }
        customer.setBalance(0.0);
        customer.setId(null);

        return customer;
    }

}
