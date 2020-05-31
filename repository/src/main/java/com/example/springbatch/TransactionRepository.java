package com.example.springbatch;

import com.example.springbatch.Address;
import com.example.springbatch.Customer;
import com.example.springbatch.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    public Optional<Transaction> findById(Integer Id);
    public List<Transaction> findAll();
    public List<Transaction> findAllBySender(Customer sender);
    public List<Transaction> findAllByReceiver(Customer receiver);
    public List<Transaction> findAllBySenderAndReceiver(Customer sender, Customer receiver);
}
