package com.tesla.platform.u20241f946.fleet.domain.model.valueobjects;

import com.tesla.platform.u20241f946.shared.domain.model.valueobjects.GeoCoordinates;

import java.math.BigDecimal;

/** Holds the route endpoints and total distance. */
public record RouteData(GeoCoordinates originCoordinates,
                        GeoCoordinates destinationCoordinates,
                        BigDecimal totalDistanceKm) {
    public RouteData {
        if (originCoordinates == null || destinationCoordinates == null) {
            throw new IllegalArgumentException("Origin and destination coordinates are required.");
        }
        if (totalDistanceKm == null || totalDistanceKm.signum() <= 0) {
            throw new IllegalArgumentException("Total distance must be greater than zero.");
        }
    }
}
