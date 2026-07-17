package com.ghanshyam.airlinedcs.dto;

public class AirlineResponseDto {

	public AirlineResponseDto() {

	}

	private Long airlineId;
	private String airlineCode;
	private String airlineName;
	private Boolean active;
	private String airportCode;
	private String airportName;
	private Long airportId;

	public Long getAirlineId() {
		return airlineId;
	}
	public void setAirlineId(Long airlineId) {
		this.airlineId = airlineId;
	}
	public String getAirlineCode() {
		return airlineCode;
	}
	public void setAirlineCode(String airlineCode) {
		this.airlineCode = airlineCode;
	}
	public String getAirlineName() {
		return airlineName;
	}
	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
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
	public void setAirportId(Long airportId) {
		this.airportId = airportId;
	}
	public Long getAirportId() {
		return airportId;
	}

	@Override
	public String toString() {
		return "AirlineResponseDto [airlineId=" + airlineId + ", airlineCode=" + airlineCode + ", airlineName="
				+ airlineName + ", active=" + active + ", airportCode=" + airportCode + ", airportName=" + airportName
				+ "]";
	}




}
