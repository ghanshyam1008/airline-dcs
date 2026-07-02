package com.ghanshyam.airlinedcs.exception;

public class DuplicateAirportException extends RuntimeException{
	
	public DuplicateAirportException(String airportCode) {
	    super("Airport with code '" + airportCode + "' already exists.");
	}
}
