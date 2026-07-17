package com.ghanshyam.airlinedcs.exception;

public class AirlineNotFoundException extends RuntimeException {

	public AirlineNotFoundException(Long airlineId) {
		 super("Airline not found with id : " + airlineId);
	}

}
