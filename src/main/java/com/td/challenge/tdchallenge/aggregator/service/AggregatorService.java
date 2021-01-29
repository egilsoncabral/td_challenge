package com.td.challenge.tdchallenge.aggregator.service;

import com.td.challenge.tdchallenge.sector.dto.SectorResponseDTO;
import com.td.challenge.tdchallenge.sector.service.PhoneSectorService;
import com.td.challenge.tdchallenge.util.FileHandlerSingleton;
import com.td.challenge.tdchallenge.validator.TelephoneNumberValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AggregatorService {

    private FileHandlerSingleton fileHandler;

    private TelephoneNumberValidator telephoneNumberValidator;

    private PhoneSectorService phoneSectorService;

    private PhoneNumberPrefixService phoneNumberPrefixService;

    @Value("${file.prefix.name}")
    private String filePath;

    public AggregatorService (FileHandlerSingleton fileHandler, TelephoneNumberValidator telephoneNumberValidator,
                              PhoneSectorService phoneSectorService, PhoneNumberPrefixService phoneNumberPrefixService) {
        this.fileHandler = fileHandler;
        this.telephoneNumberValidator = telephoneNumberValidator;
        this.phoneSectorService = phoneSectorService;
        this.phoneNumberPrefixService = phoneNumberPrefixService;
    }

    public Map<String, Map<String, Integer>> aggregatePhoneNumbers(List<String> phoneNumbers) throws IOException, URISyntaxException {
        List<String> prefixes = fileHandler.readFile(filePath);
        Map<String, Map<String, Integer>> response = new HashMap<>();
        List<String> validatedNumbers = telephoneNumberValidator.validate(phoneNumbers);
        for (String phoneNumber: validatedNumbers) {
            SectorResponseDTO sectorResponseDTO = phoneSectorService.getPhoneNumberSector(phoneNumber);
            if(null != sectorResponseDTO) {
                String prefix = phoneNumberPrefixService.getPrefix(phoneNumber, prefixes);
            }
        }
        return response;
    }

}
