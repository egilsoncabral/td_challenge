package com.td.challenge.tdchallenge.aggregator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.td.challenge.tdchallenge.aggregator.PhoneNumberNotFoundException;
import com.td.challenge.tdchallenge.sector.dto.SectorResponseDTO;
import com.td.challenge.tdchallenge.sector.service.PhoneSectorService;
import com.td.challenge.tdchallenge.validator.TelephoneNumberValidator;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class AggregatorService {

    private FileHandlerService fileHandlerService;

    private TelephoneNumberValidator telephoneNumberValidator;

    private PhoneSectorService phoneSectorService;

    private PhoneNumberPrefixService phoneNumberPrefixService;

    public AggregatorService (FileHandlerService fileHandlerService, TelephoneNumberValidator telephoneNumberValidator,
                              PhoneSectorService phoneSectorService, PhoneNumberPrefixService phoneNumberPrefixService) {
        this.fileHandlerService = fileHandlerService;
        this.telephoneNumberValidator = telephoneNumberValidator;
        this.phoneSectorService = phoneSectorService;
        this.phoneNumberPrefixService = phoneNumberPrefixService;
    }

    public Map<String, Map<String, Integer>> aggregatePhoneNumbers(List<String> phoneNumbers) throws IOException {

        var validatedNumbers = telephoneNumberValidator.validate(phoneNumbers);

        if (validatedNumbers.isEmpty()) throw new PhoneNumberNotFoundException();

        var prefixes = fileHandlerService.getFiles();

        var response = processPhoneNumbers(validatedNumbers, prefixes);

        return response;
    }

    private Map<String, Map<String, Integer>> processPhoneNumbers(List<String> validatedNumbers, Set<String> prefixes) throws JsonProcessingException {
        Map<String, Map<String, Integer>> response = new HashMap<>();
        for (String phoneNumber: validatedNumbers) {
            SectorResponseDTO sectorResponseDTO = phoneSectorService.getPhoneNumberSector(phoneNumber);
            if (sectorResponseDTO != null) {
                buildResponse(prefixes, response, phoneNumber, sectorResponseDTO);
            }
        }

        return response;
    }

    private void buildResponse(Set<String> prefixes, Map<String, Map<String, Integer>> response, String phoneNumber, SectorResponseDTO sectorResponseDTO) {
        String prefix = phoneNumberPrefixService.getPrefix(phoneNumber, prefixes);
        if (prefix != null) {
            Map<String, Integer> mapResult = response.get(prefix);
            if (mapResult != null) {
                Integer sectorCounter = mapResult.get(sectorResponseDTO.getSector());
                if (sectorCounter == null) sectorCounter = 0;
                mapResult.put(sectorResponseDTO.getSector(), ++sectorCounter);
            } else {
                mapResult = new HashMap<>();
                mapResult.put(sectorResponseDTO.getSector(), 1);
                response.put(prefix, mapResult);
            }
        }
    }
}
