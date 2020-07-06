package com.example.springbatch;

import com.example.springbatch.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByCpf(String cpf);
    Optional<Customer> findById(Integer id);
    List<Customer> findAll();
}
