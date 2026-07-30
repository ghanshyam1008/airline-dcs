package com.ghanshyam.airlinedcs.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ghanshyam.airlinedcs.enums.PnlStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class PnlRequestDto {

	private String flightNumber;

	private LocalDate flightDate;

	private Integer totalPassengerCount;

	private PnlStatus status;

	private boolean pnlExists;
	
	private List<PassengerDto> passengers;

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public LocalDate getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(LocalDate flightDate) {
		this.flightDate = flightDate;
	}

	public Integer getTotalPassengerCount() {
		return totalPassengerCount;
	}

	public void setTotalPassengerCount(Integer totalPassengerCount) {
		this.totalPassengerCount = totalPassengerCount;
	}

	public PnlStatus getStatus() {
		return status;
	}

	public void setStatus(PnlStatus status) {
		this.status = status;
	}

	public boolean isPnlExists() {
		return pnlExists;
	}

	public void setPnlExists(boolean pnlExists) {
		this.pnlExists = pnlExists;
	}
	
	

	public List<PassengerDto> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<PassengerDto> passengers) {
		this.passengers = passengers;
	}

	@Override
	public String toString() {
		return "PnlRequestDto [flightNumber=" + flightNumber + ", flightDate=" + flightDate + ", totalPassengerCount="
				+ totalPassengerCount + ", status=" + status + ", pnlExists=" + pnlExists + ", passengers=" + passengers
				+ "]";
	}

}
