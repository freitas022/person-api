package com.example.person.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.ActiveProfiles;

import com.example.person.dto.AddressDTO;
import com.example.person.entity.Address;
import com.example.person.entity.Person;
import com.example.person.repositories.AddressRepository;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;
    @Mock
    private ModelMapper mapper;
    @InjectMocks
    private AddressService addressService;

    private Address address;
    private AddressDTO addressDTO;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        addressService = new AddressService(addressRepository, mapper);
        
        addressDTO = new AddressDTO(1L,
                "Street",
                "12345-666",
                125,
                "City",
                new Person(1L, "Maria", LocalDate.now()));
        
        address = new Address(1L,
                "Street",
                "12345-666",
                125,
                "City",
                new Person(1L, "Maria", LocalDate.now()));
    }
    
    @DisplayName("When save Address should be have return an AddressDTO")
    @Test
    void testSaveAddress_Success() {
        //Given
        when(addressRepository.save(mapper.map(addressDTO, Address.class))).thenReturn(address);
        
        //When
        addressService.save(addressDTO);

        //Then
        assertNotNull(addressDTO);
        assertEquals(addressDTO.getId(), address.getId());
        assertEquals(addressDTO.getClass(), AddressDTO.class);
    }

    @DisplayName("Given the personId when list Addresses belonging a this person then return a list of AddressDTO")
    @Test
    void givenPersonId_whenListAddressesBelongingToPersonId_thenReturnListOfAddressDTOs() {
        // Given
        Long personId = 1L;
        List<Address> addresses = List.of(address);

        when(addressRepository.listAddressesBelongingToPersonId(personId)).thenReturn(addresses);

        // When
        List<AddressDTO> addressDTOs = addressService.listAddressesBelongingToPersonId(personId);

        // Then
        assertThat(addressDTOs, is(not(empty())));
        assertThat(addressDTOs.size(), is(equalTo(1)));
    }

    @DisplayName("Given the personId when list Addresses belonging an this person then return a empty list of AddressDTO")
    @Test
    void givenPersonId_whenListAddressesBelongingToPersonId_thenReturnEmptyListOfAddressDTO() {
        // Given
        Long personId = 1L;

        when(addressRepository.listAddressesBelongingToPersonId(personId)).thenReturn(Collections.emptyList());

        // When
        List<AddressDTO> addressDTOs = addressService.listAddressesBelongingToPersonId(personId);

        // Then
        assertThat(addressDTOs, is(empty()));
        assertThat(addressDTOs.size(), is(equalTo(0)));
    }
}
