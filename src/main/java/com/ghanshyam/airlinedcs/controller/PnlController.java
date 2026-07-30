package com.ghanshyam.airlinedcs.controller;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ghanshyam.airlinedcs.dto.PnlRequestDto;
import com.ghanshyam.airlinedcs.service.PnlService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pnls")
public class PnlController {

	private final PnlService pnlService;

	public PnlController(PnlService pnlService) {
		this.pnlService = pnlService;
	}

	@GetMapping("/search")
	public ResponseEntity<PnlRequestDto> searchPnl(@RequestParam String flightNumber,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate flightDate) {

		PnlRequestDto response = pnlService.searchPnl(flightNumber, flightDate);

		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<PnlRequestDto> createPnl(@Valid @RequestBody PnlRequestDto dto) {

		PnlRequestDto response = pnlService.createPnl(dto);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PutMapping("/{pnlId}")
	public ResponseEntity<PnlRequestDto> updatePnl(@PathVariable Long pnlId, @Valid @RequestBody PnlRequestDto dto) {

		PnlRequestDto response = pnlService.updatePnl(pnlId, dto);

		return ResponseEntity.ok(response);
	}

	@PostMapping("/load")
	public ResponseEntity<PnlRequestDto> loadPnl(@RequestParam String flightNumber,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate flightDate) {

		PnlRequestDto response = pnlService.loadPnl(flightNumber, flightDate);

		return ResponseEntity.ok(response);
	}

}