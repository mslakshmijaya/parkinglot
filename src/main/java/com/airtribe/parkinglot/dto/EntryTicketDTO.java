package com.airtribe.parkinglot.dto;

import com.airtribe.parkinglot.enums.SizeCategoryEnums;

import java.time.LocalDateTime;

public class EntryTicketDTO {
    private String ticketId;   // NEW
    private String licensePlate;
    private SizeCategoryEnums spotSize;
    private String spotNumber;
    private int floor;
    private LocalDateTime entryTime;

    public EntryTicketDTO(String ticketId, String licensePlate, SizeCategoryEnums spotSize, String spotNumber, int floor, LocalDateTime entryTime) {
        this.ticketId = ticketId;
        this.licensePlate = licensePlate;
        this.spotSize = spotSize;
        this.floor = floor;
        this.entryTime = entryTime;
        this.spotNumber=spotNumber;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public SizeCategoryEnums getSpotSize() {
        return spotSize;
    }

    public void setSpotSize(SizeCategoryEnums spotSize) {
        this.spotSize = spotSize;
    }

    public String getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(String spotNumber) {
        this.spotNumber = spotNumber;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }
}