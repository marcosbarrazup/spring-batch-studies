package com.example.springbatch.config;

import com.example.springbatch.csv.CashbackCSV;
import com.example.springbatch.csv.CustomerCSV;
import com.example.springbatch.domain.Cashback;
import com.example.springbatch.domain.Customer;
import com.example.springbatch.exceptions.CustomerNotFoundException;
import com.example.springbatch.exceptions.ThereIsNoEligibleCampaignException;
import com.example.springbatch.listener.CashbackListener;
import com.example.springbatch.listener.ImportCustomerListener;
import com.example.springbatch.processor.CashbackProcessor;
import com.example.springbatch.processor.ImportCustomerProcessor;
import com.sun.mail.smtp.SMTPAddressFailedException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.builder.TaskletStepBuilder;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.item.database.orm.JpaNativeQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

import javax.persistence.EntityManagerFactory;
import java.io.FileNotFoundException;
import java.util.List;

@Configuration
@EnableBatchProcessing
@ComponentScan(value = "com.example")
public class CashbackBatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    @Autowired
    public CashbackListener cashbackListener;
    @Autowired
    public ItemReader<CashbackCSV> cashbackItemReader;
    @Autowired
    public CashbackProcessor cashbackProcessor;
    @Autowired
    public ItemWriter<Cashback> compositeCashbackWriter;


    @Bean
    @Qualifier("cashbackJob")
    public Job cashbackJob() {
        return this.jobBuilderFactory.get("cashbackJob")
                .incrementer(new RunIdIncrementer())
                .listener(cashbackListener)
                .start(doCashbackStep())
                .build();
    }


    @Bean
    public Step doCashbackStep() {
        return stepBuilderFactory.get("doCashbackStep")
                .<CashbackCSV, Cashback>chunk(50)
                .reader(cashbackItemReader)
                .processor(cashbackProcessor)
                .writer(compositeCashbackWriter)
                .faultTolerant()
                .retry(CustomerNotFoundException.class)
                .skip(CustomerNotFoundException.class)
                .skip(ThereIsNoEligibleCampaignException.class)
                .skipLimit(Integer.MAX_VALUE)
                .noSkip(FileNotFoundException.class)
                .build();
    }


}
