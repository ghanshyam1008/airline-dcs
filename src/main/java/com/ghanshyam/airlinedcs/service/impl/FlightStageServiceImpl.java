package com.ghanshyam.airlinedcs.service.impl;

import java.time.DayOfWeek;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.ghanshyam.airlinedcs.dto.FlightStageResponseDto;
import com.ghanshyam.airlinedcs.entity.DepartureMessage;
import com.ghanshyam.airlinedcs.entity.Flight;
import com.ghanshyam.airlinedcs.entity.FlightStage;
import com.ghanshyam.airlinedcs.entity.Pnl;
import com.ghanshyam.airlinedcs.enums.FlightStageStatus;
import com.ghanshyam.airlinedcs.enums.PnlStatus;
import com.ghanshyam.airlinedcs.exception.FlightAlreadyCancelledException;
import com.ghanshyam.airlinedcs.exception.FlightAlreadyFinalReleasedException;
import com.ghanshyam.airlinedcs.exception.FlightAlreadyInitializedException;
import com.ghanshyam.airlinedcs.exception.FlightNotFoundException;
import com.ghanshyam.airlinedcs.exception.FlightScheduleNotAvailableException;
import com.ghanshyam.airlinedcs.exception.FlightStageNotFoundException;
import com.ghanshyam.airlinedcs.exception.InvalidFlightStageException;
import com.ghanshyam.airlinedcs.exception.PnlNotFoundException;
import com.ghanshyam.airlinedcs.exception.PnlNotLoadedException;
import com.ghanshyam.airlinedcs.repository.DepartureMessageRepository;
import com.ghanshyam.airlinedcs.repository.FlightRepository;
import com.ghanshyam.airlinedcs.repository.FlightStageRepository;
import com.ghanshyam.airlinedcs.repository.PnlRepository;
import com.ghanshyam.airlinedcs.service.FlightStageService;

@Service
public class FlightStageServiceImpl implements FlightStageService {

	private final FlightRepository flightRepository;
	private final FlightStageRepository flightStageRepository;
	private final PnlRepository pnlRepository;
	private final DepartureMessageRepository departureMessageRepository;

	public FlightStageServiceImpl(FlightRepository flightRepository, FlightStageRepository flightStageRepository,
			PnlRepository pnlRepository, DepartureMessageRepository departureMessageRepository) {

		this.flightRepository = flightRepository;
		this.flightStageRepository = flightStageRepository;
		this.pnlRepository = pnlRepository;
		this.departureMessageRepository = departureMessageRepository;
	}

	/*
	 * Converts FlightStage Entity into Response DTO. Only exposes the information
	 * required by the UI.
	 */
	private FlightStageResponseDto mapToDto(FlightStage flightStage) {

		FlightStageResponseDto dto = new FlightStageResponseDto();

		dto.setFlightNumber(flightStage.getFlight().getFlightNumber());
		dto.setFlightDate(flightStage.getFlightDate());
		dto.setCurrentStage(flightStage.getCurrentStage());

		dto.setCancelled(flightStage.isCancelled());
		dto.setCancelledBy(flightStage.getCancelledBy());
		dto.setCancellationReason(flightStage.getCancellationReason());

		return dto;
	}

	/*
	 * Validates whether the requested flight exists and operates on the selected
	 * date.
	 *
	 * Returns the Flight entity if validation succeeds.
	 */
	private Flight validateFlight(String flightNumber, LocalDate flightDate) {

		Flight flight = flightRepository.findByFlightNumber(flightNumber)
				.orElseThrow(() -> new FlightNotFoundException(flightNumber));

		if (flightDate.isBefore(flight.getStartDate()) || flightDate.isAfter(flight.getEndDate())) {

			throw new FlightScheduleNotAvailableException();
		}

		DayOfWeek selectedDay = flightDate.getDayOfWeek();

		if (!flight.getDaysOfOperation().contains(selectedDay)) {
			throw new FlightScheduleNotAvailableException(selectedDay);
		}

		return flight;
	}

	/*
	 * Retrieves the Flight Stage for the given flight and operating date.
	 *
	 * Throws FlightStageNotFoundException if no Flight Stage exists.
	 */
	private FlightStage getFlightStage(Flight flight, LocalDate flightDate) {

		return flightStageRepository.findByFlightAndFlightDate(flight, flightDate)
				.orElseThrow(() -> new FlightStageNotFoundException(flight.getFlightNumber(), flightDate));
	}

