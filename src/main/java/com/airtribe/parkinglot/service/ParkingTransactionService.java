package com.airtribe.parkinglot.service;

import com.airtribe.parkinglot.dto.EntryTicketDTO;
import com.airtribe.parkinglot.entity.ParkingSpot;
import com.airtribe.parkinglot.entity.ParkingTransaction;
import com.airtribe.parkinglot.exception.ParkingSlotNotFoundException;
import com.airtribe.parkinglot.repository.ParkingTransactionRepository;
import com.airtribe.parkinglot.util.IDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;

@Service
public class ParkingTransactionService {
    @Autowired
    private ParkingTransactionRepository parkingTransactionRepository;

    public ParkingTransaction save(ParkingTransaction parkingTransaction){

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

        transaction = parkingTransactionService.save(transaction);

        // Build DTO response
        return new EntryTicketDTO(
                transaction.getTicketId(),
                vehicle.getLicensePlate(),
                vehicle.getVehicleSize(),
                spot.getSpotNumber(),
                spot.getFloor(),
                transaction.getEntryTime()
        );


      return  parkingTransactionRepository.save(parkingTransaction);
    }
    public Optional<ParkingTransaction> findByTicketNumber(String ticketId){
       return parkingTransactionRepository.findByTicketNumber(ticketId);
    }

}
