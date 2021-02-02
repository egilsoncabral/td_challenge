package com.td.challenge.tdchallenge.aggregator.controller;

import com.td.challenge.tdchallenge.aggregator.PhoneNumberNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class AggregatorControllerAdvice {

    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(PhoneNumberNotFoundException.class)
    public String phoneNumberNotFoundExceptionHandler(PhoneNumberNotFoundException e) {
        return e.getMessage();
    }
}
