package com.example.springbatch;

import com.example.springbatch.domain.Customer;
import com.example.springbatch.exceptions.CustomerNotFoundException;
import com.example.springbatch.exceptions.ExistentAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressRepository addressRepository;

    private static final CurrencyUnit brlCurrency = Monetary.getCurrency("BRL");
    public Customer findByCpf(String cpf) throws CustomerNotFoundException {

        Optional<Customer> customer = customerRepository.findByCpf(cpf);
        if (customer.isEmpty()) {
            throw new CustomerNotFoundException("Customer not found!");
        }
        return customer.get();
    }

    public Customer insert(Customer customer) {

        if (customerRepository.findByCpf(customer.getCpf()).isEmpty()) {
            customer.setId(null);
            customer.setBalance(0.0);
            customer.getAddresses().forEach(address -> addressRepository.saveAndFlush(address));
            return customerRepository.saveAndFlush(customer);
        }

        throw new ExistentAccountException("CPF already registered!");
    }

    public Customer importCustomer(Customer customer) {

        if (customerRepository.findByCpf(customer.getCpf()).isEmpty()) {
            customer.setId(null);
            customer.setBalance(0.0);
            Customer result = customerRepository.saveAndFlush(customer);
            customer.getAddresses().forEach(address -> addressRepository.saveAndFlush(address));
            return result;
        }
        return null;
    }
}
