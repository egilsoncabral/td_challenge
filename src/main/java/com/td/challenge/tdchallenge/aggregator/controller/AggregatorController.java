package com.td.challenge.tdchallenge.aggregator.controller;

import com.td.challenge.tdchallenge.aggregator.dto.AggregatorResponseDTO;
import com.td.challenge.tdchallenge.aggregator.service.AggregatorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/aggregator")
public class AggregatorController {

    AggregatorService aggregatorService;

    public AggregatorController(AggregatorService aggregatorService) {
        this.aggregatorService = aggregatorService;
    }

    @ResponseStatus(OK)
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public AggregatorResponseDTO aggregatePhoneNumbers(@RequestBody(required = true) List<String> phoneNumbers) {
        return aggregatorService.aggregatePhoneNumbers(phoneNumbers);
    }
}
