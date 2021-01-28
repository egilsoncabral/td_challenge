package com.td.challenge.tdchallenge.aggregator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.td.challenge.tdchallenge.aggregator.dto.AggregatorResponseDTO;
import com.td.challenge.tdchallenge.sector.dto.SectorResponseDTO;
import com.td.challenge.tdchallenge.sector.service.PhoneSectorService;
import com.td.challenge.tdchallenge.util.FileHandler;
import com.td.challenge.tdchallenge.validator.TelephoneNumberValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class AggregatorService {

    private FileHandler fileHandler;

    private TelephoneNumberValidator telephoneNumberValidator;

    private PhoneSectorService phoneSectorService;

    @Value("file.prefix.name")
    private String filePath;

    public AggregatorService (FileHandler fileHandler, TelephoneNumberValidator telephoneNumberValidator, PhoneSectorService phoneSectorService) {
        this.fileHandler = fileHandler;
        this.telephoneNumberValidator = telephoneNumberValidator;
        this.phoneSectorService = phoneSectorService;
    }

    public AggregatorResponseDTO aggregatePhoneNumbers(List<String> phoneNumbers) throws IOException, URISyntaxException {
        List<String> prefixes = fileHandler.readFile(filePath);
        List<String> validatedNumbers = telephoneNumberValidator.validate(phoneNumbers);
        for (String phoneNumber: validatedNumbers) {
            SectorResponseDTO challengeResponse = phoneSectorService.getPhoneNumberSector(phoneNumber);
            if(null != challengeResponse) {

            }
        }
        return new AggregatorResponseDTO();
    }

}
