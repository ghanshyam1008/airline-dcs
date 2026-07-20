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

	@ExceptionHandler(AirportNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleAirportNotFoundException(AirportNotFoundException ex) {

		ErrorResponseDto response = new ErrorResponseDto();

		response.setMessage(ex.getMessage());
		response.setStatus(HttpStatus.NOT_FOUND.value());
		response.setTimestamp(LocalDateTime.now());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	@ExceptionHandler(DuplicateAirportException.class)
	public ResponseEntity<ErrorResponseDto> handleDuplicateAirportException(DuplicateAirportException ex) {

		ErrorResponseDto response = new ErrorResponseDto();
		response.setMessage(ex.getMessage());
		response.setStatus(HttpStatus.CONFLICT.value());
		response.setTimestamp(LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(response);

	}

	@ExceptionHandler(DuplicateAirlineException.class)
	public ResponseEntity<ErrorResponseDto> handleDuplicateAirlineException(DuplicateAirlineException ex) {

		ErrorResponseDto response = new ErrorResponseDto();
		response.setMessage(ex.getMessage());
		response.setStatus(HttpStatus.CONFLICT.value());
		response.setTimestamp(LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(response);

	}

	@ExceptionHandler(AirlineNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleAirlineNotFoundException(AirlineNotFoundException ex) {

		ErrorResponseDto response = new ErrorResponseDto();
		response.setMessage(ex.getMessage());
		response.setStatus(HttpStatus.NOT_FOUND.value());
		response.setTimestamp(LocalDateTime.now());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	@ExceptionHandler(AircraftNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleAircraftNotFoundException(AircraftNotFoundException ex) {

		ErrorResponseDto response = new ErrorResponseDto();

		response.setMessage(ex.getMessage());
		response.setStatus(HttpStatus.NOT_FOUND.value());
		response.setTimestamp(LocalDateTime.now());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	@ExceptionHandler(DuplicateAircraftException.class)
	public ResponseEntity<ErrorResponseDto> handleDuplicateAircraftException(DuplicateAircraftException ex) {

		ErrorResponseDto response = new ErrorResponseDto();

		response.setMessage(ex.getMessage());
		response.setStatus(HttpStatus.CONFLICT.value());
		response.setTimestamp(LocalDateTime.now());

		return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {

		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errors.put(error.getField(), error.getDefaultMessage());
		});

		return ResponseEntity.badRequest().body(errors);

	}

}
