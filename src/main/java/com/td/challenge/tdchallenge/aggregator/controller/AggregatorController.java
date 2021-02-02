package com.td.challenge.tdchallenge.aggregator.controller;

import com.td.challenge.tdchallenge.aggregator.service.AggregatorService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/aggregate")
public class AggregatorController {

    AggregatorService aggregatorService;

    public AggregatorController(AggregatorService aggregatorService) {
        this.aggregatorService = aggregatorService;
    }

    @ResponseStatus(OK)
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public Map<String, Map<String, Integer>> aggregatePhoneNumbers(@RequestBody(required = true) List<String> phoneNumbers) throws IOException {
        return aggregatorService.aggregatePhoneNumbers(phoneNumbers);
    }
}
