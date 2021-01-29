package com.td.challenge.tdchallenge.aggregator;

public class PhoneNumberNotFoundException extends RuntimeException {

    public PhoneNumberNotFoundException() {
        super("No valid phone number found.");
    }
}
