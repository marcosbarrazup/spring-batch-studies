package com.example.springbatch.controllers;

import com.example.springbatch.domain.Customer;
import com.example.springbatch.dto.CustomerDTO;
import com.example.springbatch.CustomerService;
import javassist.NotFoundException;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    JobLauncher jobLauncher;
    @Autowired
    @Qualifier("importCustomerJob")
    Job job;

    @RequestMapping(value = "/{cpf}", method = RequestMethod.GET)
    public CustomerDTO find(@PathVariable String cpf) throws NotFoundException {

        Customer customer = customerService.findByCpf(cpf);
        return new CustomerDTO(customer);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO insert(@Validated @RequestBody Customer customerRequest) {

        Customer customer = customerService.insert(customerRequest);
        return new CustomerDTO(customer);

    }

    @GetMapping(value = "/importCustomers")
    @ResponseStatus(HttpStatus.OK)
    public BatchStatus importCustomers() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        Map<String, JobParameter> jobParameterMap = new HashMap<>();
        jobParameterMap.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters parameters = new JobParameters(jobParameterMap);
        JobExecution jobExecution = jobLauncher.run(job, parameters);

        return jobExecution.getStatus();
    }



}
