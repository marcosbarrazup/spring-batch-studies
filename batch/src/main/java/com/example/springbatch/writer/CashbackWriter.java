package com.example.springbatch.writer;

import com.example.springbatch.CashbackService;
import com.example.springbatch.domain.Cashback;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Component
public class CashbackWriter {

    @Autowired
    EntityManagerFactory emf;

    @Bean
    public JpaItemWriter<Cashback> cashbackJpaItemWriter() {
        JpaItemWriter<Cashback> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(emf);
        return writer;
    }
}
