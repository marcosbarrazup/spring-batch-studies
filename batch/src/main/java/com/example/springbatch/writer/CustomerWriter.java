package com.example.springbatch.writer;

import com.example.springbatch.domain.Customer;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;

@Component
public class CustomerWriter {

    @Autowired
    EntityManagerFactory emf;

    @Bean
    public JpaItemWriter<Customer> customerJpaItemWriter() {
        JpaItemWriter<Customer> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(emf);
        return writer;
    }
}
