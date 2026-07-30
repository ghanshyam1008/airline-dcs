package com.ghanshyam.airlinedcs.exception;

public class PassengerCountExceededException extends RuntimeException {
	public PassengerCountExceededException() {
	super("The No of passenger count is more then the total seat");
	}
}
