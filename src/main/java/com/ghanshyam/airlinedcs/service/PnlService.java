package com.ghanshyam.airlinedcs.service;

import java.time.LocalDate;

import com.ghanshyam.airlinedcs.dto.PnlRequestDto;

public interface PnlService {

	PnlRequestDto searchPnl(String flightNumber, LocalDate flightDate);

	PnlRequestDto createPnl(PnlRequestDto dto);

	PnlRequestDto updatePnl(Long pnlId, PnlRequestDto dto);

	PnlRequestDto loadPnl(String flightNumber, LocalDate flightDate);

}
