package com.tesla.platform.u20241f946.shared.domain.model.valueobjects;

import java.math.BigDecimal;

/** Holds base energy and its weather adjustment. */
public record EnergyProfile(BigDecimal baseKwh, BigDecimal weatherMultiplier) {
    public EnergyProfile {
        if (baseKwh == null || baseKwh.signum() <= 0) {
            throw new IllegalArgumentException("Base energy must be greater than zero.");
        }
        if (weatherMultiplier == null || weatherMultiplier.compareTo(BigDecimal.ONE) < 0) {
            throw new IllegalArgumentException("Weather multiplier must be at least 1.0.");
        }
    }

    /** @return total energy in kilowatt-hours */
    public BigDecimal calculateTotalRequiredEnergy() {
        return baseKwh.multiply(weatherMultiplier);
    }
}
