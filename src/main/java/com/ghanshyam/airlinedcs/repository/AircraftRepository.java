package com.ghanshyam.airlinedcs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ghanshyam.airlinedcs.entity.Aircraft;

public interface AircraftRepository extends JpaRepository<Aircraft, Long> {

    boolean existsByAircraftNumber(String aircraftNumber);

    List<Aircraft> findByAirline_AirlineId(Long airlineId);

}
