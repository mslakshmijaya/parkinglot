package com.airtribe.parkinglot.repository;

import com.airtribe.parkinglot.entity.ParkingSpot;
import com.airtribe.parkinglot.enums.SizeCategoryEnums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingSpotRepository  extends JpaRepository<ParkingSpot,Long> {
    // Free spots filtered by size



    public List<ParkingSpot> findBySpotSizeAndIsOccupiedFalse(SizeCategoryEnums spotSize);
    public List<ParkingSpot>  findByIsOccupiedFalse();
}
