package com.ghanshyam.airlinedcs.exception;

public class PnlNotLoadedException extends RuntimeException {

	public PnlNotLoadedException() {
		super("PNL is not loaded. Please load the PNL before initializing the flight");
	}

}
