package com.ortodontalio.lessmath.geometry.plane.configuration;

/**
 * Enum-class, represents points quarters. It is more informative than practical.
 * Indicates in which quarter the specified point is located.
 *
 * @since 0.1
 * @author Ortodontalio
 */
public enum Quarter {
    UNDEFINED("O"),
    FIRST("I"),
    SECOND("II"),
    THIRD("III"),
    FOURTH("IV");

    /**
     * The value of the quarter in the form of a Roman numeral.
     */
    private final String identifier;

    /**
     * Initialization constructor for all quarters. Sets Roman numerals to all quarters.
     * @param identifier Roman numeral interpretation for quarters.
     */
    Quarter(String identifier) {
        this.identifier = identifier;
    }

    /**
     * String representation of the quarter.
     * @return Roman numeral of the quarter. For example, III.
     */
    @Override
    public String toString() {
        return this.identifier;
    }
}
