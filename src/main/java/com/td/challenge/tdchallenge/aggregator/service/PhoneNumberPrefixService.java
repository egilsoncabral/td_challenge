package com.td.challenge.tdchallenge.aggregator.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PhoneNumberPrefixService {

    public String getPrefix(String phoneNumber, Set<String> prefixes) {
        phoneNumber = validatePhoneNumber(phoneNumber);
        StringBuilder number = new StringBuilder(phoneNumber);
        int numberSize = number.length();
        if (prefixes != null) {
            for (int i = 0; i < numberSize; i++) {
                if(prefixes.contains(number.toString())) {
                    break;
                }
                number.deleteCharAt(number.length() - 1);
            }
        }
        return number.toString();
    }

    private String validatePhoneNumber(String phoneNumber) {
        return phoneNumber != null ? phoneNumber.replaceAll("\\+", "") : new String ();
    }
}
