package com.example.springbatch.listener;

import com.example.springbatch.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ImportCustomerListener  extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(ImportCustomerListener.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void beforeJob(JobExecution jobExecution) {

    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! Import Customer JOB FINISHED! Time to verify the results");
        }
    }
}

