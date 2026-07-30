package com.ghanshyam.airlinedcs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ghanshyam.airlinedcs.entity.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
