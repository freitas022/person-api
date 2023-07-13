package com.example.person.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.person.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}