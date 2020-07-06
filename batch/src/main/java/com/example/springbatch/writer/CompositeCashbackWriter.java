package com.example.springbatch.writer;

import com.example.springbatch.domain.Cashback;
import com.example.springbatch.domain.Customer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.batch.item.support.builder.CompositeItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class CompositeCashbackWriter implements ItemWriter<Cashback> {

    @Autowired
    private JpaItemWriter<Cashback> cashbackItemWriter;
    @Autowired
    private JpaItemWriter<Customer>  customerWriter;

    @Override
    public void write(List<? extends Cashback> cashbacks) {

        cashbacks.forEach(cashback -> {

            cashbackItemWriter.write(Collections.singletonList(cashback));
            customerWriter.write(Collections.singletonList(cashback.getCustomer()));
        });
    }

}
