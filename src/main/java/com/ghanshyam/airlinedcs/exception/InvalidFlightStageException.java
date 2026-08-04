package com.ghanshyam.airlinedcs.exception;

import com.ghanshyam.airlinedcs.enums.FlightStageStatus;

public class InvalidFlightStageException extends RuntimeException {

	public InvalidFlightStageException(FlightStageStatus expectedStage, FlightStageStatus currentStage) {

		super("Invalid Flight Stage Transition. Expected Stage : " + expectedStage + ", Current Stage : " + currentStage
				+ ".");
	}
}
