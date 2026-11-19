package com.tesla.platform.u20241f946.shared.domain.model.valueobjects;

import java.time.LocalDateTime;
import java.time.LocalTime;

/** Holds a dispatch departure and estimated arrival. */
public record TemporalWindow(LocalDateTime departureTime, LocalDateTime estimatedArrival) {
    public TemporalWindow {
        if (departureTime == null || estimatedArrival == null) {
            throw new IllegalArgumentException("Departure and estimated arrival are required.");
        }
        if (!estimatedArrival.isAfter(departureTime.plusHours(1))) {
            throw new IllegalArgumentException("Estimated arrival must be more than one hour after departure.");
        }
    }

    /** @return whether departure is between 20:00 inclusive and 06:00 exclusive */
    public boolean isNightOperation() {
        LocalTime departure = departureTime.toLocalTime();
        return !departure.isBefore(LocalTime.of(20, 0)) || departure.isBefore(LocalTime.of(6, 0));
    }
}
