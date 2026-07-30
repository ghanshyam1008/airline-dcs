package com.ghanshyam.airlinedcs.exception;

public class DuplicatePnrException extends RuntimeException {
	public DuplicatePnrException(String Passenger) {
		super("The PNR no is same for two Passengers.");
	}

}
