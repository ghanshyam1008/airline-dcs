package com.ghanshyam.airlinedcs.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ghanshyam.airlinedcs.dto.AirportRequestDto;
import com.ghanshyam.airlinedcs.dto.AirportResponseDto;
import com.ghanshyam.airlinedcs.dto.DeleteResponseDto;
import com.ghanshyam.airlinedcs.entity.Airport;
import com.ghanshyam.airlinedcs.exception.AirportNotFoundException;
import com.ghanshyam.airlinedcs.exception.DuplicateAirportException;
import com.ghanshyam.airlinedcs.repository.AirportRepository;
import com.ghanshyam.airlinedcs.service.AirportService;

@Service
public class AirportServiceImpl implements AirportService {

	private final AirportRepository airportRepository;
// Helpper Method
	private AirportResponseDto mapToResponseDto(Airport airport) {

		AirportResponseDto responseDto = new AirportResponseDto();

		responseDto.setAirportCode(airport.getAirportCode());
		responseDto.setAirportName(airport.getAirportName());
		responseDto.setCity(airport.getCity());
		responseDto.setCountry(airport.getCountry());
		responseDto.setActive(airport.getActive());

		return responseDto;
	}
// Helper Method
	private Airport mapToEntity(AirportRequestDto dto) {
		Airport airport = new Airport();
		airport.setAirportCode(dto.getAirportCode());
		airport.setAirportName(dto.getAirportName());
		airport.setCity(dto.getCity());
		airport.setCountry(dto.getCountry());
		airport.setActive(true);

		return airport;
	}

	public AirportServiceImpl(AirportRepository airportRepository) {
		this.airportRepository = airportRepository;
	}

	private static final Logger logger = LoggerFactory.getLogger(AirportServiceImpl.class);

	@Override
	public AirportResponseDto createAirport(AirportRequestDto dto) {
//		Check duplicate airport.
//		Convert Request DTO → Entity.
//		Save Entity.
//		Convert Entity → Response DTO.
//		Return Response.
		logger.info("CreateAirport method started.");
		// Step 1: Check duplicate airport code
		boolean airportExists = airportRepository.existsByAirportCode(dto.getAirportCode());
		if (airportExists) {
			logger.warn("Airport with code {} already exists.", dto.getAirportCode());
			throw new DuplicateAirportException(dto.getAirportCode());
		}
		// Step 2: Convert Request DTO to Entity
		Airport airport= mapToEntity(dto);
		logger.info("Saving airport with code {}", airport.getAirportCode());
		// Step 3: Save Airport
		Airport savedAirport = airportRepository.save(airport);
		logger.info("Airport '{}' created successfully.", savedAirport.getAirportName());
		// Step 4:  Convert Entity to Response DTO
		AirportResponseDto responseDto = mapToResponseDto(savedAirport);
		logger.info("CreateAirport method completed.");
		return responseDto;
	}

	@Override
	public AirportResponseDto getAirportById(Long airportId) {
		logger.info("GetAirportById method is started");
		Airport airport = airportRepository.findById(airportId)
				.orElseThrow(() -> new AirportNotFoundException(airportId));

		AirportResponseDto responseDto = new AirportResponseDto();


		responseDto.setAirportId(airport.getAirportId());
		responseDto.setAirportCode(airport.getAirportCode());
		responseDto.setAirportName(airport.getAirportName());
		responseDto.setCity(airport.getCity());
		responseDto.setCountry(airport.getCountry());
		responseDto.setActive(airport.getActive());
		logger.info("GetAirportById method is Completed");

		return responseDto;
	}

	@Override
	public List<AirportResponseDto> getAllAirports() {
		logger.info("GetallAirport method started");
		List<Airport> airports = airportRepository.findAll();

		List<AirportResponseDto> responseDtos = new ArrayList<>();
		for (Airport airport : airports) {

			AirportResponseDto responseDto = new AirportResponseDto();
			responseDto.setAirportId(airport.getAirportId());
			responseDto.setAirportCode(airport.getAirportCode());
			responseDto.setAirportName(airport.getAirportName());
			responseDto.setCity(airport.getCity());
			responseDto.setCountry(airport.getCountry());
			responseDto.setActive(airport.getActive());

			responseDtos.add(responseDto);

		}
		logger.info("GetallAirport method Completed");

		return responseDtos;
	}

	@Override
	public DeleteResponseDto deleteAirport(Long airportId) {
		logger.info("deleteAirport method started");

		Airport existingAirport = airportRepository.findById(airportId)
				.orElseThrow(() -> new AirportNotFoundException(airportId));

		String airportName = existingAirport.getAirportName();

		airportRepository.deleteById(airportId);

		DeleteResponseDto responseDto = new DeleteResponseDto();

		responseDto.setMessage("Airport '" + airportName + "' deleted successfully.");
		logger.info("deleteAirport method Completed");

		return responseDto;
	}

	@Override
	public AirportResponseDto updateAirport(Long airportId, AirportRequestDto dto) {
		logger.info("UpdateAirport method started");

		Airport existingAirport = airportRepository.findById(airportId)
				.orElseThrow(() -> new AirportNotFoundException(airportId));

		existingAirport.setAirportCode(dto.getAirportCode());
		existingAirport.setAirportName(dto.getAirportName());
		existingAirport.setCity(dto.getCity());
		existingAirport.setCountry(dto.getCountry());
		existingAirport.setActive(dto.getActive());

		Airport savedAirport = airportRepository.save(existingAirport);

		AirportResponseDto responseDto = new AirportResponseDto();

		responseDto.setAirportId(savedAirport.getAirportId());
		responseDto.setAirportCode(savedAirport.getAirportCode());
		responseDto.setAirportName(savedAirport.getAirportName());
		responseDto.setCity(savedAirport.getCity());
		responseDto.setCountry(savedAirport.getCountry());
		responseDto.setActive(savedAirport.getActive());
		logger.info("UpdateAirport method Completed");

		return responseDto;

	}
}