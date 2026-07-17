package com.ghanshyam.airlinedcs.exception;

public class AirportNotFoundException extends RuntimeException {

	public AirportNotFoundException(Long Id) {
		super("Airport not found with id: " + Id);
	}
}