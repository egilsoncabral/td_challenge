package com.td.challenge.tdchallenge.aggregator.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PhoneNumberPrefixService {

    public String getPrefix(String phoneNumber, Set<String> prefixes) {
        StringBuilder number = new StringBuilder(phoneNumber);
        int numberSize = number.length();
        for (int i = 1; i < numberSize; i++) {
            if(prefixes.contains(number.toString())) {
                break;
            }
            number.deleteCharAt(number.length() - 1);
        }
        return number.toString();
    }
}
