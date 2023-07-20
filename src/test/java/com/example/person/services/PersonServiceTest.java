package com.example.person.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.ActiveProfiles;

import com.example.person.dto.PersonDTO;
import com.example.person.entity.Person;
import com.example.person.exception.NotFoundException;
import com.example.person.repositories.PersonRepository;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private PersonService personService;

    private Person person;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        personService = new PersonService(personRepository, mapper);
        person = new Person(1L, "Maria", LocalDate.now());
    }

    @Test
    void whenCreatePerson_Success_ThenReturnAPersonObject() {
        // Given
        PersonDTO personDTO = new PersonDTO(1L, "Maria", LocalDate.now());

        // When
        when(personRepository.save(mapper.map(personDTO, Person.class))).thenReturn(person);
        this.personService.save(personDTO);

        // Then
        assertEquals(personDTO.getId(), person.getId());
        assertEquals(personDTO.getName(), person.getName());
        assertEquals(personDTO.getBirthdate(), person.getBirthdate());
        assertEquals(PersonDTO.class, personDTO.getClass());
    }

    @Test
    void canGetIdByPerson() {
        // Given
        Long id = 20L;
        // When
        doReturn(Optional.of(person)).when(personRepository).findById(id);
        PersonDTO personDTO = personService.findById(id);
        // Then
        assertEquals(person.getId(), personDTO.getId());
        assertEquals(person.getName(), personDTO.getName());
        assertEquals(person.getBirthdate(), personDTO.getBirthdate());
        assertEquals(PersonDTO.class, personDTO.getClass());
    }

    @Test
    void canGetIdByPerson_NotFoundException() {
        // Given
        Long idFalse = 223030L;
        // When
        when(personRepository.findById(idFalse)).thenThrow(new NotFoundException(idFalse));
        // Then
        assertThrows(NotFoundException.class, () -> this.personService.findById(idFalse));
    }
    
    @Test
    void testUpdate_Sucess() {
        // Given
        Long id = 123L;
        PersonDTO updatedRequest = new PersonDTO(id, "Updated", LocalDate.of(1960, 1, 1));
        Person person = new Person(id, "Maria", LocalDate.now());

        // When
        doReturn(Optional.of(person)).when(this.personRepository).findById(isA(Long.class));
        when(personRepository.save(person)).thenReturn(person);

        Person updatedPerson = this.personService.update(id, updatedRequest);
        // Then
        assertEquals(updatedPerson.getId(), person.getId());
    }

    @Test
    void testUpdate_NotFoundException() {
        // Given
        Long idFalse= 24213L;
        Long id = 123L;
        PersonDTO updatedRequest = new PersonDTO(id, "Updated", LocalDate.of(1960, 1, 1));
        Person person = new Person(id, "Maria", LocalDate.now());

        // When
        doReturn(Optional.of(person)).when(this.personRepository).findById(isA(Long.class));
        when(personRepository.save(person)).thenThrow(new NotFoundException(idFalse));

        // Then
        assertThrows(NotFoundException.class, () -> this.personService.update(idFalse, updatedRequest));
    }

    @Test
    void testFindAll_Sucess() {
        
        when(personRepository.findAll()).thenReturn(Collections.singletonList(person));

        List<PersonDTO> list = personService.findAll();

        assertNotNull(list);
        assertEquals(1, list.size());
    }

    @Test
    void testFindAll_EmptyList() {
        
        when(personRepository.findAll()).thenReturn(Collections.emptyList());

        List<PersonDTO> list = personService.findAll();

        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }
}