package com.tesla.platform.u20241f946.fleet.domain.model.entities;

/** Represents a stop within a fleet dispatch. */
public record Waypoint(Integer stopSequence, String locationName) {
    public Waypoint {
        if (stopSequence == null || stopSequence <= 0) {
            throw new IllegalArgumentException("Stop sequence must be positive.");
        }
        if (locationName == null) {
            throw new IllegalArgumentException("Location name is required.");
        }
    }
}
