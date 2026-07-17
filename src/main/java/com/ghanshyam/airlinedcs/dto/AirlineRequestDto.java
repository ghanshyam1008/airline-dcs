package com.ghanshyam.airlinedcs.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AirlineRequestDto {

	public AirlineRequestDto() {

	}

	@NotBlank (message = "Airline code cannot be empty")
	@Size (min=2,max =2, message= "Airline code must be of 2 Characters")
	private String airlineCode;

	@NotBlank (message = "Airline Name can not be empty")
	private String airlineName;

	@NotNull(message = "Active status is required")
	private Boolean active;

	@NotNull(message = "Airport Id is required")
	private Long airportId;

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
	public void setActive(Boolean active) {
		this.active = active;
	}
	public Boolean getActive() {
		return active;
	}
	public void setAirportId(Long airportId) {
	    this.airportId = airportId;
	}
	public Long getAirportId() {
	    return airportId;
	}



	@Override
	public String toString() {
		return "AirlineRequestDto [airlineCode=" + airlineCode + ", airlineName=" + airlineName + ", active=" + active
				+ ", airportId=" + airportId + "]";
	}


}
