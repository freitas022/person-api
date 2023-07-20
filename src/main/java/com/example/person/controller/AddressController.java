package com.example.person.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.person.dto.AddressDTO;
import com.example.person.entity.Address;
import com.example.person.services.AddressService;

@RestController
@RequestMapping("/api/addressess")
public class AddressController {

    private final AddressService addressService;
    

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<Address> create(@RequestBody AddressDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.save(request));
    }

    @GetMapping("/{personId}/list")
    public ResponseEntity<List<AddressDTO>> listAddressesBelongingToPersonId(@PathVariable final Long personId) {
        return ResponseEntity.status(HttpStatus.OK).body(addressService.listAddressesBelongingToPersonId(personId));
    }
}
