/**
 * 
 */
package com.td.challenge.tdchallenge.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author egilson 
 * Class used to validate telephone numbers
 */
@Slf4j
@Component
public class TelephoneNumberValidator {


	public List<String> validate(List<String> phoneNumbers) {
		log.info("Validating phone numbers" );
		return formatNumber(phoneNumbers);
	}

	private List<String> formatNumber(List<String> phoneNumbers) {
		return phoneNumbers.stream()
				.filter(Objects::nonNull)
				.filter(s -> !s.isEmpty() && !Pattern.matches(".*[a-zA-Z].*", s))
				.map( s -> s.replaceAll("\\+", "")
						.replaceAll(" ", "")
						.replaceFirst("^0+(?!$)", "" ))
				.collect(Collectors.toList());
	}

}
