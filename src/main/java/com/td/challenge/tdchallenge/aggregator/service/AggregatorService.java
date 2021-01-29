package com.td.challenge.tdchallenge.aggregator.service;

import com.td.challenge.tdchallenge.sector.dto.SectorResponseDTO;
import com.td.challenge.tdchallenge.sector.service.PhoneSectorService;
import com.td.challenge.tdchallenge.validator.TelephoneNumberValidator;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.jar.JarEntry;

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

        Map<String, Map<String, Integer>> response = new HashMap<>();
        List<String> validatedNumbers = telephoneNumberValidator.validate(phoneNumbers);

        Set<String> prefixes = fileHandlerService.getFiles();

        for (String phoneNumber: validatedNumbers) {
            SectorResponseDTO sectorResponseDTO = phoneSectorService.getPhoneNumberSector(phoneNumber);
            if (sectorResponseDTO != null) {
                String prefix = phoneNumberPrefixService.getPrefix(phoneNumber, prefixes);
                if (prefix != null) {
                    Map<String, Integer> mapResult = response.get(prefix);
                    if (mapResult != null) {
                        Integer count = mapResult.get(sectorResponseDTO.getSector());
                        if (count == null) count = 0;
                        mapResult.put(sectorResponseDTO.getSector(), count + 1);
                    } else {
                        mapResult = new HashMap<>();
                        mapResult.put(sectorResponseDTO.getSector(), 1);
                        response.put(prefix, mapResult);
                    }
                }
            }
        }
        return response;
    }
}
