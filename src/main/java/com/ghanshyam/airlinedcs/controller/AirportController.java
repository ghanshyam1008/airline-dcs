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

import com.ghanshyam.airlinedcs.dto.AirportRequestDto;
import com.ghanshyam.airlinedcs.dto.AirportResponseDto;
import com.ghanshyam.airlinedcs.dto.DeleteResponseDto;
import com.ghanshyam.airlinedcs.service.AirportService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/airports")
public class AirportController {
	private final AirportService airportService;

	public AirportController(AirportService airportService) {
		this.airportService = airportService;
	}

	@PostMapping
	public ResponseEntity<AirportResponseDto> createAirport(@Valid @RequestBody AirportRequestDto dto) {

		AirportResponseDto responseDto = airportService.createAirport(dto);

		return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AirportResponseDto> getAirportById(@PathVariable Long id) {
		AirportResponseDto responseDto = airportService.getAirportById(id);

		return ResponseEntity.ok(responseDto);
	}

	@GetMapping
	public ResponseEntity<List<AirportResponseDto>> getAllAirports() {

		List<AirportResponseDto> responseDtos = airportService.getAllAirports();

		return ResponseEntity.ok(responseDtos);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<DeleteResponseDto> deleteAirport(@PathVariable Long id) {

		DeleteResponseDto responseDto = airportService.deleteAirport(id);

		return ResponseEntity.ok(responseDto);

	}

	@PutMapping("/{id}")
	public ResponseEntity<AirportResponseDto> updateAirport(@PathVariable Long id, @RequestBody AirportRequestDto dto) {

		AirportResponseDto responseDto = airportService.updateAirport(id, dto);

		return ResponseEntity.status(HttpStatus.OK).body(responseDto);

	}

}