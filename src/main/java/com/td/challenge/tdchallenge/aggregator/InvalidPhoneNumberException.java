package com.td.challenge.tdchallenge.aggregator;

public class InvalidPhoneNumberException extends RuntimeException {

    public InvalidPhoneNumberException (String phoneNumber) {
        super("Telephone number is invalid: " + phoneNumber);
    }
}
