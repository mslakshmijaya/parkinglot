package com.example.parkinglot.enums;

import java.util.List;

public enum VehicleSizeEnums {

    SMALL,
    MEDIUM,
    LARGE;
    public List<SizeCategoryEnums> toSpotSize() {
        return switch (this) {
            case SMALL -> List.of(SizeCategoryEnums.SMALL);
            case MEDIUM  -> List.of(SizeCategoryEnums.MEDIUM);
            case LARGE   -> List.of(SizeCategoryEnums.LARGE);
        };

    }

}
