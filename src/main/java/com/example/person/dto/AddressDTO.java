package com.example.person.dto;

import org.springframework.beans.BeanUtils;

import com.example.person.entity.Address;
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

    public AddressDTO(Address entity) {
        BeanUtils.copyProperties(entity, this);
    }
}