package com.td.challenge.tdchallenge.aggregator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class FileHandleServiceTest {

    @InjectMocks
    private FileHandlerService fileHandlerService;

    @BeforeEach
    void setUp() {
        fileHandlerService.setFilePath("/prefixes.txt");
        fileHandlerService.loadFile();
    }

    @Test
    void when_loadFile_then_return_prefixes_list() {

        // Act
        var actual = fileHandlerService.getFiles();

        // Assert
        assertAll(() -> assertNotNull(actual),
                () -> assertFalse(actual.isEmpty()));
    }


}
