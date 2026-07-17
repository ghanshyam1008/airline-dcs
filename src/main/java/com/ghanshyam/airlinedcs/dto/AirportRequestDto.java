package com.ghanshyam.airlinedcs.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AirportRequestDto {

	@NotBlank(message = "Airport code cannot be empty")
	@Size(min = 3, max = 3, message = "Airport code must be exactly 3 characters")
	private String airportCode;

	@NotBlank(message = "Airport name cannot be empty")
	private String airportName;

	@NotBlank(message = "City cannot be empty")
	private String city;

	@NotBlank(message = "Country cannot be empty")
	private String country;

	@NotNull(message = "Active status is required")
	private Boolean active;

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

}