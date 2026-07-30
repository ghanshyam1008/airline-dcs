package com.ghanshyam.airlinedcs.exception;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class FlightScheduleNotAvailableException extends RuntimeException {
	public FlightScheduleNotAvailableException() {
		super("Flight is not available for selected date");
	}

	public FlightScheduleNotAvailableException(DayOfWeek dayOfWeek) {

		super("Flight is not scheduled on " + dayOfWeek);
	}

}
