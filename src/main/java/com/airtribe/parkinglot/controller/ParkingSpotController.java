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
        /*List<ParkingSpot> allSpots = new ArrayList<>();
        for (ParkingSpotDTO dto : parkingSpotConfigs) {

            // COMPACT
            for (int i = 1; i <= dto.getMediumCount(); i++) {
                ParkingSpot spot = new ParkingSpot();
                spot.setSpotNumber(dto.getFloor() + "-C" + i);
                spot.setSpotSize(SizeCategoryEnums.MEDIUM);
                spot.setOccupied(false);
                spot.setFloor(dto.getFloor());
                allSpots.add(spot);
            }



            // LARGE
            for (int i = 1; i <= dto.getLargeCount(); i++) {
                ParkingSpot spot = new ParkingSpot();
                spot.setSpotNumber(dto.getFloor() + "-L" + i);
                spot.setSpotSize(SizeCategoryEnums.LARGE);
                spot.setOccupied(false);
                spot.setFloor(dto.getFloor());
                allSpots.add(spot);
            }

            // MOTORCYCLE
            for (int i = 1; i <= dto.getSmallCount(); i++) {
                ParkingSpot spot = new ParkingSpot();
                spot.setSpotNumber(dto.getFloor() + "-M" + i);
                spot.setSpotSize(SizeCategoryEnums.SMALL);
                spot.setOccupied(false);
                spot.setFloor(dto.getFloor());
                allSpots.add(spot);
            }
        }*/
        totalCreated=   parkingSpotService.saveAll(parkingSpotConfigs);


        return "Created " + totalCreated + " spots across " + parkingSpotConfigs.size() + " floors.";
    }

    @GetMapping("/available")
    public List<ParkingSpot> findBySpotSizeAndIsOccupiedFalse(@RequestParam SizeCategoryEnums sizeCategory) {
      return  parkingSpotService.findBySpotSizeAndIsOccupiedFalse(sizeCategory);

    }



}
