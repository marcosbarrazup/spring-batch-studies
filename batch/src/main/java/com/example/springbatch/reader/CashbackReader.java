package com.example.springbatch.reader;

import com.example.springbatch.csv.CashbackCSV;
import com.example.springbatch.domain.Cashback;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class CashbackReader {

    @Bean
    public FlatFileItemReader<CashbackCSV> readCashback() {
        return new FlatFileItemReaderBuilder<CashbackCSV>()
                .name("cashbackItemReader")
                .resource(new ClassPathResource("cashback-data.csv"))
                .delimited()
                .names("cpf", "transactionAmount")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {
                    {
                        setTargetType(CashbackCSV.class);
                    }
                }).build();
    }
}
