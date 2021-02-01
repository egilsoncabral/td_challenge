package com.td.challenge.tdchallenge.aggregator.controller;

import com.td.challenge.tdchallenge.aggregator.service.AggregatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AggregatorControllerTest {

    @InjectMocks
    private AggregatorController aggregatorController;
    @Mock
    AggregatorService aggregatorService;

    @Test
    void aggregatePhoneNumbersTest() throws IOException {
        // Arrange
        when(aggregatorService.aggregatePhoneNumbers(any()))
                .thenReturn(Map.of("1", Map.of("Technology", 1)));

        // Act
        var actual = aggregatorController.aggregatePhoneNumbers(List.of("+1983236248"));

        // Assert
        assertAll(() -> assertNotNull(actual),
                () -> assertFalse(actual.isEmpty()));
    }
}
