package com.example.springbatch.scheduling;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class BatchScheduling {
    @Autowired
    JobLauncher jobLauncher;
    @Autowired
    @Qualifier("importCustomerJob")
    Job importCustomerJob;

    @Autowired
    @Qualifier("cashbackJob")
    Job cashbackJob;


    @Scheduled(cron = "0 0 0 1/1 * ?")
    public void runImportCustomerJob() throws Exception {
        Map<String, JobParameter> jobParameterMap = new HashMap<>();
        jobParameterMap.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters parameters = new JobParameters(jobParameterMap);
        jobLauncher.run(importCustomerJob, parameters);
    }

    @Scheduled(cron = "0 0 2 1/1 * ?")
    public void runCashbackJob()  throws Exception {
        Map<String, JobParameter> jobParameterMap = new HashMap<>();
        jobParameterMap.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters parameters = new JobParameters(jobParameterMap);
        jobLauncher.run(cashbackJob, parameters);
    }
}
