package com.ghanshyam.airlinedcs.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class AircraftRequestDto {

    public AircraftRequestDto() {

    }

    @NotBlank(message = "Aircraft Number cannot be empty")
    private String aircraftNumber;

    @NotBlank(message = "Aircraft Type cannot be empty")
    private String aircraftType;

    @NotNull(message = "Seat Capacity is required")
    @Positive(message = "Seat Capacity must be greater than 0")
    private Integer seatCapacity;

    @NotNull(message = "Active status is required")
    private Boolean active;

    @NotNull(message = "Airline Id is required")
    @Positive(message = "Airline Id must be greater than 0")
    private Long airlineId;

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

    public Long getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(Long airlineId) {
        this.airlineId = airlineId;
    }
}