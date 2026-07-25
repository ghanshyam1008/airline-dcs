package com.ghanshyam.airlinedcs.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ghanshyam.airlinedcs.dto.DeleteResponseDto;
import com.ghanshyam.airlinedcs.dto.FlightRequestDto;
import com.ghanshyam.airlinedcs.dto.FlightResponseDto;
import com.ghanshyam.airlinedcs.service.FlightService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

	private final FlightService flightService;

	public FlightController(FlightService flightService) {
		this.flightService = flightService;
	}

	@PostMapping
	public ResponseEntity<FlightResponseDto> createFlight(@Valid @RequestBody FlightRequestDto dto) {

		FlightResponseDto responseDto = flightService.createFlight(dto);

		return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
	}

	@PutMapping("/{flightNumber}")
	public ResponseEntity<FlightResponseDto> updateFlight(@PathVariable String flightNumber,
			@Valid @RequestBody FlightRequestDto dto) {

		FlightResponseDto responseDto = flightService.updateFlight(flightNumber, dto);

		return ResponseEntity.ok(responseDto);
	}

	@GetMapping("/{flightNumber}")
	public ResponseEntity<FlightResponseDto> getFlightByFlightNumber(@PathVariable String flightNumber) {

		FlightResponseDto responseDto = flightService.getFlightByFlightNumber(flightNumber);

		return ResponseEntity.ok(responseDto);
	}

	@GetMapping
	public ResponseEntity<List<FlightResponseDto>> getAllFlights() {

		List<FlightResponseDto> responseDto = flightService.getAllFlights();

		return ResponseEntity.ok(responseDto);
	}

	@DeleteMapping("/{flightNumber}")
	public ResponseEntity<DeleteResponseDto> deleteFlight(@PathVariable String flightNumber) {

		DeleteResponseDto responseDto = flightService.deleteFlight(flightNumber);

		return ResponseEntity.ok(responseDto);
	}

}