	/*
	 * Validates whether the PNL exists and has been loaded.
	 *
	 * A flight can only be initialized after its PNL status is LOADED.
	 */
	private void validatePnlLoaded(Flight flight, LocalDate flightDate) {

		Pnl pnl = pnlRepository.findByFlightAndFlightDate(flight, flightDate)
				.orElseThrow(() -> new PnlNotFoundException());

		if (pnl.getStatus() != PnlStatus.LOADED) {
			throw new PnlNotLoadedException();
		}
	}

	/*
	 * Creates and initializes a new Flight Stage for the given Flight and Operating
	 * Date.
	 *
	 * Default State: - Current Stage : OPEN - Initialized Time : Current Timestamp
	 * - Cancelled : No - Final Released : No
	 */
	private FlightStage createFlightStage(Flight flight, LocalDate flightDate) {

		FlightStage flightStage = new FlightStage();

		flightStage.setFlight(flight);
		flightStage.setFlightDate(flightDate);

		// Flight becomes operational after successful initialization.
		flightStage.setCurrentStage(FlightStageStatus.OPEN);

		// Record the initialization timestamp.
		flightStage.setInitializedAt(LocalDateTime.now());

		// Default values for a newly initialized Flight.
		flightStage.setCancelled(false);
		flightStage.setCancelledBy(null);
		flightStage.setCancellationReason(null);
		flightStage.setFinalReleasedAt(null);

		return flightStage;
	}

	/*
	 * Validates whether the Flight has already been initialized.
	 *
	 * A Flight can only be initialized once for a particular operating date.
	 */
	private void validateNotInitialized(Flight flight, LocalDate flightDate) {

		if (flightStageRepository.existsByFlightAndFlightDate(flight, flightDate)) {
			throw new FlightAlreadyInitializedException(flight.getFlightNumber(), flightDate);
		}
	}

	/*
	 * Validates whether the Flight is currently in the expected stage.
	 *
	 * Example: Expected Stage : OPEN Current Stage : INITIAL_CLOSE
	 *
	 * Result: Throws InvalidFlightStageException.
	 */
	private void validateCurrentStage(FlightStage flightStage, FlightStageStatus expectedStage) {

		if (flightStage.getCurrentStage() != expectedStage) {
			throw new InvalidFlightStageException(expectedStage, flightStage.getCurrentStage());
		}
	}

	/*
	 * Updates the Flight Stage and persists the changes.
	 */
	private FlightStage changeStage(FlightStage flightStage, FlightStageStatus newStage) {

		flightStage.setCurrentStage(newStage);

		return flightStageRepository.save(flightStage);
	}

	/*
	 * Validates whether the Flight has been cancelled.
	 *
	 * No further operations are allowed on a cancelled Flight.
	 */
	private void validateNotCancelled(FlightStage flightStage) {

		if (flightStage.isCancelled()) {
			throw new FlightAlreadyCancelledException();
		}
	}

	/*
	 * Validates whether the Flight has already been finally released.
	 *
	 * Once finally released, no further stage transition is allowed.
	 */
	private void validateNotFinalReleased(FlightStage flightStage) {

		if (flightStage.getFinalReleasedAt() != null) {
			throw new FlightAlreadyFinalReleasedException();
		}
	}

	@Override
	public FlightStageResponseDto searchFlightStage(String flightNumber, LocalDate flightDate) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * Initializes a Flight for the selected operating date.
	 *
	 * Business Rules: 1. Flight must exist and operate on the selected date. 2. PNL
	 * must exist and be in LOADED status. 3. Flight must not be initialized
	 * already. 4. Creates a new Flight Stage with OPEN status.
	 */
	@Override
	public FlightStageResponseDto initializeFlight(String flightNumber, LocalDate flightDate) {

		// Validate whether the Flight exists and operates on the selected date.
		Flight flight = validateFlight(flightNumber, flightDate);

		// Flight can only be initialized after PNL has been loaded.
		validatePnlLoaded(flight, flightDate);

		// Prevent duplicate initialization.
		validateNotInitialized(flight, flightDate);

		// Create a new Flight Stage.
		FlightStage flightStage = createFlightStage(flight, flightDate);

		// Persist the Flight Stage.
		FlightStage savedFlightStage = flightStageRepository.save(flightStage);

		// Convert Entity to Response DTO.
		return mapToDto(savedFlightStage);
	}

