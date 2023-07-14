package com.example.person.dto;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;

import com.example.person.entity.Person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
    
    private Long id;
    private String name;
    private LocalDate birthdate;

    public PersonDTO(Person entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
