package com.example.springbatch;

import com.example.springbatch.domain.BankStatement;
import com.example.springbatch.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.Optional;

@Repository
public interface BankStatementRepository extends JpaRepository<BankStatement, Integer> {

    public Optional<BankStatement> findById(Integer id);
    public List<BankStatement> findAllByMonthAndYear(Month month, Year year);
    public List<BankStatement> findAllByCustomer(Customer customer);
    public List<BankStatement> findAllByCustomerAndMonthAndYear(Customer customer, Month month, Year year);
}
