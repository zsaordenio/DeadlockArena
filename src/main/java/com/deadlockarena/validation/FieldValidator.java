package com.deadlockarena.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FieldValidator {

	private Pattern pattern;

	private static final String EMAIL_PATTERN = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
	// https://stackoverflow.com/questions/12018245/regular-expression-to-validate-username
	private static final String USERNAME_PATTERN = "^(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";
	// https://www.mkyong.com/regular-expressions/how-to-validate-password-with-regular-expression/
	private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*?&]).{6,20}$";
												
	private static final String NAME_PATTERN = "^[A-Z ,.'-]+$";

	/**
	 * Validate hex with regular expression
	 * 
	 * @param hex - hex for validation
	 * @return true for valid hex, false for invalid hex
	 */
	
	public boolean isValidEmail(final String input){
		this.pattern = Pattern.compile(EMAIL_PATTERN);
		return pattern.matcher(input).matches();
	}
	
	public boolean isValidUsername(final String input){
		this.pattern = Pattern.compile(USERNAME_PATTERN);
		return pattern.matcher(input).matches();
	}
	
	public boolean isValidPassword(final String input){
		this.pattern = Pattern.compile(PASSWORD_PATTERN);
		return pattern.matcher(input).matches();
	}
	
	public boolean isValidName(final String input){
		this.pattern = Pattern.compile(NAME_PATTERN);
		return pattern.matcher(input).matches();
	}
	
}