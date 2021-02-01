package com.td.challenge.tdchallenge.sector.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.td.challenge.tdchallenge.sector.dto.SectorResponseDTO;
import com.td.challenge.tdchallenge.util.RestApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PhoneSectorService {

    @Value("${challenge.rest.endpoint}")
    private String challengeEndpoint;

    @Value("${challenge.rest.sector}")
    private String sectorEndpoint;

    private ObjectMapper mapper;

    private RestApiClient restConsumer;

    public PhoneSectorService (ObjectMapper mapper, RestApiClient restConsumer) {
        this.mapper = mapper;
        this.restConsumer = restConsumer;
    }

    public SectorResponseDTO getPhoneNumberSector(String phoneNumber) throws JsonProcessingException {
        log.info("Getting sector of number: {}", phoneNumber);
        ResponseEntity<SectorResponseDTO> response = restConsumer.consumeAPI( challengeEndpoint + sectorEndpoint + phoneNumber );
        return validateResponse(response);
    }

    private SectorResponseDTO validateResponse(ResponseEntity<SectorResponseDTO> response) throws JsonProcessingException {
        if (null == response || !response.getStatusCode().equals(HttpStatus.OK)) {
            return null;
        }
        return mapper.readValue(String.valueOf(response.getBody()), SectorResponseDTO.class);
    }
}
