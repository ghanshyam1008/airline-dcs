package com.ghanshyam.airlinedcs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ghanshyam.airlinedcs.entity.Airport;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
	
	boolean existsByAirportCode(String airportCode);

}