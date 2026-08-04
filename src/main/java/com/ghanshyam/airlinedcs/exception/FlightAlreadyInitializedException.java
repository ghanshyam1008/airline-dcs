package com.ghanshyam.airlinedcs.exception;

import java.time.LocalDate;

import com.ghanshyam.airlinedcs.entity.Flight;

public class FlightAlreadyInitializedException extends RuntimeException {

	public FlightAlreadyInitializedException(String flightNumber, LocalDate flightDate) {
		super("Flight " + flightNumber + " has already been initialized for " + flightDate + ".");
	}

}
