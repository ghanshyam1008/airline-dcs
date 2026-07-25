package com.ghanshyam.airlinedcs.exception;

public class FlightNotFoundException extends RuntimeException {

	public FlightNotFoundException(String flightNumber) {

		super("Flight with code '" + flightNumber + "'  not found.");

	}
}
