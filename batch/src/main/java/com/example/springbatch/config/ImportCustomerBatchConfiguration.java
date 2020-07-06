package com.example.springbatch.config;

import com.example.springbatch.domain.Customer;
import com.example.springbatch.csv.CustomerCSV;
import com.example.springbatch.exceptions.CustomerNotFoundException;
import com.example.springbatch.exceptions.ExistentAccountException;
import com.example.springbatch.exceptions.ThereIsNoEligibleCampaignException;
import com.example.springbatch.listener.ImportCustomerListener;
import com.example.springbatch.processor.ImportCustomerProcessor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.PersistenceException;

@Configuration
@EnableBatchProcessing
@ComponentScan(value = "com.example")
public class ImportCustomerBatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    @Autowired
    public ItemWriter<Customer> importCustomerWriter;
    @Autowired
    public ImportCustomerListener importCustomerListener;
    @Autowired
    public ImportCustomerProcessor importCustomerProcessor;
    @Autowired
    public ItemReader<CustomerCSV> importCustomerReader;

    @Bean
    @Qualifier("importCustomerJob")
    public Job importCustomerJob() {
        return this.jobBuilderFactory.get("importCustomerJob")
                .incrementer(new RunIdIncrementer())
                .listener(importCustomerListener)
                .start(importCustomerStep())
                .build();
    }

    @Bean
    public Step importCustomerStep() {
        return stepBuilderFactory.get("importCustomer")
                .<CustomerCSV, Customer>chunk(50)
                .reader(importCustomerReader)
                .processor(importCustomerProcessor)
                .writer(importCustomerWriter)
                .faultTolerant()
                .skip(ExistentAccountException.class)
                .skipLimit(Integer.MAX_VALUE)
                .build();
    }

}
