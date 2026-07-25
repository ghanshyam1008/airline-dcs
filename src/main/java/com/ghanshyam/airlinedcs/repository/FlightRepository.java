package com.ghanshyam.airlinedcs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ghanshyam.airlinedcs.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

	boolean existsByFlightNumber(String flightNumber);
	List<Flight> findByAirline_AirlineId(Long airlineId);
	Optional<Flight> findByFlightNumber(String flightNumber);

}
