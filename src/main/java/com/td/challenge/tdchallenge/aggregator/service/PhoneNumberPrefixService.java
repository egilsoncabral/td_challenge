package com.td.challenge.tdchallenge.aggregator.service;

import com.td.challenge.tdchallenge.util.FileHandlerSingleton;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class PhoneNumberPrefixService {

    private List<String> prefixes;

    public String getPrefix(String phoneNumber, List<String> prefixes) {
        StringBuilder number = new StringBuilder(phoneNumber);
        int numberSize = number.length();
        for (int i = 1; i < numberSize; i++) {
            if(prefixes.contains(number)) {
                break;
            }
            number.deleteCharAt(number.length() - 1);
        }
        return number.toString();
    }
}
