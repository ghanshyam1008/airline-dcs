package com.ghanshyam.airlinedcs.exception;

public class PnlAlreadyLoadedException extends RuntimeException {
	
	public PnlAlreadyLoadedException() {
		super("PNl is laready loaded");
	}

}
