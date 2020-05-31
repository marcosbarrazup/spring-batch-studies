package com.example.springbatch.reader;

import com.example.springbatch.CustomerCSV;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class ImportCustomerReader {

    @Bean
    public FlatFileItemReader<CustomerCSV> reader() {
        return new FlatFileItemReaderBuilder<CustomerCSV>()
                .name("customerItemReader")
                .resource(new ClassPathResource("customer-data.csv"))
                .delimited()
                .names("name", "cpf", "birthDate", "address1", "address2")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {
                    {
                        setTargetType(CustomerCSV.class);
                    }
                }).build();
    }
}
