package com.example.person.services;

import com.example.person.entity.Address;
import com.example.person.repositories.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address save(Address request) {
        return addressRepository.save(request);
    }
}
