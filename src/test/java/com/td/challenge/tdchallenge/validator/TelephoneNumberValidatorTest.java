package com.td.challenge.tdchallenge.validator;

import com.td.challenge.tdchallenge.util.RestApiClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TelephoneNumberValidatorTest {

    @InjectMocks
    private TelephoneNumberValidator telephoneNumberValidator;

    @Test
    void when_validate_with_valid_numbers_then_return_number_list() {
        // Act
        var actual = telephoneNumberValidator.validate(List.of("+1983236248"));

        // Assert
        assertAll(() -> assertNotNull(actual),
                () -> assertFalse(actual.isEmpty()));
    }

    @Test
    void when_validate_with_invalid_numbers_then_return_number_list() {
        // Act
        var actual = telephoneNumberValidator.validate(List.of("+@teste"));

        // Assert
        assertAll(() -> assertNotNull(actual),
                () -> assertTrue(actual.isEmpty()));
    }

}
