package com.ghanshyam.airlinedcs.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ghanshyam.airlinedcs.dto.AircraftRequestDto;
import com.ghanshyam.airlinedcs.dto.AircraftResponseDto;
import com.ghanshyam.airlinedcs.dto.DeleteResponseDto;
import com.ghanshyam.airlinedcs.service.AircraftService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/aircrafts")
@Validated
public class AircraftController {

	private final AircraftService aircraftService;

	public AircraftController(AircraftService aircraftService) {
		this.aircraftService = aircraftService;
	}

	@PostMapping
	public ResponseEntity<AircraftResponseDto> createAircraft(@Valid @RequestBody AircraftRequestDto dto) {

		AircraftResponseDto response = aircraftService.createAircraft(dto);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/{aircraftId}")
	public ResponseEntity<AircraftResponseDto> getAircraftById(@PathVariable Long aircraftId) {

		AircraftResponseDto response = aircraftService.getAircraftById(aircraftId);

		return ResponseEntity.ok(response);
	}

	@PutMapping("/{aircraftId}")
	public ResponseEntity<AircraftResponseDto> updateAircraft(@PathVariable Long aircraftId,
			@Valid @RequestBody AircraftRequestDto dto) {

		AircraftResponseDto response = aircraftService.updateAircraft(aircraftId, dto);

		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{aircraftId}")
	public ResponseEntity<DeleteResponseDto> deleteAircraft(@PathVariable Long aircraftId) {

		DeleteResponseDto response = aircraftService.deleteAircraft(aircraftId);

		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<List<AircraftResponseDto>> getAllAircraft(@RequestParam Long airlineId) {

		List<AircraftResponseDto> response = aircraftService.getAllAircraft(airlineId);

		return ResponseEntity.ok(response);
	}
}