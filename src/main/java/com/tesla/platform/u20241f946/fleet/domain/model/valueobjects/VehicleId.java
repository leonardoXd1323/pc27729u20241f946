package com.tesla.platform.u20241f946.fleet.domain.model.valueobjects;

import java.util.UUID;

/** Identifies a vehicle from another bounded context. */
public record VehicleId(UUID value) {
    public VehicleId {
        if (value == null) {
            throw new IllegalArgumentException("Vehicle ID is required.");
        }
    }
}
