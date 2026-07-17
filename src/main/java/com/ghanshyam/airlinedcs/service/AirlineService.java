package com.ghanshyam.airlinedcs.service;

import java.util.List;

import com.ghanshyam.airlinedcs.dto.AirlineRequestDto;
import com.ghanshyam.airlinedcs.dto.AirlineResponseDto;
import com.ghanshyam.airlinedcs.dto.DeleteResponseDto;

public interface AirlineService {
	AirlineResponseDto createAirline(AirlineRequestDto dto);

	AirlineResponseDto getAirlineById(Long airlineId);

	AirlineResponseDto updateAirline(Long airlineId, AirlineRequestDto dto);

	DeleteResponseDto deleteAirline(Long airlineId);

	List<AirlineResponseDto> getAllAirlines(Long airportId);

}
