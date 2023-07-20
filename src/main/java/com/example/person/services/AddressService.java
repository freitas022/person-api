package com.example.person.services;

import com.example.person.dto.AddressDTO;
import com.example.person.entity.Address;
import com.example.person.repositories.AddressRepository;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public Address save(AddressDTO request) {
        Address response = mapper.map(request, Address.class);
        return addressRepository.save(response);
    }

    @Transactional
    public List<AddressDTO> listAddressesBelongingToPersonId(final Long personId) {
        return addressRepository.listAddressesBelongingToPersonId(personId)
                .stream()
                .map(AddressDTO::new)
                .collect(Collectors.toList());
    }
}
