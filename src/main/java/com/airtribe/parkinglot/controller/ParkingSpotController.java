package com.airtribe.parkinglot.controller;

import com.airtribe.parkinglot.dto.ParkingSpotDTO;
import com.airtribe.parkinglot.entity.ParkingSpot;
import com.airtribe.parkinglot.enums.SizeCategoryEnums;
import com.airtribe.parkinglot.service.ParkingSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spots")

public class ParkingSpotController {
    @Autowired
    private ParkingSpotService parkingSpotService;

    @PostMapping("/init")
    public String initSpots(@RequestBody  List<ParkingSpotDTO> parkingSpotConfigs) {
        int totalCreated = 0;
        totalCreated=   parkingSpotService.saveAll(parkingSpotConfigs);
        return "Created " + totalCreated + " spots across " + parkingSpotConfigs.size() + " floors.";
    }

    @GetMapping("/available")
    public List<ParkingSpot> getAvailableSpots(@RequestParam(required = false) SizeCategoryEnums sizeCategory) {

            if (sizeCategory != null) {
                return parkingSpotService.findBySpotSizeAndIsOccupiedFalse(sizeCategory);
            } else {
                return parkingSpotService.findByIsOccupiedFalse();
            }

    }



}
