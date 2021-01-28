package com.td.challenge.tdchallenge.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class RestApiClient {

    private RestTemplate restTemplate;

    public RestApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T> ResponseEntity<T> consumeAPI(String url) {
        log.info("Consuming API in endpoint: " + url);
        return (ResponseEntity<T>) restTemplate.getForEntity(url, String.class);
    }

}
