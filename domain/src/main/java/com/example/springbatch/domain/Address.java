package com.example.springbatch.domain;

import com.example.springbatch.domain.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String country;
    private String state;
    private String city;
    private String street;
    private String streetNumber;
    @ManyToOne
    @JsonIgnore
    private Customer customer;

    public Address(String country, String state, String city, String street, String streetNumber, Customer customer) {
        this.country = country;
        this.state = state;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
        this.customer = customer;
    }


}
