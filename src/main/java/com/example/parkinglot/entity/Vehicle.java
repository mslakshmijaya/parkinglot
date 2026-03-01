package com.example.parkinglot.entity;

import com.example.parkinglot.enums.SizeCategoryEnums;
import com.example.parkinglot.util.IDGenerator;
import jakarta.persistence.*;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true)
    private String licensePlate;

    @Enumerated(EnumType.STRING)
    private SizeCategoryEnums vehicleSize;



public Vehicle() {}
    public SizeCategoryEnums getVehicleSize() {
        return vehicleSize;
    }

    public void setVehicleSize(SizeCategoryEnums vehicleSize) {
        this.vehicleSize = vehicleSize;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
