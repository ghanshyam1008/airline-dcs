package com.ghanshyam.airlinedcs.dto;

public class AircraftResponseDto {

	public AircraftResponseDto() {

	}

	private Long aircraftId;
	private String aircraftNumber;
	private String aircraftType;
	private Integer seatCapacity;
	private Boolean active;

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

	
	@Override
	public String toString() {
		return "AircraftResponseDto [aircraftId=" + aircraftId + ", aircraftNumber=" + aircraftNumber
				+ ", aircraftType=" + aircraftType + ", seatCapacity=" + seatCapacity + ", active=" + active + "]";
	}
	
	

}
