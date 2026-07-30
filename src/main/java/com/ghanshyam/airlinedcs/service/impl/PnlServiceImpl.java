package com.ghanshyam.airlinedcs.service.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.ghanshyam.airlinedcs.dto.PassengerDto;
import com.ghanshyam.airlinedcs.dto.PnlRequestDto;
import com.ghanshyam.airlinedcs.entity.Flight;
import com.ghanshyam.airlinedcs.entity.Passenger;
import com.ghanshyam.airlinedcs.entity.Pnl;
import com.ghanshyam.airlinedcs.enums.PnlStatus;
import com.ghanshyam.airlinedcs.exception.DuplicatePnlException;
import com.ghanshyam.airlinedcs.exception.DuplicatePnrException;
import com.ghanshyam.airlinedcs.exception.DuplicateSeatException;
import com.ghanshyam.airlinedcs.exception.FlightNotFoundException;
import com.ghanshyam.airlinedcs.exception.FlightScheduleNotAvailableException;
import com.ghanshyam.airlinedcs.exception.PassengerCountExceededException;
import com.ghanshyam.airlinedcs.exception.PnlAlreadyLoadedException;
import com.ghanshyam.airlinedcs.exception.PnlNotFoundException;
import com.ghanshyam.airlinedcs.repository.FlightRepository;
import com.ghanshyam.airlinedcs.repository.PnlRepository;
import com.ghanshyam.airlinedcs.service.PnlService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PnlServiceImpl implements PnlService {

	private final FlightRepository flightRepository;
	private final PnlRepository pnlRepository;

	public PnlServiceImpl(FlightRepository flightRepository, PnlRepository pnlRepository) {

		this.flightRepository = flightRepository;
		this.pnlRepository = pnlRepository;
	}

	/**
	 * Converts PNL Entity into Header DTO.
	 *
	 * Purpose: After every operation (Search/Create/Update/Load), only header
	 * information is returned to UI.
	 *
	 * Passenger list is intentionally excluded because different screens may
	 * require different levels of data.
	 */
	private PnlRequestDto mapToHeaderDto(Pnl pnl) {

		PnlRequestDto dto = new PnlRequestDto();

		dto.setFlightNumber(pnl.getFlight().getFlightNumber());
		dto.setFlightDate(pnl.getFlightDate());
		dto.setTotalPassengerCount(pnl.getTotalPassengerCount());
		dto.setStatus(pnl.getStatus());
		dto.setPnlExists(true);

		return dto;
	}

	/**
	 * Validates Flight Number and Flight Date.
	 *
	 * Validation Includes: 1. Flight must exist. 2. Flight must operate within
	 * start/end dates. 3. Flight must operate on the selected day.
	 *
	 * Returns the managed Flight entity if validation succeeds.
	 */
	private Flight validateFlight(String flightNumber, LocalDate flightDate) {

		Flight flight = flightRepository.findByFlightNumber(flightNumber)
				.orElseThrow(() -> new FlightNotFoundException(flightNumber));

		if (flightDate.isBefore(flight.getStartDate()) || flightDate.isAfter(flight.getEndDate())) {

			throw new FlightScheduleNotAvailableException();
		}

		DayOfWeek selectedDay = flightDate.getDayOfWeek();

		if (!flight.getDaysOfOperation().contains(selectedDay)) {

			throw new FlightScheduleNotAvailableException(selectedDay);
		}

		return flight;
	}

	/**
	 * Creates a new PNL entity from DTO.
	 *
	 * Used only while creating a brand-new PNL.
	 */
	private Pnl mapToEntity(PnlRequestDto dto, Flight flight) {

		Pnl pnl = new Pnl();

		pnl.setFlight(flight);
		pnl.setFlightDate(dto.getFlightDate());
		pnl.setTotalPassengerCount(dto.getTotalPassengerCount());
		pnl.setStatus(PnlStatus.SAVED);

		if (dto.getPassengers() != null) {

			for (PassengerDto passengerDto : dto.getPassengers()) {

				Passenger passenger = new Passenger();

				passenger.setPnl(pnl);

				updatePassenger(passenger, passengerDto);

				pnl.getPassengers().add(passenger);
			}
		}

		return pnl;
	}

	/**
	 * Copies passenger details from DTO to Entity.
	 *
	 * Used during: - Create Passenger - Update Passenger
	 *
	 * DRY Principle: All passenger field mapping is maintained at one place.
	 */
	private void updatePassenger(Passenger passenger, PassengerDto dto) {

		passenger.setPnr(dto.getPnr());
		passenger.setTicketNumber(dto.getTicketNumber());
		passenger.setPassengerName(dto.getPassengerName());
		passenger.setPassengerType(dto.getPassengerType());
		passenger.setGender(dto.getGender());
		passenger.setSeatNumber(dto.getSeatNumber());
	}

	/**
	 * Validates passenger count.
	 *
	 * Passenger list should never exceed Total Passenger Count entered by the user.
	 */
	private void validatePassengerCount(PnlRequestDto dto) {

		if (dto.getPassengers() != null && dto.getPassengers().size() > dto.getTotalPassengerCount()) {

			throw new PassengerCountExceededException();
		}
	}

	/**
	 * Ensures duplicate PNRs are not entered within the same PNL.
	 *
	 * HashSet provides O(1) lookup time.
	 */
	private void validateDuplicatePnr(PnlRequestDto dto) {

		if (dto.getPassengers() == null) {
			return;
		}

		Set<String> pnrSet = new HashSet<>();

		for (PassengerDto passenger : dto.getPassengers()) {

			if (!pnrSet.add(passenger.getPnr())) {

				throw new DuplicatePnrException(passenger.getPnr());
			}
		}
	}

	/**
	 * Ensures duplicate seat numbers are not assigned within the same flight.
	 */
	private void validateDuplicateSeat(PnlRequestDto dto) {

		if (dto.getPassengers() == null) {
			return;
		}

		Set<String> seatSet = new HashSet<>();

		for (PassengerDto passenger : dto.getPassengers()) {

			if (!seatSet.add(passenger.getSeatNumber())) {

				throw new DuplicateSeatException(passenger.getSeatNumber());
			}
		}
	}

	/**
	 * Central validation method.
	 *
	 * Keeps service methods clean by grouping all passenger validations together.
	 */
	private void validatePassengerData(PnlRequestDto dto) {

		validatePassengerCount(dto);
		validateDuplicatePnr(dto);
		validateDuplicateSeat(dto);
	}

	/**
	 * Creates a HashMap using PNR as Key.
	 *
	 * Why? Searching in HashMap is O(1), whereas List search is O(n).
	 *
	 * Used while synchronizing passengers during PNL Update.
	 */
	private Map<String, Passenger> getPassengerMap(Pnl pnl) {

		Map<String, Passenger> passengerMap = new HashMap<>();

		for (Passenger passenger : pnl.getPassengers()) {

			passengerMap.put(passenger.getPnr(), passenger);
		}

		return passengerMap;
	}

	/**
	 * Synchronizes Passenger List between UI and Database.
	 *
	 * Business Flow:
	 *
	 * Existing Passenger -> Update New Passenger -> Insert Missing Passenger ->
	 * Delete
	 *
	 * Assumption: Frontend always sends the COMPLETE passenger list.
	 *
	 * Example:
	 *
	 * Database: P1 P2 P3 P4
	 *
	 * UI Sends: P1 P2 P5
	 *
	 * Result: P1 -> Updated P2 -> Updated P5 -> Inserted P3,P4 -> Deleted
	 *
	 * orphanRemoval = true automatically removes deleted passengers from the
	 * database.
	 */
	private void syncPassengers(Pnl pnl, List<PassengerDto> passengerDtos) {

		if (passengerDtos == null) {
			return;
		}

		// Convert existing passengers into HashMap
		// for O(1) lookup.

		Map<String, Passenger> existingPassengerMap = getPassengerMap(pnl);

		for (PassengerDto dto : passengerDtos) {

			/*
			 * remove() performs two operations:
			 *
			 * 1. Returns Passenger if already exists. 2. Removes it from the map.
			 *
			 * After processing every passenger, only deleted passengers remain in the map.
			 */

			Passenger passenger = existingPassengerMap.remove(dto.getPnr());

			if (passenger == null) {

				/*
				 * Passenger not found.
				 *
				 * Create new Passenger.
				 */

				passenger = new Passenger();

				passenger.setPnl(pnl);

				pnl.getPassengers().add(passenger);
			}

			/*
			 * Existing Passenger -> Update New Passenger -> Populate data
			 */

			updatePassenger(passenger, dto);
		}

		/*
		 * Remaining passengers were NOT received from frontend.
		 *
		 * Business meaning: User removed them from PNL.
		 *
		 * orphanRemoval=true automatically deletes them from database during save().
		 */

		pnl.getPassengers().removeAll(existingPassengerMap.values());
	}

	/**
	 * Searches PNL for a given Flight and Date.
	 *
	 * Business Flow:
	 *
	 * 1. Validate Flight Schedule. 2. Search PNL. 3. If found, return Header
	 * Information. 4. Otherwise return Flight details with pnlExists = false.
	 *
	 * Purpose: Frontend uses this response to decide whether Create, Update or Load
	 * operations should be enabled.
	 */
	@Override
	public PnlRequestDto searchPnl(String flightNumber, LocalDate flightDate) {

		validateFlight(flightNumber, flightDate);

		Optional<Pnl> optionalPnl = pnlRepository.findByFlight_FlightNumberAndFlightDate(flightNumber, flightDate);

		if (optionalPnl.isPresent()) {
			return mapToHeaderDto(optionalPnl.get());
		}

		PnlRequestDto dto = new PnlRequestDto();

		dto.setFlightNumber(flightNumber);
		dto.setFlightDate(flightDate);
		dto.setPnlExists(false);

		return dto;
	}

	/**
	 * Creates a new Passenger Name List (PNL).
	 *
	 * Business Flow:
	 *
	 * Validate Flight ↓ Check Duplicate PNL ↓ Validate Passenger Data ↓ Convert DTO
	 * → Entity ↓ Save PNL ↓ Return Header DTO
	 *
	 * Note: Because of CascadeType.ALL, saving PNL automatically saves all
	 * passengers.
	 */
	@Override
	public PnlRequestDto createPnl(PnlRequestDto dto) {

		Flight flight = validateFlight(dto.getFlightNumber(), dto.getFlightDate());

		if (pnlRepository.existsByFlightAndFlightDate(flight, dto.getFlightDate())) {

			throw new DuplicatePnlException();
		}

		validatePassengerData(dto);

		Pnl pnl = mapToEntity(dto, flight);

		return mapToHeaderDto(pnlRepository.save(pnl));
	}

	/**
	 * Updates an existing PNL.
	 *
	 * Business Rules:
	 *
	 * - Loaded PNL cannot be modified. - Passenger list received from UI is treated
	 * as the latest version. - Existing passengers are updated. - New passengers
	 * are inserted. - Removed passengers are automatically deleted using
	 * orphanRemoval=true.
	 */
	@Override
	public PnlRequestDto updatePnl(Long pnlId, PnlRequestDto dto) {

		Pnl pnl = pnlRepository.findById(pnlId).orElseThrow(() -> new PnlNotFoundException(pnlId));

		if (pnl.getStatus() == PnlStatus.LOADED) {
			throw new PnlAlreadyLoadedException();
		}

		validatePassengerData(dto);

		// Update PNL Header
		pnl.setTotalPassengerCount(dto.getTotalPassengerCount());

		// Synchronize Passenger List
		syncPassengers(pnl, dto.getPassengers());

		/*
		 * Hibernate already manages this entity. save() is used for readability and to
		 * explicitly indicate persistence.
		 */

		Pnl updatedPnl = pnlRepository.save(pnl);

		return mapToHeaderDto(updatedPnl);
	}

	/**
	 * Loads a Saved PNL.
	 *
	 * Business Flow:
	 *
	 * Validate Flight ↓ Find PNL ↓ Check if already Loaded ↓ Change Status to
	 * LOADED ↓ Save ↓ Return Header DTO
	 *
	 * Once a PNL is LOADED, further modifications are not allowed.
	 */
	@Override
	public PnlRequestDto loadPnl(String flightNumber, LocalDate flightDate) {

		validateFlight(flightNumber, flightDate);

		Pnl pnl = pnlRepository.findByFlight_FlightNumberAndFlightDate(flightNumber, flightDate)
				.orElseThrow(PnlNotFoundException::new);

		if (pnl.getStatus() == PnlStatus.LOADED) {
			throw new PnlAlreadyLoadedException();
		}

		pnl.setStatus(PnlStatus.LOADED);

		return mapToHeaderDto(pnlRepository.save(pnl));
	}
}
