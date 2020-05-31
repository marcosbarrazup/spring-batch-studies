package com.example.springbatch;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class CustomerCSV {

    private String name;
    private String cpf;
    private String birthDate;
    private String address1;
    private String address2;

}