	@Override
	public FlightStageResponseDto uninitializeFlight(String flightNumber, LocalDate flightDate) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * Moves the Flight from OPEN stage to INITIAL_CLOSE.
	 *
	 * Business Rules: 1. Flight must exist. 2. Flight Stage must exist. 3. Flight
	 * must not be cancelled. 4. Flight must not be finally released. 5. Current
	 * Stage must be OPEN.
	 */
	@Override
	public FlightStageResponseDto initialClose(String flightNumber, LocalDate flightDate) {

		Flight flight = validateFlight(flightNumber, flightDate);

		FlightStage flightStage = getFlightStage(flight, flightDate);

		validateNotCancelled(flightStage);

		validateNotFinalReleased(flightStage);

		validateCurrentStage(flightStage, FlightStageStatus.OPEN);

		FlightStage updatedFlightStage = changeStage(flightStage, FlightStageStatus.INITIAL_CLOSE);

		return mapToDto(updatedFlightStage);
	}

	/*
	 * Reopens the Initial Close stage to allow pending passenger check-in.
	 *
	 * Business Rules: 1. Flight Stage must exist. 2. Flight must not be cancelled.
	 * 3. Flight must not be finally released. 4. Current Stage must be
	 * INITIAL_CLOSE.
	 */
	@Override
	public FlightStageResponseDto reopenInitialClose(String flightNumber, LocalDate flightDate) {

		Flight flight = validateFlight(flightNumber, flightDate);

		FlightStage flightStage = getFlightStage(flight, flightDate);

		validateNotCancelled(flightStage);

		validateNotFinalReleased(flightStage);

		validateCurrentStage(flightStage, FlightStageStatus.INITIAL_CLOSE);

		FlightStage updatedFlightStage = changeStage(flightStage, FlightStageStatus.REOPEN_INITIAL_CLOSE);

		return mapToDto(updatedFlightStage);
	}

	/*
	 * Moves the Flight from INITIAL_CLOSE stage to FINAL_CLOSE.
	 *
	 * Business Rules: 1. Flight must exist. 2. Flight Stage must exist. 3. Flight
	 * must not be cancelled. 4. Flight must not be finally released. 5. Current
	 * Stage must be INITIAL_CLOSE.
	 */
	@Override
	public FlightStageResponseDto finalClose(String flightNumber, LocalDate flightDate) {

		// Validate whether the Flight exists and operates on the selected date.
		Flight flight = validateFlight(flightNumber, flightDate);

		// Retrieve the Flight Stage.
		FlightStage flightStage = getFlightStage(flight, flightDate);

		// Flight must not be cancelled.
		validateNotCancelled(flightStage);

		// Flight must not be finally released.
		validateNotFinalReleased(flightStage);

		// Flight must currently be in INITIAL_CLOSE stage.
		validateCurrentStage(flightStage, FlightStageStatus.INITIAL_CLOSE);

		// Move the Flight to FINAL_CLOSE stage.
		FlightStage updatedFlightStage = changeStage(flightStage, FlightStageStatus.FINAL_CLOSE);

		// Return updated Flight Stage details.
		return mapToDto(updatedFlightStage);
	}

	/*
	 * Reopens the FINAL_CLOSE stage to allow pending passenger boarding.
	 *
	 * Business Rules: 1. Flight must exist. 2. Flight Stage must exist. 3. Flight
	 * must not be cancelled. 4. Flight must not be finally released. 5. Current
	 * Stage must be FINAL_CLOSE.
	 */
	@Override
	public FlightStageResponseDto reopenFinalClose(String flightNumber, LocalDate flightDate) {

		Flight flight = validateFlight(flightNumber, flightDate);

		FlightStage flightStage = getFlightStage(flight, flightDate);

		validateNotCancelled(flightStage);

		validateNotFinalReleased(flightStage);

		validateCurrentStage(flightStage, FlightStageStatus.FINAL_CLOSE);

		FlightStage updatedFlightStage = changeStage(flightStage, FlightStageStatus.REOPEN_FINAL_CLOSE);

		return mapToDto(updatedFlightStage);
	}

	/*
	 * Marks the Flight as Finally Released.
	 *
	 * Business Rules: 1. Flight must exist. 2. Flight Stage must exist. 3. Flight
	 * must not be cancelled. 4. Flight must not be already finally released. 5.
	 * Current Stage must be FINAL_CLOSE.
	 */
	@Override
	public FlightStageResponseDto finalRelease(String flightNumber, LocalDate flightDate) {

		// Validate Flight Schedule.
		Flight flight = validateFlight(flightNumber, flightDate);

		// Retrieve Flight Stage.
		FlightStage flightStage = getFlightStage(flight, flightDate);

		// Flight must not be cancelled.
		validateNotCancelled(flightStage);

		// Flight must not already be finally released.
		validateNotFinalReleased(flightStage);

		// Flight must currently be in FINAL_CLOSE stage.
		validateCurrentStage(flightStage, FlightStageStatus.FINAL_CLOSE);

		// Move to FINAL_RELEASE stage.
		flightStage.setCurrentStage(FlightStageStatus.FINAL_RELEASE);

		// Record Final Release time.
		flightStage.setFinalReleasedAt(LocalDateTime.now());

		FlightStage updatedFlightStage = flightStageRepository.save(flightStage);

		return mapToDto(updatedFlightStage);
	}

