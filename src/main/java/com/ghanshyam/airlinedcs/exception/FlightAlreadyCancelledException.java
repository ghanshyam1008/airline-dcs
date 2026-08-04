package com.ghanshyam.airlinedcs.exception;

public class FlightAlreadyCancelledException extends RuntimeException {

	public FlightAlreadyCancelledException() {
		super("The flight has already cancelled");
	}

}
