package com.ghanshyam.airlinedcs.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "aircraft")
public class Aircraft {

	public Aircraft() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "aircraft_id")
	private Long aircraftId;

	@Column(name = "aircraft_number", unique = true, nullable = false)
	private String aircraftNumber;

	@Column(name = "aircraft_type", nullable = false)
	private String aircraftType;

	@Column(name = "seat_capacity", nullable = false)
	private Integer seatCapacity;

	@Column(name = "active", nullable = false)
	private Boolean active;

	@ManyToOne
	@JoinColumn(name = "airline_id", nullable = false)
	private Airline airline;

	public Long getAircraftId() {
		return aircraftId;
	}

	public void setAircraftId(Long aircraftId) {
		this.aircraftId = aircraftId;
	}

	public String getAircraftNumber() {
		return aircraftNumber;
	}

	public void setAircraftNumber(String aircraftNumber) {
		this.aircraftNumber = aircraftNumber;
	}

	public String getAircraftType() {
		return aircraftType;
	}

	public void setAircraftType(String aircraftType) {
		this.aircraftType = aircraftType;
	}

	public Integer getSeatCapacity() {
		return seatCapacity;
	}

	public void setSeatCapacity(Integer seatCapacity) {
		this.seatCapacity = seatCapacity;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	@Override
	public String toString() {
		return "Aircraft [aircraftId=" + aircraftId + ", aircraftNumber=" + aircraftNumber + ", aircraftType="
				+ aircraftType + ", seatCapacity=" + seatCapacity + ", active=" + active + "]";
	}

}
