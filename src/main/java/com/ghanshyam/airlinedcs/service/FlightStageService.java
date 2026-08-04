package com.ghanshyam.airlinedcs.service;

import java.time.LocalDate;

import com.ghanshyam.airlinedcs.dto.FlightStageResponseDto;

public interface FlightStageService {

	FlightStageResponseDto searchFlightStage(String flightNumber, LocalDate flightDate);

	FlightStageResponseDto initializeFlight(String flightNumber, LocalDate flightDate);

	FlightStageResponseDto uninitializeFlight(String flightNumber, LocalDate flightDate);

	FlightStageResponseDto initialClose(String flightNumber, LocalDate flightDate);

	FlightStageResponseDto reopenInitialClose(String flightNumber, LocalDate flightDate);

	FlightStageResponseDto finalClose(String flightNumber, LocalDate flightDate);

	FlightStageResponseDto reopenFinalClose(String flightNumber, LocalDate flightDate);

	FlightStageResponseDto finalRelease(String flightNumber, LocalDate flightDate);

	FlightStageResponseDto cancelFlight(String flightNumber, LocalDate flightDate, String cancellationReason);

	FlightStageResponseDto sendDepartureMessage(String flightNumber, LocalDate flightDate, String recipientEmail,
			String messageBody);

	FlightStageResponseDto completeReopenInitialClose(String flightNumber, LocalDate flightDate);

	FlightStageResponseDto completeReopenFinalClose(String flightNumber, LocalDate flightDate);
}