package com.ghanshyam.airlinedcs.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Airport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long airportId;

	@NotBlank(message = "Airport code cannot be empty")
	private String airportCode;

	@NotBlank(message = "Airport name cannot be empty")
	private String airportName;

	private String city;

	@NotNull(message = "Country cannot be empty")
	private String country;

	private Boolean active;

	public Long getAirportId() {
		return airportId;
	}

	public void setAirportId(Long airportId) {
		this.airportId = airportId;
	}

	public String getAirportCode() {
		return airportCode;
	}

	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}

	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Airport [airportId=" + airportId + ", airportCode=" + airportCode + ", airportName=" + airportName
				+ ", city=" + city + ", country=" + country + ", active=" + active + "]";
	}

	public Airport() {
	}

	public Airport(Long airportId, String airportCode, String airportName, String city, String country,
			Boolean active) {
		super();
		this.airportId = airportId;
		this.airportCode = airportCode;
		this.airportName = airportName;
		this.city = city;
		this.country = country;
		this.active = active;
	}

}