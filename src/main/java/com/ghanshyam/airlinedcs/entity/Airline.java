package com.ghanshyam.airlinedcs.entity;

import java.util.ArrayList;
import java.util.List;

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
@Table(name = "airline")
public class Airline {

	public Airline() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long airlineId;
	private String airlineCode;
	private String airlineName;
	private boolean active;

	@ManyToOne
	@JoinColumn(name = "airport_id")
	private Airport airport;

	@OneToMany(mappedBy = "airline", fetch = FetchType.LAZY)
	private List<Aircraft> aircrafts = new ArrayList<>();

	public Long getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(Long airlineId) {
		this.airlineId = airlineId;
	}

	public void setAirlineCode(String airlineCode) {
		this.airlineCode = airlineCode;
	}

	public String getAirlineCode() {
		return airlineCode;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setAirport(Airport airport) {
		this.airport = airport;
	}

	public Airport getAirport() {
		return airport;
	}

	@Override
	public String toString() {
		return "Airline [airlineId=" + airlineId + ", airlineCode=" + airlineCode + ", airlineName=" + airlineName
				+ ", active=" + active + ", airport=" + airport + "]";
	}

}