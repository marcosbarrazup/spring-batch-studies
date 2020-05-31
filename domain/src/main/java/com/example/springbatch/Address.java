package com.example.springbatch;

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
    private Customer customer;

    public Address(String country, String state, String city, String street, String streetNumber) {
        this.country = country;
        this.state = state;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
    }


}
