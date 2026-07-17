package com.ghanshyam.airlinedcs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ghanshyam.airlinedcs.entity.Airline;

public interface AirlineRepository extends JpaRepository<Airline, Long> {

	boolean existsByAirlineCodeAndAirport_AirportId(String airlineCode, Long airportId);

	List<Airline> findByAirport_AirportId(Long airportId);

	Optional<Airline> findByAirport_AirportIdAndAirlineCode(Long airportId, String airlineCode);


}