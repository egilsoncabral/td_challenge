/**
 * 
 */
package com.td.challenge.tdchallenge.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author egilson 
 * Class used to validate telephone numbers
 */
@Slf4j
@Component
public class TelephoneNumberValidator {


	public List<String> validate( List<String> phoneNumbers ) {
		log.info("Validating phone numbers" );
		return checkPhoneHasNoLettersAndReplaceUselessCharacters(phoneNumbers);
	}

	//This function is used to:
	// check null
	// removes invalid phone numbers (phone numbers with letters)
	// remove "+",
	// remove spaces
	// remove "00" in the beginning of the phone number
	private List<String> checkPhoneHasNoLettersAndReplaceUselessCharacters(List<String> phoneNumbers) {
		return phoneNumbers.stream()
				.filter(Objects::nonNull)
				.filter(s -> !Pattern.matches(".*[a-zA-Z].*", s)  )
				.map( s -> s.replaceAll("\\+", "")
						.replaceAll(" ", "")
						.replaceFirst("^0+(?!$)", "" ))
				.collect(Collectors.toList());
	}

}
