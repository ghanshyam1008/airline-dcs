package com.ghanshyam.airlinedcs.dto;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import com.ghanshyam.airlinedcs.enums.FlightType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class FlightRequestDto {
	
	public FlightRequestDto() {
		
	}
	
	@NotBlank(message = "Flight number cannot be empty")
	@Pattern(
	    regexp = "\\d{1,4}",
	    message = "Flight number must contain only digits (1-4 digits)."
	)
	private String flightNumber;

	@NotNull(message = "Airline is required")
	@Positive(message = "Airline Id must be positive")
	private Long airlineId;

	@NotNull(message = "Aircraft is required")
	@Positive(message = "Aircraft Id must be positive")
	private Long aircraftId;

	@NotNull(message = "Origin Airport is required")
	@Positive(message = "Origin Airport Id must be positive")
	private Long originAirportId;

	@NotNull(message = "Destination Airport is required")
	@Positive(message = "Destination Airport Id must be positive")
	private Long destinationAirportId;

	@NotNull(message = "Departure time is required")
	private LocalTime departureTime;

	@NotNull(message = "Arrival time is required")
	private LocalTime arrivalTime;

	@NotNull(message = "Start date is required")
	private LocalDate startDate;

	@NotNull(message = "End date is required")
	private LocalDate endDate;

	@NotEmpty(message = "Please select at least one operating day")
	private Set<DayOfWeek> daysOfOperation;

	@NotNull(message = "Active status is required")
	private Boolean active;
	
	@NotNull(message = "Flight type is required.")
	private FlightType flightType;
	
	
	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public Long getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(Long airlineId) {
		this.airlineId = airlineId;
	}

	public Long getAircraftId() {
		return aircraftId;
	}

	public void setAircraftId(Long aircraftId) {
		this.aircraftId = aircraftId;
	}

	public Long getOriginAirportId() {
		return originAirportId;
	}

	public void setOriginAirportId(Long originAirportId) {
		this.originAirportId = originAirportId;
	}

	public Long getDestinationAirportId() {
		return destinationAirportId;
	}

	public void setDestinationAirportId(Long destinationAirportId) {
		this.destinationAirportId = destinationAirportId;
	}

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}

	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Set<DayOfWeek> getDaysOfOperation() {
		return daysOfOperation;
	}

	public void setDaysOfOperation(Set<DayOfWeek> daysOfOperation) {
		this.daysOfOperation = daysOfOperation;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	public FlightType getFlightType() {
	    return flightType;
	}

	public void setFlightType(FlightType flightType) {
	    this.flightType = flightType;
	}

}
