package com.td.challenge.tdchallenge.aggregator.service;

import com.td.challenge.tdchallenge.aggregator.PhoneNumberNotFoundException;
import com.td.challenge.tdchallenge.sector.dto.SectorResponseDTO;
import com.td.challenge.tdchallenge.sector.service.PhoneSectorService;
import com.td.challenge.tdchallenge.validator.TelephoneNumberValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AggregatorServiceTest {

    @InjectMocks
    AggregatorService aggregatorService;

    @Mock
    private FileHandlerService fileHandlerService;

    @Mock
    private TelephoneNumberValidator telephoneNumberValidator;

    @Mock
    private PhoneSectorService phoneSectorService;

    @Mock
    private PhoneNumberPrefixService phoneNumberPrefixService;

    @Test
    void when_aggregatePhoneNumbers_with_valid_phone_number_return_sector_map() throws IOException {
        // Arrange
        when(telephoneNumberValidator.validate(anyList()))
                .thenReturn(List.of("1983236248"));
        when(fileHandlerService.getFiles())
                .thenReturn(Set.of("1"));
        when(phoneSectorService.getPhoneNumberSector(anyString()))
                .thenReturn(new SectorResponseDTO("1", "Technology"));
        when(phoneNumberPrefixService.getPrefix(anyString(), anySet()))
                .thenReturn("1");
        // Act
        var actual = aggregatorService.aggregatePhoneNumbers(List.of("+1983236248"));

        // Assert
        assertAll(() -> assertNotNull(actual),
                () -> assertFalse(actual.isEmpty()));
    }

    @Test
    void when_aggregatePhoneNumbers_with_invalid_phone_number_then_throws_exception() throws IOException {
        // Arrange
        when(telephoneNumberValidator.validate(anyList()))
                .thenReturn(List.of());

        // Act
        var exception = assertThrows(PhoneNumberNotFoundException.class,
                () -> aggregatorService.aggregatePhoneNumbers(List.of("+983236248")));

        // Assert
        assertEquals("No valid phone number found.", exception.getMessage());
    }


}
