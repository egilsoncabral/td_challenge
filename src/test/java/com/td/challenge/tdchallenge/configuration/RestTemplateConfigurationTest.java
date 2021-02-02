package com.td.challenge.tdchallenge.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RestTemplateConfigurationTest {

    @Test
    void restTemplate() {
        assertNotNull(new RestTemplateConfiguration().restTemplate());
    }
}
