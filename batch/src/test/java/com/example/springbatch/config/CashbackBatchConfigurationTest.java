package com.example.springbatch.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBatchTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes=CashbackBatchConfiguration.class)
public class CashbackBatchConfigurationTest {
    @Autowired
    JobLauncher jobLauncher;
    @Autowired
    @Qualifier("importCustomerJob")
    Job importCustomerJob;

    @Autowired
    @Qualifier("cashbackJob")
    Job cashbackJob;

    @Test
    public void testJob() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        Map<String, JobParameter> jobParameterMap = new HashMap<>();
        jobParameterMap.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters parameters = new JobParameters(jobParameterMap);
        jobLauncher.run(cashbackJob, parameters);
    }
}