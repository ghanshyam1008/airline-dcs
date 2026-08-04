package com.ghanshyam.airlinedcs.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "departure_message")
public class DepartureMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "departure_message_id")
	private Long departureMessageId;

	/*
	 * Flight Stage to which this departure message belongs. One FlightStage can
	 * have multiple departure messages.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "flight_stage_id", nullable = false)
	private FlightStage flightStage;

	/*
	 * Recipient email address.
	 */
	@Column(name = "recipient_email", nullable = false)
	private String recipientEmail;

	/*
	 * Departure message sent to the recipient.
	 */
	@Column(name = "message", nullable = false, length = 2000)
	private String message;

	/*
	 * Timestamp when the message was sent.
	 */
	@Column(name = "sent_at", nullable = false)
	private LocalDateTime sentAt;

	public DepartureMessage() {
	}

	public Long getDepartureMessageId() {
		return departureMessageId;
	}

	public void setDepartureMessageId(Long departureMessageId) {
		this.departureMessageId = departureMessageId;
	}

	public FlightStage getFlightStage() {
		return flightStage;
	}

	public void setFlightStage(FlightStage flightStage) {
		this.flightStage = flightStage;
	}

	public String getRecipientEmail() {
		return recipientEmail;
	}

	public void setRecipientEmail(String recipientEmail) {
		this.recipientEmail = recipientEmail;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getSentAt() {
		return sentAt;
	}

	public void setSentAt(LocalDateTime sentAt) {
		this.sentAt = sentAt;
	}

	@Override
	public String toString() {
		return "DepartureMessage [departureMessageId=" + departureMessageId + ", recipientEmail=" + recipientEmail
				+ ", sentAt=" + sentAt + "]";
	}
}