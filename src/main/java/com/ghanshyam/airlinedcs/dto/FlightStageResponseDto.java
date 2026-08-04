package com.ghanshyam.airlinedcs.dto;

import java.time.LocalDate;

import com.ghanshyam.airlinedcs.enums.FlightStageStatus;

public class FlightStageResponseDto {

	// Flight Information
	private String flightNumber;
	private LocalDate flightDate;

	// Current Stage
	private FlightStageStatus currentStage;

	// Flight State
	private boolean initialized;
	private boolean cancelled;
	private boolean finalReleased;
	private String cancelledBy;
	private boolean active;
	
	
	

	
	// Cancellation Details
	private String cancellationReason;

	// Success / Information Message
	private String message;

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

	public FlightStageStatus getCurrentStage() {
		return currentStage;
	}

	public void setCurrentStage(FlightStageStatus currentStage) {
		this.currentStage = currentStage;
	}

	public boolean isInitialized() {
		return initialized;
	}

	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	public boolean isFinalReleased() {
		return finalReleased;
	}

	public void setFinalReleased(boolean finalReleased) {
		this.finalReleased = finalReleased;
	}

	public String getCancelledBy() {
		return cancelledBy;
	}

	public void setCancelledBy(String cancelledBy) {
		this.cancelledBy = cancelledBy;
	}

	public String getCancellationReason() {
		return cancellationReason;
	}

	public void setCancellationReason(String cancellationReason) {
		this.cancellationReason = cancellationReason;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "FlightStageResponseDto [flightNumber=" + flightNumber + ", flightDate=" + flightDate + ", currentStage="
				+ currentStage + ", initialized=" + initialized + ", cancelled=" + cancelled + ", finalReleased="
				+ finalReleased + ", cancelledBy=" + cancelledBy + ", cancellationReason=" + cancellationReason
				+ ", message=" + message + "]";
	}

}
