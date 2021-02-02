package com.td.challenge.tdchallenge.sector.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.td.challenge.tdchallenge.sector.dto.SectorResponseDTO;
import com.td.challenge.tdchallenge.util.RestApiClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PhoneSectorServiceTest {

    @InjectMocks
    PhoneSectorService phoneSectorService;

    @Mock
    private ObjectMapper mapper;

    @Mock
    private RestApiClient restConsumer;

    @Test
    void getPhoneNumberSector_with_valid_phone_number_return_valid_response() throws JsonProcessingException {
        // Arrange
        SectorResponseDTO sectorResponseDTO = new SectorResponseDTO("1", "Technology");
        when(restConsumer.consumeAPI(anyString()))
                .thenReturn(ResponseEntity.ok().body(sectorResponseDTO));
        when(mapper.readValue(String.valueOf(sectorResponseDTO), SectorResponseDTO.class))
                .thenReturn(sectorResponseDTO);
        // Act
        var actual = phoneSectorService.getPhoneNumberSector("+1983236248");

        // Assert
        assertAll(() -> assertNotNull(actual),
                () -> assertEquals(actual.getSector(), "Technology"));
    }

    @Test
    void getPhoneNumberSector_with_invalid_phone_number_return_null() throws JsonProcessingException {
        // Arrange
        when(restConsumer.consumeAPI(anyString()))
                .thenReturn(null);
        // Act
        var actual = phoneSectorService.getPhoneNumberSector("+983236248");

        // Assert
        assertNull(actual);
    }
}
