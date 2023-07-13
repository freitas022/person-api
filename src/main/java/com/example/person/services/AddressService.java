package com.example.person.services;

import com.example.person.dto.AddressDTO;
import com.example.person.entity.Address;
import com.example.person.repositories.AddressRepository;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    
    private final AddressRepository addressRepository;
    private final ModelMapper mapper;

    public AddressService(AddressRepository addressRepository, ModelMapper mapper) {
        this.addressRepository = addressRepository;
        this.mapper = mapper;
    }

    public AddressDTO save(AddressDTO request) {
        Address address = mapper.map(request, Address.class);
        Address created = addressRepository.save(address);
        return mapper.map(created, AddressDTO.class);
    }
}
