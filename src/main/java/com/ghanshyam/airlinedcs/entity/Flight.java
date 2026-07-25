package com.ghanshyam.airlinedcs.entity;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import com.ghanshyam.airlinedcs.enums.FlightType;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "flight")
public class Flight {

	public Flight() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long flightId;

	@NotBlank(message = "Flight number cannot be empty")
	@Column(nullable = false, length = 6)
	private String flightNumber;

	@ManyToOne
	@JoinColumn(name = "airline_id", nullable = false)
	@NotNull(message = "Airline is required")
	private Airline airline;

	@ManyToOne
	@JoinColumn(name = "aircraft_id", nullable = false)
	@NotNull(message = "Aircraft is required")
	private Aircraft aircraft;

	@ManyToOne
	@JoinColumn(name = "origin_airport_id", nullable = false)
	@NotNull(message = "Origin airport is required")
	private Airport originAirport;

	@ManyToOne
	@JoinColumn(name = "destination_airport_id", nullable = false)
	@NotNull(message = "Destination airport is required")
	private Airport destinationAirport;

	@NotNull(message = "Departure time is required")
	@Column(nullable = false)
	private LocalTime departureTime;

	@Column(nullable = false)
	@NotNull(message = "Arrival time time is required")
	private LocalTime arrivalTime;

	@Column(nullable = false)
	@NotNull(message = "Start date is required")
	private LocalDate startDate;

	@Column(nullable = false)
	@NotNull(message = "End date is required")
	private LocalDate endDate;

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "flight_days", joinColumns = @JoinColumn(name = "flight_id"))
	@Column(name = "day_of_week")
	@Enumerated(EnumType.STRING)
	private Set<DayOfWeek> daysOfOperation;

	@NotNull(message = "Active status is required")
	private Boolean active;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private FlightType flightType;

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

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public Aircraft getAircraft() {
		return aircraft;
	}

	public void setAircraft(Aircraft aircraft) {
		this.aircraft = aircraft;
	}

	public Airport getOriginAirport() {
		return originAirport;
	}

	public void setOriginAirport(Airport originAirport) {
		this.originAirport = originAirport;
	}

	public Airport getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(Airport destinationAirport) {
		this.destinationAirport = destinationAirport;
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
		return "Flight [flightId=" + flightId + ", flightNumber=" + flightNumber + ", airline=" + airline
				+ ", aircraft=" + aircraft + ", originAirport=" + originAirport + ", destinationAirport="
				+ destinationAirport + ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", daysOfOperation=" + daysOfOperation
				+ ", active=" + active + "]";
	}

}
