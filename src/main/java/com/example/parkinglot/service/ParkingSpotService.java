package com.example.parkinglot.service;

import com.example.parkinglot.dto.ParkingSpotDTO;
import com.example.parkinglot.entity.ParkingSpot;
import com.example.parkinglot.enums.SizeCategoryEnums;
import com.example.parkinglot.repository.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParkingSpotService {
    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    public int saveAll(List<ParkingSpotDTO> parkingSpotConfigs)
    {

        int totalCreated = 0;
        List<ParkingSpot> allSpots = new ArrayList<>();
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
        }
        parkingSpotRepository.saveAll(allSpots);
        totalCreated = allSpots.size();
        return totalCreated;

    }
    public ParkingSpot save(ParkingSpot spot)
    {
       return  parkingSpotRepository.save(spot);

    }
    public List<ParkingSpot> findBySpotSizeAndIsOccupiedFalse(SizeCategoryEnums sizeCategory)
    {
        return parkingSpotRepository.findBySpotSizeAndIsOccupiedFalse(sizeCategory);
    }

    public List<ParkingSpot> findByIsOccupiedFalse()
    {
        return parkingSpotRepository.findByIsOccupiedFalse();
    }
}
