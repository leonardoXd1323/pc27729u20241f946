package com.tesla.platform.u20241f946.fleet.domain.model.valueobjects;

/** Represents a dispatch code. */
public record DispatchCode(String code) {
    public DispatchCode {
        if (code == null || !code.matches("DSP-[0-9]{6}")) {
            throw new IllegalArgumentException("Dispatch code must be DSP- followed by six digits.");
        }
    }
}
