package com.ghanshyam.airlinedcs.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.ghanshyam.airlinedcs.enums.FlightStageStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "flight_stage", uniqueConstraints = { @UniqueConstraint(columnNames = { "flight_id", "flight_date" }) })
public class FlightStage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "flight_stage_id")
	private Long flightStageId;

	/*
	 * Flight on which operations are being performed. One Flight can have many
	 * FlightStage records (one per operating date).
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "flight_id", nullable = false)
	private Flight flight;

	/*
	 * Operational date of the flight. Same flight can operate on multiple dates.
	 */
	@Column(name = "flight_date", nullable = false)
	private LocalDate flightDate;

	/*
	 * Current operational stage of the flight.
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "current_stage", nullable = false)
	private FlightStageStatus currentStage;

	/*
	 * Timestamp when flight was initialized. Initialization means the flight is
	 * ready for operations.
	 */
	@Column(name = "initialized_at")
	private LocalDateTime initializedAt;

	/*
	 * Timestamp when Initial Close was performed.
	 */
	@Column(name = "initial_closed_at")
	private LocalDateTime initialClosedAt;

	/*
	 * Timestamp when Initial Close was reopened.
	 */
	@Column(name = "reopened_initial_close_at")
	private LocalDateTime reopenedInitialCloseAt;

	/*
	 * Timestamp when Final Close was performed.
	 */
	@Column(name = "final_closed_at")
	private LocalDateTime finalClosedAt;

	/*
	 * Timestamp when Final Close was reopened.
	 */
	@Column(name = "reopened_final_close_at")
	private LocalDateTime reopenedFinalCloseAt;

	/*
	 * Timestamp when the flight was finally released. After this stage no
	 * operational changes are allowed.
	 */
	@Column(name = "final_released_at")
	private LocalDateTime finalReleasedAt;

	/*
	 * Timestamp when flight was cancelled.
	 */
	@Column(name = "cancelled_at")
	private boolean cancelled;

	@Column(name = "cancelled_By")
	private String cancelledBy;


	/*
	 * Reason for flight cancellation.
	 */
	@Column(name = "cancellation_reason", length = 500)
	private String cancellationReason;

	/*
	 * Stores all departure messages sent for this flight.
	 */
	@OneToMany(mappedBy = "flightStage", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DepartureMessage> departureMessages = new ArrayList<>();

	public FlightStage() {
	}

	public Long getFlightStageId() {
		return flightStageId;
	}

	public void setFlightStageId(Long flightStageId) {
		this.flightStageId = flightStageId;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
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

	public LocalDateTime getInitializedAt() {
		return initializedAt;
	}

	public void setInitializedAt(LocalDateTime initializedAt) {
		this.initializedAt = initializedAt;
	}

	public LocalDateTime getInitialClosedAt() {
		return initialClosedAt;
	}

	public void setInitialClosedAt(LocalDateTime initialClosedAt) {
		this.initialClosedAt = initialClosedAt;
	}

	public LocalDateTime getReopenedInitialCloseAt() {
		return reopenedInitialCloseAt;
	}

	public void setReopenedInitialCloseAt(LocalDateTime reopenedInitialCloseAt) {
		this.reopenedInitialCloseAt = reopenedInitialCloseAt;
	}

	public LocalDateTime getFinalClosedAt() {
		return finalClosedAt;
	}

	public void setFinalClosedAt(LocalDateTime finalClosedAt) {
		this.finalClosedAt = finalClosedAt;
	}

	public LocalDateTime getReopenedFinalCloseAt() {
		return reopenedFinalCloseAt;
	}

	public void setReopenedFinalCloseAt(LocalDateTime reopenedFinalCloseAt) {
		this.reopenedFinalCloseAt = reopenedFinalCloseAt;
	}

	public LocalDateTime getFinalReleasedAt() {
		return finalReleasedAt;
	}

	public void setFinalReleasedAt(LocalDateTime finalReleasedAt) {
		this.finalReleasedAt = finalReleasedAt;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
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

	public List<DepartureMessage> getDepartureMessages() {
		return departureMessages;
	}

	public void setDepartureMessages(List<DepartureMessage> departureMessages) {
		this.departureMessages = departureMessages;
	}


	@Override
	public String toString() {
		return "FlightStage [flightStageId=" + flightStageId + ", flightDate=" + flightDate + ", currentStage="
				+ currentStage + "]";
	}
}