package com.ghanshyam.airlinedcs.exception;

public class FlightAlreadyFinalReleasedException extends RuntimeException{
	
	public FlightAlreadyFinalReleasedException() {
		super("The flight has already released");
	}

}
