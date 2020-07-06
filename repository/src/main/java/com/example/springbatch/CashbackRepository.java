package com.example.springbatch;

import com.example.springbatch.domain.Campaign;
import com.example.springbatch.domain.Cashback;
import com.example.springbatch.domain.Customer;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CashbackRepository extends JpaRepository<Cashback, Long> {

    List<Cashback> findAllByCustomer(Customer customer);
    List<Cashback> findAllByCampaign(Campaign campaign);
    List<Cashback> findAllByCreatedAtIsBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Cashback> findAll();

}
