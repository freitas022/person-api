package com.example.person.services;

import org.springframework.stereotype.Service;

import com.example.person.entity.Person;
import com.example.person.repositories.PersonRepository;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person save(Person request) {
        return personRepository.save(request);
    }

}
