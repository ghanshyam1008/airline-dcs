package com.ghanshyam.airlinedcs.dto;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import com.ghanshyam.airlinedcs.enums.FlightType;

public class FlightResponseDto {
	
	public FlightResponseDto() {
		
	}
	
	private Long flightId;

	private String flightNumber;

	private String airlineCode;

	private String aircraftNumber;

	private String originAirportCode;

	private String destinationAirportCode;
	
	private FlightType flightType;

	private LocalTime departureTime;

	private LocalTime arrivalTime;

	private LocalDate startDate;

	private LocalDate endDate;

	private Set<DayOfWeek> daysOfOperation;

	private Boolean active;
	
	

	public Long getFlightId() {
		return flightId;
	}

	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getAirlineCode() {
		return airlineCode;
	}

	public void setAirlineCode(String airlineCode) {
		this.airlineCode = airlineCode;
	}

	public String getAircraftNumber() {
		return aircraftNumber;
	}

	public void setAircraftNumber(String aircraftNumber) {
		this.aircraftNumber = aircraftNumber;
	}

	public String getOriginAirportCode() {
		return originAirportCode;
	}

	public void setOriginAirportCode(String originAirportCode) {
		this.originAirportCode = originAirportCode;
	}

	public String getDestinationAirportCode() {
		return destinationAirportCode;
	}

	public void setDestinationAirportCode(String destinationAirportCode) {
		this.destinationAirportCode = destinationAirportCode;
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

	@Override
	public String toString() {
		return "FlightResponseDto [flightId=" + flightId + ", flightNumber=" + flightNumber + ", airlineCode="
				+ airlineCode + ", aircraftNumber=" + aircraftNumber + ", originAirportCode=" + originAirportCode
				+ ", destinationAirportCode=" + destinationAirportCode + ", flightType=" + flightType
				+ ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", daysOfOperation=" + daysOfOperation + ", active=" + active + "]";
	}

	
	

}
