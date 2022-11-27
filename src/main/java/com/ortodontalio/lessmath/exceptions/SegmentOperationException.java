package com.ortodontalio.lessmath.exceptions;

/**
 * A class, provides a common exception for all operations with segments. It doesn't matter in which coordinate system.
 * Contains constants - messages, that are accompanied in certain situations.
 *
 * @since 0.1
 * @author Ortodontalio
 */
public class SegmentOperationException extends ArithmeticException {

    public static final String INCORRECT_CREATION = "Attempt to create a zero segment! Use points instead of zero segments.";

    /**
     * Initialization constructor for the exception. Sets exception message.
     * @param s is the exception message.
     */
    public SegmentOperationException(String s) {
        super(s);
    }
}
