package com.example.person.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.person.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
    
    @Query(nativeQuery = true, value = "SELECT * FROM tb_address WHERE id_person = :personId")
    List<Address> listAddressesBelongingToPersonId(Long personId);
}