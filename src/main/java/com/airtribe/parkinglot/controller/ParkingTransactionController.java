package com.airtribe.parkinglot.controller;

import com.airtribe.parkinglot.dto.EntryTicketDTO;
import com.airtribe.parkinglot.dto.ExitTicketDTO;
import com.airtribe.parkinglot.entity.ParkingSpot;
import com.airtribe.parkinglot.entity.ParkingTransaction;
import com.airtribe.parkinglot.entity.Vehicle;
import com.airtribe.parkinglot.exception.ParkingSlotNotFoundException;
import com.airtribe.parkinglot.exception.ParkingTransactionNotFoundException;
import com.airtribe.parkinglot.service.ParkingSpotService;
import com.airtribe.parkinglot.service.ParkingTransactionService;
import com.airtribe.parkinglot.util.FeeCalculator;
import com.airtribe.parkinglot.util.IDGenerator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Comparator;

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
        return  parkingTransactionService.checkinVehicle(vehicle);
    }

    @Transactional
    @GetMapping("/checkout")
    public ExitTicketDTO checkOut(String ticketId) throws ParkingTransactionNotFoundException {
        return  parkingTransactionService.checkoutVehicle(ticketId);
    }


}
