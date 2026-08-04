package com.ghanshyam.airlinedcs.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ghanshyam.airlinedcs.entity.Flight;
import com.ghanshyam.airlinedcs.entity.Pnl;

public interface PnlRepository extends JpaRepository<Pnl, Long> {

	Optional<Pnl> findByFlight_FlightNumberAndFlightDate(String flightNumber, LocalDate flightDate);

	boolean existsByFlightAndFlightDate(Flight flight, LocalDate flightDate);

	Optional<Pnl> findById(Long id);

	Optional<Pnl> findByFlightAndFlightDate(Flight flight, LocalDate flightDate);

}