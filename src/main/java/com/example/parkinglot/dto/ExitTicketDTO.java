package com.example.parkinglot.dto;

import java.time.LocalDateTime;

public class ExitTicketDTO {
    private String ticketId;   // same as entry ticket
    private String licensePlate;
    private String spotNumber;
    private int floor;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private double fee;

    public ExitTicketDTO(String ticketId ,String licensePlate ,  String spotNumber , int floor, LocalDateTime entryTime , LocalDateTime exitTime , double fee ) {
        this.ticketId = ticketId;
        this.licensePlate = licensePlate;
        this.spotNumber = spotNumber;
        this.floor = floor;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.fee = fee;
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

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }
}