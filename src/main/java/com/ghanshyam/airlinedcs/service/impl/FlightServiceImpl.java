package com.ghanshyam.airlinedcs.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ghanshyam.airlinedcs.dto.AircraftRequestDto;
import com.ghanshyam.airlinedcs.dto.AircraftResponseDto;
import com.ghanshyam.airlinedcs.dto.DeleteResponseDto;
import com.ghanshyam.airlinedcs.dto.FlightRequestDto;
import com.ghanshyam.airlinedcs.dto.FlightResponseDto;
import com.ghanshyam.airlinedcs.entity.Aircraft;
import com.ghanshyam.airlinedcs.entity.Airline;
import com.ghanshyam.airlinedcs.entity.Airport;
import com.ghanshyam.airlinedcs.entity.Flight;
import com.ghanshyam.airlinedcs.exception.AircraftNotFoundException;
import com.ghanshyam.airlinedcs.exception.AirlineNotFoundException;
import com.ghanshyam.airlinedcs.exception.AirportNotFoundException;
import com.ghanshyam.airlinedcs.exception.DuplicateFlightException;
import com.ghanshyam.airlinedcs.exception.FlightNotFoundException;
import com.ghanshyam.airlinedcs.repository.AircraftRepository;
import com.ghanshyam.airlinedcs.repository.AirlineRepository;
import com.ghanshyam.airlinedcs.repository.AirportRepository;
import com.ghanshyam.airlinedcs.repository.FlightRepository;
import com.ghanshyam.airlinedcs.service.FlightService;

@Service
public class FlightServiceImpl implements FlightService {

	private final FlightRepository flightRepository;
	private final AirlineRepository airlineRepository;
	private final AircraftRepository aircraftRepository;
	private final AirportRepository airportRepository;

	public FlightServiceImpl(FlightRepository flightRepository, AirlineRepository airlineRepository,
			AircraftRepository aircraftRepository, AirportRepository airportRepository) {
		this.flightRepository = flightRepository;
		this.airlineRepository = airlineRepository;
		this.aircraftRepository = aircraftRepository;
		this.airportRepository = airportRepository;
	}

	private static final Logger logger = LoggerFactory.getLogger(FlightServiceImpl.class);

	private Flight mapToEntity(FlightRequestDto dto) {

		Flight flight = new Flight();

		flight.setDepartureTime(dto.getDepartureTime());
		flight.setArrivalTime(dto.getArrivalTime());
		flight.setStartDate(dto.getStartDate());
		flight.setEndDate(dto.getEndDate());
		flight.setDaysOfOperation(dto.getDaysOfOperation());
		flight.setFlightType(dto.getFlightType());
		flight.setActive(dto.getActive());

		return flight;
	}

	private FlightResponseDto mapToResponseDto(Flight flight) {

		FlightResponseDto responseDto = new FlightResponseDto();

		responseDto.setFlightId(flight.getFlightId());
		responseDto.setFlightNumber(flight.getFlightNumber());

		responseDto.setAirlineCode(flight.getAirline().getAirlineCode());

		responseDto.setAircraftNumber(flight.getAircraft().getAircraftNumber());

		responseDto.setOriginAirportCode(flight.getOriginAirport().getAirportCode());

		responseDto.setDestinationAirportCode(flight.getDestinationAirport().getAirportCode());

		responseDto.setFlightType(flight.getFlightType());

		responseDto.setDepartureTime(flight.getDepartureTime());
		responseDto.setArrivalTime(flight.getArrivalTime());

		responseDto.setStartDate(flight.getStartDate());
		responseDto.setEndDate(flight.getEndDate());

		responseDto.setDaysOfOperation(flight.getDaysOfOperation());

		responseDto.setActive(flight.getActive());

		return responseDto;
	}

	@Override
	public FlightResponseDto createFlight(FlightRequestDto dto) {
		logger.info("Inside createFlight()");

		logger.info("Creating flight {}", dto.getFlightNumber());

		// Validate Airline
		Airline airline = airlineRepository.findById(dto.getAirlineId())
				.orElseThrow(() -> new AirlineNotFoundException(dto.getAirlineId()));

		String completeFlightNumber = airline.getAirlineCode() + dto.getFlightNumber();

		// Validate Aircraft
		Aircraft aircraft = aircraftRepository.findById(dto.getAircraftId())
				.orElseThrow(() -> new AircraftNotFoundException(dto.getAircraftId()));

		// Business Validation - Aircraft must belong to selected Airline
		if (!aircraft.getAirline().getAirlineId().equals(dto.getAirlineId())) {
			throw new IllegalArgumentException("Selected aircraft does not belong to the selected airline.");
		}

		// Validate Origin Airport
		Airport originAirport = airportRepository.findById(dto.getOriginAirportId())
				.orElseThrow(() -> new AirportNotFoundException(dto.getOriginAirportId()));

		// Validate Destination Airport
		Airport destinationAirport = airportRepository.findById(dto.getDestinationAirportId())
				.orElseThrow(() -> new AirportNotFoundException(dto.getDestinationAirportId()));

		// Business Validation - Origin and Destination cannot be same
		if (originAirport.getAirportId().equals(destinationAirport.getAirportId())) {
			throw new IllegalArgumentException("Origin and destination airports cannot be the same.");
		}

		// Business Validation - Flight Number should be unique within Airline
		boolean flightExists = flightRepository.existsByFlightNumber(completeFlightNumber);

		if (flightExists) {
			throw new DuplicateFlightException(dto.getFlightNumber());
		}

		// Business Validation - Start Date cannot be before today
		if (dto.getStartDate().isBefore(LocalDate.now())) {
			throw new IllegalArgumentException("Start date cannot be before today.");
		}

		// Business Validation - End Date cannot be before Start Date
		if (dto.getEndDate().isBefore(dto.getStartDate())) {
			throw new IllegalArgumentException("End date cannot be before start date.");
		}

		// Map DTO to Entity
		Flight flight = mapToEntity(dto);

		flight.setFlightNumber(completeFlightNumber);

		// Set Relationships
		flight.setAirline(airline);
		flight.setAircraft(aircraft);
		flight.setOriginAirport(originAirport);
		flight.setDestinationAirport(destinationAirport);

		// Save Flight
		flight = flightRepository.save(flight);

		logger.info("Flight {} created successfully", dto.getFlightNumber());

		return mapToResponseDto(flight);
	}

