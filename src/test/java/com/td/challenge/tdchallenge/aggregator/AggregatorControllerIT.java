package com.td.challenge.tdchallenge.aggregator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.td.challenge.tdchallenge.aggregator.controller.AggregatorController;
import com.td.challenge.tdchallenge.aggregator.service.AggregatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AggregatorController.class)
public class AggregatorControllerIT {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private AggregatorService aggregatorService;

    @Test
    public void givenTelephoneList_whenInvokeAggregatePhoneNumbers_thenReturnSectorWithCounters() throws Exception {
        given(aggregatorService.aggregatePhoneNumbers(any()))
                .willReturn(prepareResponse());

        mvc.perform(post("/aggregate")
                .content(asJsonString(new String[] {"+1983236248", "+1 7490276403", "001382355A", "+351917382672", "+35191734022"}))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.1").exists());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, Map<String, Integer>> prepareResponse() {
        Map<String, Map<String, Integer>> response = new HashMap<>();

        HashMap<String, Integer> firstNumberSector = new HashMap<>();
        firstNumberSector.put("Technology", 1);
        firstNumberSector.put("Banking", 1);
        response.put("1", firstNumberSector);

        HashMap<String, Integer> secondNumberSector = new HashMap<>();
        secondNumberSector.put("Clothing", 2);
        response.put("3159173", secondNumberSector);

        return response;
    }
}
