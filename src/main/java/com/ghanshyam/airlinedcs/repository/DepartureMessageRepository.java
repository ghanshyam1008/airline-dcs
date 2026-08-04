package com.ghanshyam.airlinedcs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ghanshyam.airlinedcs.entity.DepartureMessage;

public interface DepartureMessageRepository extends JpaRepository<DepartureMessage, Long> {

}