	@Override
	public FlightResponseDto updateFlight(String flightNumber, FlightRequestDto dto) {

		logger.info("Inside updateFlight()");

		Flight flight = flightRepository.findByFlightNumber(flightNumber)
				.orElseThrow(() -> new FlightNotFoundException(flightNumber));

		logger.info("Updating flight {}", flight.getFlightNumber());

		Airline airline = flight.getAirline();
		
		

		Aircraft aircraft = aircraftRepository.findById(dto.getAircraftId())
				.orElseThrow(() -> new AircraftNotFoundException(dto.getAircraftId()));

		if (!aircraft.getAirline().getAirlineId().equals(airline.getAirlineId())) {
			throw new IllegalArgumentException("Selected aircraft does not belong to the selected airline.");
		}

		Airport originAirport = airportRepository.findById(dto.getOriginAirportId())
				.orElseThrow(() -> new AirportNotFoundException(dto.getOriginAirportId()));

		Airport destinationAirport = airportRepository.findById(dto.getDestinationAirportId())
				.orElseThrow(() -> new AirportNotFoundException(dto.getDestinationAirportId()));

		if (originAirport.getAirportId().equals(destinationAirport.getAirportId())) {
			throw new IllegalArgumentException("Origin and destination airports cannot be the same.");
		}

		if (dto.getStartDate().isBefore(LocalDate.now())) {
			throw new IllegalArgumentException("Start date cannot be before today.");
		}

		// Business Validation - End Date cannot be before Start Date
		if (dto.getEndDate().isBefore(dto.getStartDate())) {
			throw new IllegalArgumentException("End date cannot be before start date.");
		}
		flight.setAircraft(aircraft);
		flight.setOriginAirport(originAirport);
		flight.setDestinationAirport(destinationAirport);
		flight.setDepartureTime(dto.getDepartureTime());
		flight.setFlightType(dto.getFlightType());
		flight.setArrivalTime(dto.getArrivalTime());
		flight.setStartDate(dto.getStartDate());
		flight.setEndDate(dto.getEndDate());
		flight.setDaysOfOperation(dto.getDaysOfOperation());
		flight.setActive(dto.getActive());

		flight = flightRepository.save(flight);

		logger.info("Flight {} updated successfully", flight.getFlightNumber());

		return mapToResponseDto(flight);

	}

	@Override
	public FlightResponseDto getFlightByFlightNumber(String flightNumber) {

		logger.info("Inside getFlightByFlightNumber()");

		logger.info("Fetching flight {}", flightNumber);

		Flight flight = flightRepository.findByFlightNumber(flightNumber)
				.orElseThrow(() -> new FlightNotFoundException(flightNumber));

		logger.info("Flight {} fetched successfully", flightNumber);

		return mapToResponseDto(flight);
	}

	@Override
	public DeleteResponseDto deleteFlight(String flightNumber) {
		logger.info("Inside deleteFlight()");

		logger.info("Deleting flight {}", flightNumber);

		Flight flight = flightRepository.findByFlightNumber(flightNumber)
				.orElseThrow(() -> new FlightNotFoundException(flightNumber));

		String deletedFlightNumber = flight.getFlightNumber();

		flightRepository.delete(flight);

		DeleteResponseDto responseDto = new DeleteResponseDto();

		responseDto.setMessage("Flight '" + deletedFlightNumber + "' deleted successfully.");

		logger.info("Flight {} deleted successfully", flightNumber);

		return responseDto;
	}

	@Override
	public List<FlightResponseDto> getAllFlights() {

		logger.info("Inside getAllFlights()");

		logger.info("Fetching all flights");

		List<Flight> flights = flightRepository.findAll();

		List<FlightResponseDto> response = new ArrayList<>();

		for (Flight flight : flights) {
			response.add(mapToResponseDto(flight));
		}

		logger.info("Total {} flights fetched successfully", response.size());

		return response;
	}

}
