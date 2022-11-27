package com.ortodontalio.lessmath.geometry.plane.configuration;

/**
 * The class, represents a repository for constants used in mathematical calculations, for example,
 * to specify {@link Constants#EPSILON the error of calculations}.
 *
 * @since 0.1
 * @author Ortodontalio
 */
public enum Constants {
    /**
     * It is mainly used to compare floating point values.
     */
    EPSILON(1e-6f);

    /**
     * Constant value.
     */
    private final float value;

    /**
     * Initialization constructor for all mathematical constants.
     * @param value is the value of the constant.
     */
    Constants(float value) {
        this.value = value;
    }

    /**
     * Getter-method for the {@link #value value field}.
     * @return the value of the {@link #value value field}.
     */
    public float getValue() {
        return value;
    }
}
