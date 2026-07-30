package com.ghanshyam.airlinedcs.entity;

import com.ghanshyam.airlinedcs.enums.GenderType;
import com.ghanshyam.airlinedcs.enums.PassengerType;

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
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "passenger", uniqueConstraints = { @UniqueConstraint(columnNames = { "pnl_id", "pnr" }),
		@UniqueConstraint(columnNames = { "pnl_id", "seat_number" }) })

public class Passenger {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long passengerId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pnl_id", nullable = false)
	private Pnl pnl;

	@Column(nullable = false)
	private String pnr;

	@Column(name = "ticket_number", nullable = false)
	private String ticketNumber;

	@Column(name = "passenger_name", nullable = false)
	private String passengerName;

	@Enumerated(EnumType.STRING)
	@Column(name = "passenger_type", nullable = false)
	private PassengerType passengerType;

	@Enumerated(EnumType.STRING)
	@Column(name = "gender", nullable = false)
	private GenderType gender;

	@Column(name = "seat_number", nullable = false, length = 5)
	private String seatNumber;

	public Passenger() {
	}

	public Long getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(Long passengerId) {
		this.passengerId = passengerId;
	}

	public Pnl getPnl() {
		return pnl;
	}

	public void setPnl(Pnl pnl) {
		this.pnl = pnl;
	}

	public String getPnr() {
		return pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public String getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public PassengerType getPassengerType() {
		return passengerType;
	}

	public void setPassengerType(PassengerType passengerType) {
		this.passengerType = passengerType;
	}

	public GenderType getGender() {
		return gender;
	}

	public void setGender(GenderType gender) {
		this.gender = gender;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	@Override
	public String toString() {
		return "Passenger [passengerId=" + passengerId + ", pnr=" + pnr + ", ticketNumber=" + ticketNumber
				+ ", passengerName=" + passengerName + ", passengerType=" + passengerType + ", gender=" + gender
				+ ", seatNumber=" + seatNumber + "]";
	}
}