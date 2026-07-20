package com.ghanshyam.airlinedcs.exception;

public class DuplicateAircraftException extends RuntimeException {
	public DuplicateAircraftException(String aircraftNumber) {
		super("Aircraft with '" + aircraftNumber + "' already exists.");

	}

}
