package com.ghanshyam.airlinedcs.service;

import java.util.List;

import com.ghanshyam.airlinedcs.dto.AirportRequestDto;
import com.ghanshyam.airlinedcs.dto.AirportResponseDto;
import com.ghanshyam.airlinedcs.dto.DeleteResponseDto;

public interface AirportService {

	AirportResponseDto createAirport(AirportRequestDto dto);

	AirportResponseDto getAirportById(Long airportId);

	List<AirportResponseDto> getAllAirports();

	DeleteResponseDto deleteAirport(Long airportId);

	AirportResponseDto updateAirport(Long airportId, AirportRequestDto dto);
}