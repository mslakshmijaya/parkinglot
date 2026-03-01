package com.example.parkinglot.controller;

import com.example.parkinglot.dto.EntryTicketDTO;
import com.example.parkinglot.dto.ExitTicketDTO;
import com.example.parkinglot.entity.ParkingSpot;
import com.example.parkinglot.entity.ParkingTransaction;
import com.example.parkinglot.entity.Vehicle;
import com.example.parkinglot.enums.SizeCategoryEnums;
import com.example.parkinglot.exception.ParkingSlotNotFoundException;
import com.example.parkinglot.exception.ParkingTransactionNotFoundException;
import com.example.parkinglot.service.ParkingSpotService;
import com.example.parkinglot.service.ParkingTransactionService;
import com.example.parkinglot.util.FeeCalculator;
import com.example.parkinglot.util.IDGenerator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/parkingTransaction")
public class ParkingTransactionController {
    @Autowired
    private ParkingTransactionService parkingTransactionService;
    @Autowired
    private ParkingSpotService parkingSpotService;
@Transactional
@PostMapping("/checkin")
    public EntryTicketDTO checkinVehicle(@RequestBody Vehicle vehicle) throws ParkingSlotNotFoundException
    {
        /*ParkingSpot spot = parkingSpotService.findBySpotSizeAndIsOccupiedFalse(vehicle.getVehicleSize())
                .stream()
                .findFirst()
                .orElseThrow(() -> new ParkingSlotNotFoundException("No available spot for size Category "+vehicle.getVehicleSize()));*/

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

    }

    @Transactional
    @GetMapping("/checkout")
    public ExitTicketDTO checkOut(String ticketId) throws ParkingTransactionNotFoundException {
        // Find transaction
        ParkingTransaction transaction = parkingTransactionService.findByTicketNumber(ticketId)
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

        transaction = parkingTransactionService.save(transaction);

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
