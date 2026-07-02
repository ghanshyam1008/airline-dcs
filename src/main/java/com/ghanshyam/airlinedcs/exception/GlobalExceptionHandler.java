package com.ghanshyam.airlinedcs.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AirportNotFoundException.class)
	public ResponseEntity<String> handleAirportNotFoundException(AirportNotFoundException ex) {

		return ResponseEntity.status(404).body(ex.getMessage());
	}
	
	@ExceptionHandler(DuplicateAirportException.class)
	public ResponseEntity<String> DuplicateAirportException(DuplicateAirportException ex) {

		return ResponseEntity.status(409).body(ex.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(
    MethodArgumentNotValidException ex) {

  
    Map<String, String> errors = new HashMap<>();

    ex.getBindingResult().getFieldErrors().forEach(error -> {
        errors.put(error.getField(), error.getDefaultMessage());
    });

    return ResponseEntity.badRequest().body(errors);
    

    }

}
