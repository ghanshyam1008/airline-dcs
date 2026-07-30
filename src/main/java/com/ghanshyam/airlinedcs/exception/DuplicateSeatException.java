package com.ghanshyam.airlinedcs.exception;

public class DuplicateSeatException extends RuntimeException {
	public DuplicateSeatException(String Passenger) {
		super("the seat no  is same for two Passenegrs.");
	}

}
