package com.example.springbatch;

import com.example.springbatch.Address;
import com.example.springbatch.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    public Optional<Address> findById(Integer Id);
    public List<Address> findAll();
    public List<Address> findAllByCustomer(Customer customer);
}
