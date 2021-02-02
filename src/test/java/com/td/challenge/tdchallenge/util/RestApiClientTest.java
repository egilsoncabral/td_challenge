package com.td.challenge.tdchallenge.util;

import com.td.challenge.tdchallenge.aggregator.PhoneNumberNotFoundException;
import com.td.challenge.tdchallenge.sector.dto.SectorResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RestApiClientTest {

    @InjectMocks
    private RestApiClient restApiClient;

    @Mock
    private RestTemplate restTemplate;

    @Test
    void when_consumeAPI_with_valid_url_return_valid_response() {
        // Arrange
        when(restTemplate.getForEntity(anyString(), any()))
                .thenReturn(ResponseEntity.ok().body(new SectorResponseDTO("1", "Technology")));
        // Act
        var actual = restApiClient.consumeAPI("teste/api");

        // Assert
        assertAll(() -> assertNotNull(actual),
                () -> assertEquals(actual.getStatusCode(), HttpStatus.OK));
    }

    @Test
    void when_consumeAPI_with_invalid_url_return_valid_response() {
        // Arrange
        when(restTemplate.getForEntity(anyString(), any()))
                .thenThrow(HttpClientErrorException.class);
        // Act
        var actual = restApiClient.consumeAPI("wrong/url");

        // Assert
        assertNull(actual);
    }
}
