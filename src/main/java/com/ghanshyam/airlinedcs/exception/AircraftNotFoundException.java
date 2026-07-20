package com.ghanshyam.airlinedcs.exception;


public class AircraftNotFoundException extends RuntimeException {
	public AircraftNotFoundException(Long aircraftId) {
		super("Aircraft with '" +aircraftId+ "' not exist");
		
	}

}
