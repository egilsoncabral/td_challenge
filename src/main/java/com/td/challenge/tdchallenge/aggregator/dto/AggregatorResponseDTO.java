package com.td.challenge.tdchallenge.aggregator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AggregatorResponseDTO {

    private Map<String, Map<String,Integer>> response;

}
