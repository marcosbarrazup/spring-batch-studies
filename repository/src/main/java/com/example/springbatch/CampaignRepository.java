package com.example.springbatch;

import com.example.springbatch.domain.Campaign;
import com.example.springbatch.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    Optional<Campaign> findById(Integer id);
    List<Campaign> findAll();
    List<Campaign> findAllByStartDateBeforeAndEndDateAfter(LocalDate startDate, LocalDate endDate);

}
