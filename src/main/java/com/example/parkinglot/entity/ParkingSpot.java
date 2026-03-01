package com.example.parkinglot.entity;

import com.example.parkinglot.enums.SizeCategoryEnums;
import jakarta.persistence.*;

@Entity
public class ParkingSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private boolean isOccupied;
    @Column(nullable = false, unique = true)
    private String spotNumber;
    @Enumerated(EnumType.STRING)
    private SizeCategoryEnums spotSize;
    private int floor;

   public ParkingSpot() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public String getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(String spotNumber) {
        this.spotNumber = spotNumber;
    }

    public SizeCategoryEnums getSpotSize() {
        return spotSize;
    }

    public void setSpotSize(SizeCategoryEnums spotSize) {
        this.spotSize = spotSize;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }
}
