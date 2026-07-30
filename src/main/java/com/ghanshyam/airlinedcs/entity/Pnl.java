package com.ghanshyam.airlinedcs.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ghanshyam.airlinedcs.enums.PnlStatus;

import jakarta.persistence.CascadeType;
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
@Table(name = "pnl", uniqueConstraints = { @UniqueConstraint(columnNames = { "flight_id", "flight_date" }) })
public class Pnl {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pnlId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "flight_id", nullable = false)
	private Flight flight;

	private LocalDate flightDate;

	private Integer totalPassengerCount;

	@Enumerated(EnumType.STRING)
	private PnlStatus status;

	@OneToMany(mappedBy = "pnl", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Passenger> passengers = new ArrayList<>();

	public Long getPnlId() {
		return pnlId;
	}

	public void setPnlId(Long pnlId) {
		this.pnlId = pnlId;
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

	public void addPassenger(Passenger passenger) {
	    passengers.add(passenger);
	    passenger.setPnl(this);
	}

	public void removePassenger(Passenger passenger) {
	    passengers.remove(passenger);
	    passenger.setPnl(null);
	}
	
	

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

	@Override
	public String toString() {
		return "Pnl [pnlId=" + pnlId + ", flight=" + flight + ", flightDate=" + flightDate + ", totalPassengerCount="
				+ totalPassengerCount + ", status=" + status + "]";
	}

}