package com.example.springbatch.writer;

import com.example.springbatch.Customer;
import com.example.springbatch.CustomerRepository;
import com.example.springbatch.CustomerService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerWriter implements ItemWriter<Customer> {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerService customerService;

    @Override
    public void write(List<? extends Customer> customers) throws Exception {

        customers.forEach(customer -> customerService.importCustomer(customer));
    }
}
