package com.ghanshyam.airlinedcs.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ghanshyam.airlinedcs.dto.ErrorResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler {

	private ResponseEntity<ErrorResponseDto> buildErrorResponse(String message, HttpStatus status) {

		ErrorResponseDto error = new ErrorResponseDto();
		error.setMessage(message);
		error.setStatus(status.value());
		error.setTimestamp(LocalDateTime.now());

		return ResponseEntity.status(status).body(error);
	}

	@ExceptionHandler(AirportNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleAirportNotFoundException(AirportNotFoundException ex) {

		return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DuplicateAirportException.class)
	public ResponseEntity<ErrorResponseDto> handleDuplicateAirportException(DuplicateAirportException ex) {

		return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(DuplicateAirlineException.class)
	public ResponseEntity<ErrorResponseDto> handleDuplicateAirlineException(DuplicateAirlineException ex) {

		return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(AirlineNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleAirlineNotFoundException(AirlineNotFoundException ex) {

		return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(AircraftNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleAircraftNotFoundException(AircraftNotFoundException ex) {

		return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DuplicateAircraftException.class)
	public ResponseEntity<ErrorResponseDto> handleDuplicateAircraftException(DuplicateAircraftException ex) {

		return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {

		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errors.put(error.getField(), error.getDefaultMessage());
		});

		return ResponseEntity.badRequest().body(errors);

	}

	@ExceptionHandler(FlightNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleFlightNotFoundException(FlightNotFoundException ex) {
		return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DuplicateFlightException.class)
	public ResponseEntity<ErrorResponseDto> handleDuplicateFlightException(DuplicateFlightException ex) {

		return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponseDto> handleIllegalArgumentException(IllegalArgumentException ex) {

		return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DuplicatePnlException.class)
	public ResponseEntity<ErrorResponseDto> handleDuplicatePnlException(DuplicatePnlException ex) {
		return buildErrorResponse(ex.getMessage(), HttpStatus.CONFLICT);
	}

	@ExceptionHandler(PnlNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handlePnlNotFoundException(PnlNotFoundException ex) {
		return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(PnlAlreadyLoadedException.class)
	public ResponseEntity<ErrorResponseDto> handlePnlAlreadyLoadedException(PnlAlreadyLoadedException ex) {
		return buildErrorResponse(ex.getMessage(), HttpStatus.CONFLICT);
	}

	@ExceptionHandler(DuplicatePnrException.class)
	public ResponseEntity<ErrorResponseDto> handleDuplicatePnrException(DuplicatePnrException ex) {
		return buildErrorResponse(ex.getMessage(), HttpStatus.CONFLICT);
	}

	@ExceptionHandler(DuplicateSeatException.class)
	public ResponseEntity<ErrorResponseDto> handleDuplicateSeatException(DuplicateSeatException ex) {
		return buildErrorResponse(ex.getMessage(), HttpStatus.CONFLICT);
	}

	@ExceptionHandler(PassengerCountExceededException.class)
	public ResponseEntity<ErrorResponseDto> handlePassengerCountExceededException(PassengerCountExceededException ex) {
		return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(FlightScheduleNotAvailableException.class)
	public ResponseEntity<ErrorResponseDto> handleFlightScheduleNotAvailableException(
			FlightScheduleNotAvailableException ex) {

		return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

}
