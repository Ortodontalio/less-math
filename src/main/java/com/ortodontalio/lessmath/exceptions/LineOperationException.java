package com.ortodontalio.lessmath.exceptions;

/**
 * A class, provides a common exception for all operations with lines. It doesn't matter in which coordinate system.
 * Contains constants - messages, that are accompanied in certain situations.
 *
 * @since 0.1
 * @author Ortodontalio
 */
public class LineOperationException extends ArithmeticException {

    public static final String NO_POINTS = "There are no intersection points!";
    public static final String INFINITY_POINTS = "There are infinity intersection points!";
    public static final String B_NOT_NULL = "B should not be 0!";
    public static final String INCORRECT_CREATION = "Attempt to create a non-existent line!";

    /**
     * Initialization constructor for the exception. Sets exception message.
     * @param s is the exception message.
     */
    public LineOperationException(String s) {
        super(s);
    }
}
