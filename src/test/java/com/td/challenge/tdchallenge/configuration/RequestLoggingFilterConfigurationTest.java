package com.td.challenge.tdchallenge.configuration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RequestLoggingFilterConfigurationTest {

    @Test
    void logFilter() {
        assertNotNull(new RequestLoggingFilterConfiguration().logFilter());
    }

}
