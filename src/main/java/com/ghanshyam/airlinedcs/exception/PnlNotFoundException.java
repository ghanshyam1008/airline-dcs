package com.ghanshyam.airlinedcs.exception;

public class PnlNotFoundException extends RuntimeException {

	public PnlNotFoundException() {
		super("Pnl Does not exist");
	}

	public PnlNotFoundException(Long PnlId) {
		super("Pnl Does not exist" + PnlId);
	}

}
