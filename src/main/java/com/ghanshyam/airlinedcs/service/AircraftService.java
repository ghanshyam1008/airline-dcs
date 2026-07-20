package com.ghanshyam.airlinedcs.service;

import java.util.List;

import com.ghanshyam.airlinedcs.dto.AircraftRequestDto;
import com.ghanshyam.airlinedcs.dto.AircraftResponseDto;
import com.ghanshyam.airlinedcs.dto.DeleteResponseDto;

public interface AircraftService {

	AircraftResponseDto createAircraft(AircraftRequestDto dto);

	AircraftResponseDto getAircraftById(Long aircraftId);

	AircraftResponseDto updateAircraft(Long aircraftId, AircraftRequestDto dto);

	DeleteResponseDto deleteAircraft(Long aircraftId);

	List<AircraftResponseDto> getAllAircraft(Long airlineId);

}