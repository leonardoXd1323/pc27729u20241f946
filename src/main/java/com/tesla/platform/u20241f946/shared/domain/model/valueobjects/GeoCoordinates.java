package com.tesla.platform.u20241f946.shared.domain.model.valueobjects;

import java.math.BigDecimal;

/** Represents a point in decimal degrees. */
public record GeoCoordinates(BigDecimal latitude, BigDecimal longitude) {
    public GeoCoordinates {
        if (latitude == null || latitude.abs().compareTo(BigDecimal.valueOf(90)) > 0) {
            throw new IllegalArgumentException("Latitude must be between -90 and 90.");
        }
        if (longitude == null || longitude.abs().compareTo(BigDecimal.valueOf(180)) > 0) {
            throw new IllegalArgumentException("Longitude must be between -180 and 180.");
        }
    }
}
