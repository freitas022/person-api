package com.example.person.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.person.dto.PersonDTO;
import com.example.person.entity.Person;
import com.example.person.repositories.PersonRepository;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final ModelMapper mapper;

    public PersonService(PersonRepository personRepository, ModelMapper mapper) {
        this.personRepository = personRepository;
        this.mapper = mapper;
    }

    public PersonDTO save(PersonDTO request) {
        Person person = mapper.map(request, Person.class);
        Person created = personRepository.save(person);
        return mapper.map(created, PersonDTO.class);
    }

}
