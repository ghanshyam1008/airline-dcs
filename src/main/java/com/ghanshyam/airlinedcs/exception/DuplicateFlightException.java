package com.ghanshyam.airlinedcs.exception;

public class DuplicateFlightException extends RuntimeException {
	
	public DuplicateFlightException(String flightCode) {
	    super("Flight with code '" + flightCode + "' already exists.");
	}

}
