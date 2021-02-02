package com.td.challenge.tdchallenge.configuration;

import com.td.challenge.tdchallenge.aggregator.PhoneNumberNotFoundException;
import com.td.challenge.tdchallenge.aggregator.controller.AggregatorControllerAdvice;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationControllerAdviceTest {

    @Test
    void runtimeExceptionHandler() {
        assertEquals("Unexpected error!",
                new ApplicationControllerAdvice().runtimeExceptionHandler(new RuntimeException("Unexpected error!")));
    }

    @Test
    void illegalArgumentExceptionHandler() {
        assertEquals("Invalid phone number",
                new ApplicationControllerAdvice().illegalArgumentExceptionHandler(new IllegalArgumentException("Invalid phone number")));
    }

    @Test
    void phoneNumberNotFoundExceptionHandler() {
        assertEquals("No valid phone number found.",
                new AggregatorControllerAdvice().phoneNumberNotFoundExceptionHandler(new PhoneNumberNotFoundException()));
    }

}