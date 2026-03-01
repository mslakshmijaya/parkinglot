package com.example.parkinglot.dto;

public class ParkingSpotDTO {
    private int floor;
    private int smallCount;
    private int mediumCount;
    private int largeCount;


    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }


    public int getLargeCount() {
        return largeCount;
    }

    public void setLargeCount(int largeCount) {
        this.largeCount = largeCount;
    }

    public int getSmallCount() {
        return smallCount;
    }

    public void setSmallCount(int smallCount) {
        this.smallCount = smallCount;
    }

    public int getMediumCount() {
        return mediumCount;
    }

    public void setMediumCount(int mediumCount) {
        this.mediumCount = mediumCount;
    }
}