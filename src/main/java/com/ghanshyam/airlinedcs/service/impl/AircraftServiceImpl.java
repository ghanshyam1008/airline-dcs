package com.ghanshyam.airlinedcs.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ghanshyam.airlinedcs.dto.AircraftRequestDto;
import com.ghanshyam.airlinedcs.dto.AircraftResponseDto;
import com.ghanshyam.airlinedcs.dto.AirlineRequestDto;
import com.ghanshyam.airlinedcs.dto.AirlineResponseDto;
import com.ghanshyam.airlinedcs.dto.DeleteResponseDto;
import com.ghanshyam.airlinedcs.entity.Aircraft;
import com.ghanshyam.airlinedcs.entity.Airline;
import com.ghanshyam.airlinedcs.exception.AircraftNotFoundException;
import com.ghanshyam.airlinedcs.exception.AirlineNotFoundException;
import com.ghanshyam.airlinedcs.exception.DuplicateAircraftException;
import com.ghanshyam.airlinedcs.repository.AircraftRepository;
import com.ghanshyam.airlinedcs.repository.AirlineRepository;
import com.ghanshyam.airlinedcs.service.AircraftService;

@Service
public class AircraftServiceImpl implements AircraftService {

	public final AircraftRepository aircraftRepository;
	public final AirlineRepository airlineRepository;

	public AircraftServiceImpl(AircraftRepository aircraftRepository, AirlineRepository airlineRepository) {
		this.aircraftRepository = aircraftRepository;
		this.airlineRepository = airlineRepository;
	}

	private static final Logger logger = LoggerFactory.getLogger(AircraftServiceImpl.class);

	private Aircraft mapToEntity(AircraftRequestDto dto) {

		Aircraft aircraft = new Aircraft();
		aircraft.setAircraftNumber(dto.getAircraftNumber());
		aircraft.setAircraftType(dto.getAircraftType());
		aircraft.setActive(dto.getActive());
		aircraft.setSeatCapacity(dto.getSeatCapacity());

		return aircraft;
	}

	private AircraftResponseDto mapToResponseDto(Aircraft aircraft) {

		AircraftResponseDto responseDto = new AircraftResponseDto();
		responseDto.setAircraftId(aircraft.getAircraftId());
		responseDto.setAircraftNumber(aircraft.getAircraftNumber());
		responseDto.setAircraftType(aircraft.getAircraftType());
		responseDto.setSeatCapacity(aircraft.getSeatCapacity());
		responseDto.setActive(aircraft.getActive());

		responseDto.setAircraftId(aircraft.getAirline().getAirlineId());
		return responseDto;
	}

	@Override
	public AircraftResponseDto createAircraft(AircraftRequestDto dto) {
		logger.info("Creating aircraft with number {}", dto.getAircraftNumber());

		Airline airline = airlineRepository.findById(dto.getAirlineId())
				.orElseThrow(() -> new AirlineNotFoundException(dto.getAirlineId()));

		boolean aircraftExists = aircraftRepository.existsByAircraftNumber(dto.getAircraftNumber());

		if (aircraftExists) {
			throw new DuplicateAircraftException(dto.getAircraftNumber());
		}
		Aircraft aircraft = mapToEntity(dto);
		aircraft.setAirline(airline);
		Aircraft savedAircraft = aircraftRepository.save(aircraft);

		logger.info("Aircraft {} created successfully", savedAircraft.getAircraftNumber());

		return mapToResponseDto(savedAircraft);
	}

	@Override
	public AircraftResponseDto getAircraftById(Long aircraftId) {
		logger.info("Fetching aircraft with ID {}", aircraftId);
		Aircraft aircraft = aircraftRepository.findById(aircraftId)
				.orElseThrow(() -> new AircraftNotFoundException(aircraftId));
		logger.info("Aircraft {} fetched successfully", aircraft.getAircraftNumber());

		return mapToResponseDto(aircraft);
	}

	@Override
	public AircraftResponseDto updateAircraft(Long aircraftId, AircraftRequestDto dto) {
		logger.info("update aircraft with ID {}", aircraftId);

		Aircraft existingAircraft = aircraftRepository.findById(aircraftId)
				.orElseThrow(() -> new AircraftNotFoundException(aircraftId));

		if (!existingAircraft.getAircraftNumber().equals(dto.getAircraftNumber())) {
			boolean aircraftExists = aircraftRepository.existsByAircraftNumber(dto.getAircraftNumber());

			if (aircraftExists) {
				throw new DuplicateAircraftException(dto.getAircraftNumber());
			}
		}
		Airline airline = airlineRepository.findById(dto.getAirlineId())
				.orElseThrow(() -> new AirlineNotFoundException(dto.getAirlineId()));

		existingAircraft.setAirline(airline);

		existingAircraft.setAircraftNumber(dto.getAircraftNumber());
		existingAircraft.setAircraftType(dto.getAircraftType());
		existingAircraft.setSeatCapacity(dto.getSeatCapacity());
		existingAircraft.setActive(dto.getActive());
		Aircraft updatedAircraft = aircraftRepository.save(existingAircraft);
		return mapToResponseDto(updatedAircraft);
	}

	@Override
	public DeleteResponseDto deleteAircraft(Long aircraftId) {

		logger.info("Deleting aircraft with ID {}", aircraftId);

		Aircraft existingAircraft = aircraftRepository.findById(aircraftId)
				.orElseThrow(() -> new AircraftNotFoundException(aircraftId));

		// TODO: Prevent deletion if aircraft is assigned to any flights.

		String aircraftNumber = existingAircraft.getAircraftNumber();

		aircraftRepository.delete(existingAircraft);
		
		DeleteResponseDto responseDto = new DeleteResponseDto();
		
		responseDto.setMessage("Aircraft '" + aircraftNumber + "' deleted successfully.");

		logger.info("Aircraft {} deleted successfully", aircraftNumber);

		return responseDto;
	}

	@Override
	public List<AircraftResponseDto> getAllAircraft(Long airlineId) {

		logger.info("Fetching all aircraft for airline ID {}", airlineId);

		List<Aircraft> aircraftList = aircraftRepository.findByAirline_AirlineId(airlineId);

		List<AircraftResponseDto> responseList = new ArrayList<>();

		for (Aircraft aircraft : aircraftList) {
			responseList.add(mapToResponseDto(aircraft));
		}

		logger.info("{} aircraft found for airline ID {}", responseList.size(), airlineId);

		return responseList;
	}

}
