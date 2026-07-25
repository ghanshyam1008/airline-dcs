package com.ghanshyam.airlinedcs.service;

import java.util.List;

import com.ghanshyam.airlinedcs.dto.DeleteResponseDto;
import com.ghanshyam.airlinedcs.dto.FlightRequestDto;
import com.ghanshyam.airlinedcs.dto.FlightResponseDto;

public interface FlightService {

	FlightResponseDto createFlight(FlightRequestDto dto);

	FlightResponseDto updateFlight(String flightNumber, FlightRequestDto dto);

	FlightResponseDto getFlightByFlightNumber(String flightNumber);

	DeleteResponseDto deleteFlight(String flightNumber);

	List<FlightResponseDto> getAllFlights();

}
