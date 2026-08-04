package com.ghanshyam.airlinedcs.exception;

import java.time.LocalDate;

import com.ghanshyam.airlinedcs.entity.Flight;

public class FlightStageNotFoundException extends RuntimeException {

	public FlightStageNotFoundException(String flightNumber, LocalDate flightDate) {

		super("Flight Stage not found for Flight " + flightNumber + " on " + flightDate + ".");
	}
}