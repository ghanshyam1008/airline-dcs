package com.ghanshyam.airlinedcs.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ghanshyam.airlinedcs.dto.AirlineRequestDto;
import com.ghanshyam.airlinedcs.dto.AirlineResponseDto;
import com.ghanshyam.airlinedcs.dto.DeleteResponseDto;
import com.ghanshyam.airlinedcs.entity.Airline;
import com.ghanshyam.airlinedcs.entity.Airport;
import com.ghanshyam.airlinedcs.exception.AirlineNotFoundException;
import com.ghanshyam.airlinedcs.exception.AirportNotFoundException;
import com.ghanshyam.airlinedcs.exception.DuplicateAirlineException;
import com.ghanshyam.airlinedcs.repository.AirlineRepository;
import com.ghanshyam.airlinedcs.repository.AirportRepository;
import com.ghanshyam.airlinedcs.service.AirlineService;

@Service
public class AirlineServiceImpl implements AirlineService {

	private final AirlineRepository airlineRepository;
	private final AirportRepository airportRepository;

	private static final Logger logger = LoggerFactory.getLogger(AirlineServiceImpl.class);

	private Airline mapToEntity(AirlineRequestDto dto) {

		Airline airline = new Airline();
		airline.setAirlineCode(dto.getAirlineCode());
		airline.setAirlineName(dto.getAirlineName());
		airline.setActive(dto.getActive());

		return airline;
	}

	private AirlineResponseDto mapToResponseDto(Airline airline) {

		AirlineResponseDto responseDto = new AirlineResponseDto();
		responseDto.setAirlineId(airline.getAirlineId());
		responseDto.setAirlineCode(airline.getAirlineCode());
		responseDto.setAirlineName(airline.getAirlineName());
		responseDto.setActive(airline.getActive());

		responseDto.setAirportId(airline.getAirport().getAirportId());
		responseDto.setAirportCode(airline.getAirport().getAirportCode());
		responseDto.setAirportName(airline.getAirport().getAirportName());

		return responseDto;
	}

	public AirlineServiceImpl(AirlineRepository airlineRepository, AirportRepository airportRepository) {

		this.airlineRepository = airlineRepository;
		this.airportRepository = airportRepository;
	}

	@Override
	public AirlineResponseDto createAirline(AirlineRequestDto dto) {
		logger.info("CreateAirline method started");

		Airport airport = airportRepository.findById(dto.getAirportId())
				.orElseThrow(() -> new AirportNotFoundException(dto.getAirportId()));

		boolean airlineExists = airlineRepository.existsByAirlineCodeAndAirport_AirportId(dto.getAirlineCode(),
				dto.getAirportId());
		if (airlineExists) {
			throw new DuplicateAirlineException(dto.getAirlineCode());
		}
		Airline airline = mapToEntity(dto);
		airline.setAirport(airport);
		Airline savedAirline = airlineRepository.save(airline);
		logger.info("CreateAirline method completed");
		return mapToResponseDto(savedAirline);
	}

	@Override
	public AirlineResponseDto getAirlineById(Long airlineId) {
		logger.info("GetAirlineById method started");
		Airline airline = airlineRepository.findById(airlineId)
				.orElseThrow(() -> new AirlineNotFoundException(airlineId));
		logger.info("GetAirlineById method completed");

		return mapToResponseDto(airline);

	}

	@Override
	public AirlineResponseDto updateAirline(Long airlineId, AirlineRequestDto dto) {

		logger.info("UpdateAirlineById method started");
		Airline existingAirline = airlineRepository.findById(airlineId)
				.orElseThrow(() -> new AirlineNotFoundException(airlineId));

		existingAirline.setAirlineName(dto.getAirlineName());
		existingAirline.setActive(dto.getActive());

		Airline savedAirline = airlineRepository.save(existingAirline);

		logger.info("UpdateAirlineById method completed");

		return mapToResponseDto(savedAirline);

	}

	@Override
	public DeleteResponseDto deleteAirline(Long airlineId) {
		logger.info("DeleteAirlineById method started");

		Airline airline = airlineRepository.findById(airlineId)
				.orElseThrow(() -> new AirlineNotFoundException(airlineId));

		String airlineName = airline.getAirlineName();

		airlineRepository.delete(airline);
		DeleteResponseDto responseDto = new DeleteResponseDto();

		responseDto.setMessage("Airline '" + airlineName + "' deleted successfully.");
		logger.info("DeleteAirlineById method completed");

		return responseDto;
	}

	@Override
	public List<AirlineResponseDto> getAllAirlines(Long airportId) {
		logger.info("GetAllAirlines method started");
		//here not using orelse throw coz it returns list<airlines> so on fronteende we will give
		//some message like no airline exist if list is empty
		List<Airline> airlines = airlineRepository.findByAirport_AirportId(airportId);

		List<AirlineResponseDto> responseList = new ArrayList<>();
		for (Airline airline : airlines) {

			responseList.add(mapToResponseDto(airline));
		}
		logger.info("GetAllAirlines method completed");

		return responseList;
	}

}
