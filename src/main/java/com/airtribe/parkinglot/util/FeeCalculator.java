package com.airtribe.parkinglot.util;

import com.airtribe.parkinglot.enums.SizeCategoryEnums;

import java.time.Duration;
import java.time.LocalDateTime;

public class FeeCalculator {

    public static double calculateFee(LocalDateTime entryTime, LocalDateTime exitTime, SizeCategoryEnums vehicleSize) {
        long minutes = Duration.between(entryTime, exitTime).toMinutes();
        double hours = Math.ceil(minutes / 60.0);

        // Advanced switch expression
        double ratePerHour = switch (vehicleSize) {
            case SMALL    -> 20;
            case MEDIUM -> 30;
            case LARGE    -> 40;
            default       -> 25;
        };

        if (hours < 1) {
            hours = 1; // minimum charge for 1 hour
        }

        return hours * ratePerHour;
    }
}