	/*
	 * Cancels the Flight.
	 *
	 * Business Rules: 1. Flight must exist. 2. Flight Stage must exist. 3. Flight
	 * must not be finally released. 4. Flight must not already be cancelled. 5.
	 * Store cancellation reason. 6. Store the user who cancelled the flight.
	 */
	@Override
	public FlightStageResponseDto cancelFlight(String flightNumber, LocalDate flightDate, String cancellationReason) {

		// Validate Flight.
		Flight flight = validateFlight(flightNumber, flightDate);

		// Retrieve Flight Stage.
		FlightStage flightStage = getFlightStage(flight, flightDate);

		// Final Released flights cannot be cancelled.
		validateNotFinalReleased(flightStage);

		// Flight should not already be cancelled.
		validateNotCancelled(flightStage);

		// Mark Flight as cancelled.
		flightStage.setCancelled(true);

		// Store cancellation reason.
		flightStage.setCancellationReason(cancellationReason);

		// Temporary value.
		// Later this will come from the logged-in user.
		flightStage.setCancelledBy("ADMIN");

		FlightStage updatedFlightStage = flightStageRepository.save(flightStage);

		return mapToDto(updatedFlightStage);
	}

	/*
	 * Sends a Departure Message for the Flight.
	 *
	 * Business Rules: 1. Flight must exist. 2. Flight Stage must exist. 3. Flight
	 * must not be cancelled. 4. Flight must be in FINAL_RELEASE stage. 5. Save the
	 * message in DepartureMessage table.
	 *
	 * Note: Actual Email sending will be implemented later.
	 */
	@Override
	public FlightStageResponseDto sendDepartureMessage(String flightNumber, LocalDate flightDate, String recipientEmail,
			String messageBody) {

		// Validate Flight.
		Flight flight = validateFlight(flightNumber, flightDate);

		// Retrieve Flight Stage.
		FlightStage flightStage = getFlightStage(flight, flightDate);

		// Flight should not be cancelled.
		validateNotCancelled(flightStage);

		// Message can only be sent after Final Release.
		validateCurrentStage(flightStage, FlightStageStatus.FINAL_RELEASE);

		// Create Departure Message.
		DepartureMessage departureMessage = new DepartureMessage();

		departureMessage.setRecipientEmail(recipientEmail);
		departureMessage.setMessage(messageBody);
		departureMessage.setFlightStage(flightStage);
		flightStage.getDepartureMessages().add(departureMessage);

		// Save Message History.
		DepartureMessage savedMessage = departureMessageRepository.save(departureMessage);

		/*
		 * Future Enhancement: Send actual email using JavaMailSender.
		 */

		return mapToDto(flightStage);
	}

	@Override
	public FlightStageResponseDto completeReopenInitialClose(String flightNumber, LocalDate flightDate) {

		// Validate Flight.
		Flight flight = validateFlight(flightNumber, flightDate);

		// Retrieve Flight Stage.
		FlightStage flightStage = getFlightStage(flight, flightDate);

		// Flight must not be cancelled.
		validateNotCancelled(flightStage);

		// Flight must not be finally released.
		validateNotFinalReleased(flightStage);

		// Current Stage must be REOPEN_INITIAL_CLOSE.
		validateCurrentStage(flightStage, FlightStageStatus.REOPEN_INITIAL_CLOSE);

		// Move Flight back to OPEN stage.
		FlightStage updatedFlightStage = changeStage(flightStage, FlightStageStatus.OPEN);

		return mapToDto(updatedFlightStage);
	}

	@Override
	public FlightStageResponseDto completeReopenFinalClose(String flightNumber, LocalDate flightDate) {

		// Validate Flight.
		Flight flight = validateFlight(flightNumber, flightDate);

		// Retrieve Flight Stage.
		FlightStage flightStage = getFlightStage(flight, flightDate);

		// Flight must not be cancelled.
		validateNotCancelled(flightStage);

		// Flight must not be finally released.
		validateNotFinalReleased(flightStage);

		// Current Stage must be REOPEN_FINAL_CLOSE.
		validateCurrentStage(flightStage, FlightStageStatus.REOPEN_FINAL_CLOSE);

		// Move Flight back to INITIAL_CLOSE stage.
		FlightStage updatedFlightStage = changeStage(flightStage, FlightStageStatus.INITIAL_CLOSE);

		return mapToDto(updatedFlightStage);
	}

}
