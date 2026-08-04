package com.ghanshyam.airlinedcs.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ghanshyam.airlinedcs.entity.Flight;
import com.ghanshyam.airlinedcs.entity.FlightStage;

public interface FlightStageRepository extends JpaRepository<FlightStage, Long> {

	/*
	 * Check whether Flight Stage already exists for a flight on a particular date.
	 */
	boolean existsByFlightAndFlightDate(Flight flight, LocalDate flightDate);

	/*
	 * Find Flight Stage using Flight Number and Operating Date.
	 */
	Optional<FlightStage> findByFlight_FlightNumberAndFlightDate(String flightNumber, LocalDate flightDate);

	Optional<FlightStage> findByFlightAndFlightDate(Flight flight, LocalDate flightDate);

}