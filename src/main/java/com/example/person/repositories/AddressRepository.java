package com.example.person.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.person.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}