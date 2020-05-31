package com.example.springbatch.processor;

import com.example.springbatch.Address;
import com.example.springbatch.Customer;
import com.example.springbatch.CustomerCSV;
import org.javamoney.moneta.Money;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import java.util.Arrays;

@Component(value = "insertCustomerProcessor")
public class ImportCustomerProcessor implements ItemProcessor<CustomerCSV, Customer> {
    private static final CurrencyUnit brlCurrency = Monetary.getCurrency("BRL");

    @Override
    public Customer process(CustomerCSV customerCSV) throws Exception {

        Customer customer = new Customer(customerCSV);
        String[] address1String = customerCSV.getAddress1().split(",");
        Address address1 = new Address(
                address1String[0],
                address1String[1],
                address1String[2],
                address1String[3],
                address1String[4]
        );

        if(customerCSV.getAddress2() !=null && !customerCSV.getAddress2().isBlank()) {
            String[] address2String = customerCSV.getAddress2().split(",");
            Address address2 = new Address(
                    address2String[0],
                    address2String[1],
                    address2String[2],
                    address2String[3],
                    address2String[4]
            );
            customer.getAddresses().addAll(Arrays.asList(address1, address2));
        }
        else {
            customer.getAddresses().add(address1);
        }
        customer.setBalance(Money.zero(brlCurrency));

        return customer;
    }

}
