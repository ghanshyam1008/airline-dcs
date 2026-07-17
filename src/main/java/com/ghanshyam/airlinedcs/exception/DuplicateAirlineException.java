package com.ghanshyam.airlinedcs.exception;

public class DuplicateAirlineException extends RuntimeException {

	public DuplicateAirlineException(String airlineCode) {
		super("Airline with code '" + airlineCode + "' already exists.");

	}

}
