package com.td.challenge.tdchallenge.aggregator.service;

import com.td.challenge.tdchallenge.aggregator.dto.AggregatorResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AggregatorService {
    public AggregatorResponseDTO aggregatePhoneNumbers(List<String> phoneNumbers) {
        return new AggregatorResponseDTO();
    }
}
