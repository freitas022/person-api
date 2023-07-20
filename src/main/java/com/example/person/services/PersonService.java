package com.example.person.services;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.person.dto.PersonDTO;
import com.example.person.entity.Person;
import com.example.person.exception.NotFoundException;
import com.example.person.repositories.PersonRepository;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final ModelMapper mapper;

    public PersonService(PersonRepository personRepository, ModelMapper mapper) {
        this.personRepository = personRepository;
        this.mapper = mapper;
    }

    public Person save(PersonDTO request) {
        return personRepository.save(mapper.map(request, Person.class));
    }

    public Person update(final Long id, PersonDTO request) {
        return personRepository.findById(id).map(person -> {
            setIfNonNull(person::setName, request.getName());
            setIfNonNull(person::setBirthdate, request.getBirthdate());

            return personRepository.save(person);
        }).orElseThrow(() -> new NotFoundException(id));
    }

    public List<PersonDTO> findAll() {
        return personRepository.findAll().stream().map(PersonDTO::new).collect(Collectors.toList());
    }

    public PersonDTO findById(final Long id) {
        return personRepository.findById(id).map(PersonDTO::new).orElseThrow(() -> new NotFoundException(id));
    }

    private <T> void setIfNonNull(final Consumer<T> setter, final T value) {
        if (value != null) {
            setter.accept(value);
        }
    }

}
