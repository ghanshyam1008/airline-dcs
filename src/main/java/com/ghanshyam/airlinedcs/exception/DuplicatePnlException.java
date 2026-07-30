package com.ghanshyam.airlinedcs.exception;

public class DuplicatePnlException extends RuntimeException{
	
	public DuplicatePnlException() {
		super("Pnl Already exists, please edit the same pnl.");
	}

}
