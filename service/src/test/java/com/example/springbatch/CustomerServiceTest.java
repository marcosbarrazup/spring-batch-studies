package com.example.springbatch;

import org.junit.Rule;
import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
public class CustomerServiceTest {

    @Test
    public void generateCashbacks() throws IOException {

        BufferedReader buffRead = new BufferedReader(new FileReader("/home/marcosbarra/Área de Trabalho/springbatch/cashback-data.csv"));
        String linha = "";
        List<String> lines = new ArrayList<>();
        while (true) {
            if (linha != null) {
                System.out.println(linha);
                lines.add(linha);
            } else
                break;
            linha = buffRead.readLine();
        }
        buffRead.close();

        DecimalFormat formato = new DecimalFormat("#.##");


        BufferedWriter buffWrite = new BufferedWriter(new FileWriter("/home/marcosbarra/Área de Trabalho/springbatch/cashback-data2.csv"));

        for (String line : lines) {
            Double randomNumber = Math.random() * (500) + 1;
            String number = formato.format(randomNumber).replace(",", ".");
            System.out.println(line + number);

            buffWrite.append(line).append(number).append("\n");

        }
        buffWrite.close();
    }

    @Test
    public void generateCustomers() throws IOException {

        BufferedReader buffRead = new BufferedReader(new FileReader("/home/marcosbarra/Área de Trabalho/springbatch/customer-data.csv"));
        String linha = buffRead.readLine();
        List<String> lines = new ArrayList<>();
        while (true) {
            if (linha != null) {
                System.out.println(linha);
                lines.add(linha);
            } else
                break;
            linha = buffRead.readLine();
        }
        buffRead.close();

        DecimalFormat formato = new DecimalFormat("#.##");


        BufferedWriter buffWrite = new BufferedWriter(new FileWriter("/home/marcosbarra/Área de Trabalho/springbatch/customer-data2.csv"));

        for (String line : lines) {
            String name = line.split(",")[0];
            System.out.println(line + "," + name + "@gmail.com");

            buffWrite.append(line).append(",").append(name).append("@gmail.com").append("\n");

        }
        buffWrite.close();
    }

    @Test
    public void editBithDate() throws IOException {

        BufferedReader buffRead = new BufferedReader(new FileReader("/home/marcosbarra/Área de Trabalho/springbatch/customer-data.csv"));
        String linha = buffRead.readLine();
        List<String> lines = new ArrayList<>();
        while (true) {
            if (linha != null) {
                System.out.println(linha);
                lines.add(linha);
            } else
                break;
            linha = buffRead.readLine();
        }
        buffRead.close();

        DecimalFormat formato = new DecimalFormat("#.##");


        BufferedWriter buffWrite = new BufferedWriter(new FileWriter("/home/marcosbarra/Área de Trabalho/springbatch/customer-data2.csv"));

        for (String line : lines) {
            String year = line.split(",")[2].split("/")[2];
            if (Integer.parseInt(year) > 2005) {
                line = line.replaceAll(year, String.valueOf(Integer.parseInt(year) - 90));
            }

            buffWrite.append(line).append("\n");

        }
        buffWrite.close();
    }

}