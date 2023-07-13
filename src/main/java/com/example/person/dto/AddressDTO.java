package com.example.person.dto;

import com.example.person.entity.Person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    private Long id;
    private String street;
    private String zipcode;
    private Integer number;
    private String city;
    private Person person;
}