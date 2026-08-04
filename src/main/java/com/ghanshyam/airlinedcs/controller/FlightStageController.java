package com.ghanshyam.airlinedcs.controller;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ghanshyam.airlinedcs.dto.FlightStageResponseDto;
import com.ghanshyam.airlinedcs.service.FlightStageService;

@RestController
@RequestMapping("/api/flight-stage")
public class FlightStageController {

	private final FlightStageService flightStageService;

	public FlightStageController(FlightStageService flightStageService) {
		this.flightStageService = flightStageService;
	}

	/*
	 * Searches the Flight Stage for the selected Flight and Date.
	 */
	@GetMapping("/search")
	public ResponseEntity<FlightStageResponseDto> searchFlightStage(@RequestParam String flightNumber,
			@RequestParam LocalDate flightDate) {

		FlightStageResponseDto response = flightStageService.searchFlightStage(flightNumber, flightDate);

		return ResponseEntity.ok(response);
	}

	/*
	 * Initializes the Flight.
	 */
	@PostMapping("/initialize")
	public ResponseEntity<FlightStageResponseDto> initializeFlight(@RequestParam String flightNumber,
			@RequestParam LocalDate flightDate) {

		FlightStageResponseDto response = flightStageService.initializeFlight(flightNumber, flightDate);

		return ResponseEntity.ok(response);
	}

	/*
	 * Uninitializes the Flight.
	 */
	@DeleteMapping("/uninitialize")
	public ResponseEntity<FlightStageResponseDto> uninitializeFlight(@RequestParam String flightNumber,
			@RequestParam LocalDate flightDate) {

		FlightStageResponseDto response = flightStageService.uninitializeFlight(flightNumber, flightDate);

		return ResponseEntity.ok(response);
	}

	/*
	 * Performs Initial Close.
	 */
	@PutMapping("/initial-close")
	public ResponseEntity<FlightStageResponseDto> initialClose(@RequestParam String flightNumber,
			@RequestParam LocalDate flightDate) {

		FlightStageResponseDto response = flightStageService.initialClose(flightNumber, flightDate);

		return ResponseEntity.ok(response);
	}

	/*
	 * Reopens Initial Close.
	 */
	@PutMapping("/reopen-initial-close")
	public ResponseEntity<FlightStageResponseDto> reopenInitialClose(@RequestParam String flightNumber,
			@RequestParam LocalDate flightDate) {

		FlightStageResponseDto response = flightStageService.reopenInitialClose(flightNumber, flightDate);

		return ResponseEntity.ok(response);
	}

	/*
	 * Performs Final Close.
	 */
	@PutMapping("/final-close")
	public ResponseEntity<FlightStageResponseDto> finalClose(@RequestParam String flightNumber,
			@RequestParam LocalDate flightDate) {

		FlightStageResponseDto response = flightStageService.finalClose(flightNumber, flightDate);

		return ResponseEntity.ok(response);
	}

	/*
	 * Reopens Final Close.
	 */
	@PutMapping("/reopen-final-close")
	public ResponseEntity<FlightStageResponseDto> reopenFinalClose(@RequestParam String flightNumber,
			@RequestParam LocalDate flightDate) {

		FlightStageResponseDto response = flightStageService.reopenFinalClose(flightNumber, flightDate);

		return ResponseEntity.ok(response);
	}

	/*
	 * Marks the Flight as Finally Released.
	 */
	@PutMapping("/final-release")
	public ResponseEntity<FlightStageResponseDto> finalRelease(@RequestParam String flightNumber,
			@RequestParam LocalDate flightDate) {

		FlightStageResponseDto response = flightStageService.finalRelease(flightNumber, flightDate);

		return ResponseEntity.ok(response);
	}

	/*
	 * Cancels the Flight.
	 */
	@PutMapping("/cancel")
	public ResponseEntity<FlightStageResponseDto> cancelFlight(@RequestParam String flightNumber,
			@RequestParam LocalDate flightDate, @RequestParam String cancellationReason) {

		FlightStageResponseDto response = flightStageService.cancelFlight(flightNumber, flightDate, cancellationReason);

		return ResponseEntity.ok(response);
	}

	/*
	 * Sends the Departure Message.
	 */
	@PostMapping("/send-message")
	public ResponseEntity<FlightStageResponseDto> sendDepartureMessage(@RequestParam String flightNumber,
			@RequestParam LocalDate flightDate, @RequestParam String recipientEmail, @RequestParam String messageBody) {

		FlightStageResponseDto response = flightStageService.sendDepartureMessage(flightNumber, flightDate,
				recipientEmail, messageBody);

		return ResponseEntity.ok(response);
	}

	@PutMapping("/complete-reopen-initial-close")
	public ResponseEntity<FlightStageResponseDto> completeReopenInitialClose(@RequestParam String flightNumber,
			@RequestParam LocalDate flightDate) {

		FlightStageResponseDto response = flightStageService.completeReopenInitialClose(flightNumber, flightDate);

		return ResponseEntity.ok(response);
	}

	@PutMapping("/complete-reopen-final-close")
	public ResponseEntity<FlightStageResponseDto> completeReopenFinalClose(@RequestParam String flightNumber,
			@RequestParam LocalDate flightDate) {

		FlightStageResponseDto response = flightStageService.completeReopenFinalClose(flightNumber, flightDate);

		return ResponseEntity.ok(response);
	}
}
