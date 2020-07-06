package com.example.springbatch.processor;

import com.example.springbatch.CashbackService;
import com.example.springbatch.csv.CashbackCSV;
import com.example.springbatch.domain.Cashback;
import com.example.springbatch.domain.Customer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.money.CurrencyUnit;
import javax.money.Monetary;

@Component(value = "cashbackProcessor")
public class CashbackProcessor implements ItemProcessor<CashbackCSV, Cashback> {
    private static final CurrencyUnit brlCurrency = Monetary.getCurrency("BRL");


    @Autowired
    private CashbackService customerService;

    @Override
    public Cashback process(CashbackCSV cashbackCSV) {


        return customerService.doCashback(new Cashback(cashbackCSV));
    }

}
