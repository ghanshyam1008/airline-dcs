package com.ghanshyam.airlinedcs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ghanshyam.airlinedcs.dto.AirlineRequestDto;
import com.ghanshyam.airlinedcs.dto.AirlineResponseDto;
import com.ghanshyam.airlinedcs.dto.DeleteResponseDto;
import com.ghanshyam.airlinedcs.service.AirlineService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/airlines")
public class AirlineController {
	private final AirlineService airlineService;

	@Autowired
	public AirlineController(AirlineService airlineService) {
		this.airlineService = airlineService;

	}

	@PostMapping
	public ResponseEntity<AirlineResponseDto> createAirline(@Valid @RequestBody AirlineRequestDto dto) {
		// Call service
		AirlineResponseDto responseDto = airlineService.createAirline(dto);
		// Return ResponseEntity
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
	}

	@GetMapping("/{airlineId}")
	public ResponseEntity<AirlineResponseDto> getAirlineById(@PathVariable Long airlineId) {
		// Call service
		AirlineResponseDto responseDto = airlineService.getAirlineById(airlineId);
		// Return ResponseEntity
		return ResponseEntity.ok(responseDto);
	}

	@PutMapping("/{airlineId}")
	public ResponseEntity<AirlineResponseDto> updateAirline(@ PathVariable Long airlineId, @Valid @ RequestBody AirlineRequestDto dto) {
		// Call service
		AirlineResponseDto responseDto = airlineService.updateAirline(airlineId, dto);
		// Return ResponseEntity
		return ResponseEntity.ok(responseDto);
	}

	@DeleteMapping("/{airlineId}")
	public ResponseEntity<DeleteResponseDto> deleteAirline(@ PathVariable Long airlineId) {
		// Call service
		DeleteResponseDto responseDto = airlineService.deleteAirline(airlineId);
		// Return ResponseEntity
		return ResponseEntity.ok(responseDto);
	}

	@GetMapping
	public ResponseEntity<List<AirlineResponseDto>> getAllAirlines(@RequestParam Long airportId) {
		// Call service
		List<AirlineResponseDto> responseDtos = airlineService.getAllAirlines(airportId);
		// Return ResponseEntity
		return ResponseEntity.ok(responseDtos);
	}

}
