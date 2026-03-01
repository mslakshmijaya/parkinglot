package com.airtribe.parkinglot.service;

import com.airtribe.parkinglot.dto.EntryTicketDTO;
import com.airtribe.parkinglot.dto.ExitTicketDTO;
import com.airtribe.parkinglot.entity.ParkingSpot;
import com.airtribe.parkinglot.entity.ParkingTransaction;
import com.airtribe.parkinglot.entity.Vehicle;
import com.airtribe.parkinglot.exception.DuplicateCheckinException;
import com.airtribe.parkinglot.exception.ParkingSlotNotFoundException;
import com.airtribe.parkinglot.exception.ParkingTransactionNotFoundException;
import com.airtribe.parkinglot.repository.ParkingTransactionRepository;
import com.airtribe.parkinglot.util.FeeCalculator;
import com.airtribe.parkinglot.util.IDGenerator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;

@Service
public class ParkingTransactionService {
    @Autowired
    private ParkingTransactionRepository parkingTransactionRepository;
    @Autowired
    private ParkingSpotService parkingSpotService;

    @Transactional
    public EntryTicketDTO checkinVehicle(Vehicle vehicle) throws ParkingSlotNotFoundException, DuplicateCheckinException {


        boolean alreadyParked = parkingTransactionRepository
                .existsByVehicle_LicensePlateAndExitTimeIsNull(vehicle.getLicensePlate());

        if (alreadyParked) {
            throw new DuplicateCheckinException(
                    "Vehicle " + vehicle.getLicensePlate() + " is already checked in and has not checked out yet.");
        }


        ParkingSpot spot = parkingSpotService.findByIsOccupiedFalse()
                .stream()
                .filter(s -> s.getSpotSize().ordinal() >= vehicle.getVehicleSize().ordinal())
                .sorted(Comparator.comparing(ParkingSpot::getSpotSize)) // smallest suitable spot first
                .findFirst()
                .orElseThrow(() -> new ParkingSlotNotFoundException(
                        "No available spot for size " + vehicle.getVehicleSize()));

        spot.setOccupied(true);
        spot= parkingSpotService.save(spot);
        if (!spot.isOccupied()) {
            throw new ParkingSlotNotFoundException("Failed to mark spot " + spot.getSpotNumber() + " as occupied");
        }


        // Create transaction
        ParkingTransaction transaction = new ParkingTransaction();
        transaction.setTicketId(IDGenerator.generateTicketId()); // e.g. "TICKET-1001"
        transaction.setVehicle(vehicle);
        transaction.setParkingSpot(spot);
        transaction.setEntryTime(LocalDateTime.now());
        transaction=   parkingTransactionRepository.save(transaction);

        return new EntryTicketDTO(
                transaction.getTicketId(),
                vehicle.getLicensePlate(),
                vehicle.getVehicleSize(),
                spot.getSpotSize(),
                spot.getSpotNumber(),
                spot.getFloor(),
                transaction.getEntryTime()
        );
    }
    public Optional<ParkingTransaction> findByTicketId(String ticketId){
       return parkingTransactionRepository.findByTicketId(ticketId);
    }
@Transactional
    public ExitTicketDTO checkoutVehicle(String ticketId)throws ParkingTransactionNotFoundException {
        // Find transaction
        ParkingTransaction transaction = findByTicketId(ticketId)
                .orElseThrow(() -> new ParkingTransactionNotFoundException(
                        "No active transaction found for ticket Id " + ticketId));

        ParkingSpot spot = transaction.getParkingSpot();

        // Free the spot
        spot.setOccupied(false);
        parkingSpotService.save(spot);

        // Record exit time
        LocalDateTime exitTime = LocalDateTime.now();
        transaction.setExitTime(exitTime);

        // Calculate fee using FeeCalculator
        double fee = FeeCalculator.calculateFee(
                transaction.getEntryTime(),
                transaction.getExitTime(),
                transaction.getVehicle().getVehicleSize()
        );
        transaction.setFee(fee);

        transaction = parkingTransactionRepository.save(transaction);

        // Build DTO response
        return new ExitTicketDTO(
                transaction.getTicketId(),
                transaction.getVehicle().getLicensePlate(),
                spot.getSpotNumber(),
                spot.getFloor(),
                transaction.getEntryTime(),
                transaction.getExitTime(),
                transaction.getFee()
        );
    }

}
