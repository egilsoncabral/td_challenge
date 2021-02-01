package com.td.challenge.tdchallenge.aggregator.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PhoneNumberPrefixServiceTest {

    @InjectMocks
    private PhoneNumberPrefixService phoneNumberPrefixService;

    @Test
    void getPrefix_with_invalid_number_return_empty_String() {
        // Act
        var actual = phoneNumberPrefixService.getPrefix("+92721839", Set.of("1"));

        // Assert
        assertAll(() -> assertNotNull(actual),
                () -> assertTrue(actual.isEmpty()));
    }

    @Test
    void getPrefix_with_valid_number_return_prefix() {
        // Act
        var actual = phoneNumberPrefixService.getPrefix("+12721839", Set.of("1"));

        // Assert
        assertAll(() -> assertNotNull(actual),
                () -> assertFalse(actual.isEmpty()));
    }

    @Test
    void getPrefix_with_null_return_empty_String() {
        // Act
        var actual = phoneNumberPrefixService.getPrefix(null, Set.of("1"));

        // Assert
        assertAll(() -> assertNotNull(actual),
                () -> assertTrue(actual.isEmpty()));
    }
}
