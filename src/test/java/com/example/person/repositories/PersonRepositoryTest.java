package com.example.person.repositories;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.person.entity.Person;

@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;
    
    @DisplayName("Given person object when save is called, then return saved person")
    @Test
    void testGivenPersonObject_WhenSave_ThenReturnSavedPerson() {
        // given
        Person expected = new Person(null, "Maria", LocalDate.of(1990, 3, 30));

        // when
        Person actual = personRepository.save(expected);
        // then
        assertNotNull(actual);
        assertTrue(actual.getId() > 0);
    }

    @DisplayName("Given PersonList when findAll is called, then return PersonList")
    @Test
    void testGivenPersonList_WhenFindAll_ThenReturnPersonList() {
        // given
        Person p1 = new Person(25L, "Maria", LocalDate.of(1990, 3, 30));
        Person p2 = new Person(123L, "Bob", LocalDate.of(1995, 7, 7));
        personRepository.saveAll(Arrays.asList(p1, p2));
        // when
        List<Person> result = personRepository.findAll();
        // then
        assertNotNull(result);
        assertEquals(7, result.size());
    }

    @DisplayName("Given Person object when findById then return Person")
    @Test
    void testGivenPersonObject_WhenFindById_ThenReturnPerson() {
        // given
        Person expected = new Person(null, "Maria", LocalDate.of(1990, 3, 30));
        personRepository.save(expected);
        // when
        Optional<Person> actual = personRepository.findById(expected.getId());
        // then
        assertNotNull(actual);
        assertEquals(actual.get().getId(), expected.getId());
    }
    
    @DisplayName("Given Person Object when update Person then return updated Person")
    @Test
    void testGivenPersonObject_WhenUpdatePerson_ThenReturnUpdatedPerson() {
        // given
        Person expected = new Person(null, "Maria", LocalDate.of(1990, 3, 30));
        personRepository.save(expected);
        // when
        Optional<Person> optional = personRepository.findById(expected.getId());
        expected.setName("Alex");
        expected.setBirthdate(LocalDate.of(2003, 4, 25));

        Person updated = personRepository.save(expected);
        // then
        assertNotNull(updated);
        assertEquals("Alex", updated.getName());
        assertEquals(optional.get().getId(), updated.getId());
    }
}