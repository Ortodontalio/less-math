package com.ortodontalio.lessmath.geometry.plane.model;

import com.ortodontalio.lessmath.geometry.plane.configuration.Quarter;
import com.ortodontalio.lessmath.geometry.plane.configuration.Constants;

import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;

/**
 * A class, representing a point (a minimum unit of mathematical geometric operations) in
 * two-dimensional space. Uses a <i>rectangular coordinate system</i>. The point has its own letter
 * designation {@link Point2D#name name}, as well as {@link Point2D#x X} and {@link Point2D#y Y}
 * coordinates. In addition, the class contains a characteristic {@link Point2D#quarter quarter},
 * describing the quarter in which this point is located. All class properties are <b>immutable</b>, however,
 * the class is semi-immutable, since it can be extended.
 * Objects of this class can be sorted, and the largest point will be the one whose distance to the
 * center of the coordinate system will be the largest. To copy data from one point to another,
 * use {@link #Point2D(char, Point2D) the copy constructor}, which additionally allows to assign
 * a name to a new point.<br>
 * Supports the following mathematical operations:
 * <ul>
 *  <li>{@link #isCenter() determine} whether a point is the center of a coordinate system;</li>
 *  <li>{@link #liesOnX() determine} whether a point lies on the OX axis;</li>
 *  <li>{@link #liesOnY() determine} whether a point lies on the OY axis.</li>
 * </ul>
 *
 * @since 0.1
 * @author Ortodontalio
 */
public class Point2D implements Serializable, Comparable<Point2D> {

    /**
     * Letter designation of the point.
     */
    private final char name;
    /**
     * The X coordinate of the point.
     */
    private final float x;
    /**
     * The Y coordinate of the point.
     */
    private final float y;
    /**
     * The quarter in which the point is located.
     */
    private final Quarter quarter;
    private static final long serialVersionUID = 1L;

    /**
     * The default constructor. Creates a point in the center of the coordinate system and
     * gives it a standardized name for such points - <b>O</b>.<br>
     * Text representation of a point - <i>O(0; 0)</i>.
     */
    public Point2D() {
        this.name = 'O';
        this.x = 0;
        this.y = 0;
        this.quarter = initializeQuarter();
    }

    /**
     * Initialization constructor, that allows to create a point with the specified
     * coordinates and {@link Point2D#name name}.
     * @param name is a letter designation of the point being created.
     * @param x is the X coordinate of the point being created.
     * @param y is the Y coordinate of the point being created.
     */
    public Point2D(final char name, final float x, final float y) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.quarter = initializeQuarter();
    }

    /**
     * Copy constructor, that transfers coordinates from another point, but it is
     * possible to set a different name for new point.
     * @param name is a letter designation of the point.
     * @param point is another point, whose parameters need to be moved to a new point.
     */
    public Point2D(final char name, final Point2D point) {
        this.name = name;
        this.x = point.getX();
        this.y = point.getY();
        this.quarter = initializeQuarter();
    }

    /**
     * Getter-method for getting the value of the {@link Point2D#name name} field.
     * @return a letter designation of the point.
     */
    public char getName() {
        return this.name;
    }

    /**
     * Getter-method for getting the value of the {@link Point2D#x x} field.
     * @return the X coordinate of the point.
     */
    public float getX() {
        return this.x;
    }

    /**
     * Getter-method for getting the value of the {@link Point2D#y y} field.
     * @return the Y coordinate of the point.
     */
    public float getY() {
        return this.y;
    }

    /**
     * Getter-method for getting the value of the {@link #quarter quarter} field.
     * @return the quarter in which the point is located.
     */
    public Quarter quarter() {
        return this.quarter;
    }

    /**
     * Method, that determines whether a point is the center of a coordinate system.
     * @return true, if <b>all coordinates are zero</b>, otherwise return false.
     */
    public boolean isCenter() {
        return this.x == 0 && this.y == 0;
    }

    /**
     * Method, that determines whether a point lies on the OX axis.
     * @return true, if <b>the {@link #y Y} coordinate is zero</b>, otherwise return false.
     */
    public boolean liesOnX() {
        return this.y == 0;
    }

    /**
     * Method, that determines whether a point lies on the OY axis.
     * @return true, if <b>the {@link #x X} coordinate is zero</b>, otherwise return false.
     */
    public boolean liesOnY() {
        return this.x == 0;
    }

    /**
     * Private initialization method of the {@link #quarter quarter} field.
     * Determines the quarter according to the following criteria:
     * <ul>
     *     <li>if <b>{@link #y y} > 0</b> and <b>{@link #x x} > 0</b>,
     *     then the point is in <b>the first quarter;</b></li>
     *     <li>if <b>{@link #y y} > 0</b> and <b>{@link #x x} < 0</b>,
     *     then the point is in <b>the second quarter;</b></li>
     *     <li>if <b>{@link #y y} < 0</b> and <b>{@link #x x} < 0</b>,
     *     then the point is in <b>the third quarter;</b></li>
     *     <li>if <b>{@link #y y} < 0</b> and <b>{@link #x x} > 0</b>,
     *     then the point is in <b>the fourth quarter;</b></li>
     *     <li>in the case, when <b>any of the coordinates is zero</b>, or all,
     *     returns uncertainty ({@link Quarter#UNDEFINED Quarter.UNDEFINED}).</li>
     * </ul>
     * @return a {@link Quarter quarter} according to the fulfilled condition.
     */
    private Quarter initializeQuarter() {
        if (this.x < 0) {
            if (this.y < 0) {
                return Quarter.THIRD;
            }
            if (this.y > 0) {
                return Quarter.SECOND;
            }
        } else {
            if (this.y < 0) {
                return Quarter.FOURTH;
            }
            if (this.y > 0) {
                return Quarter.FIRST;
            }
        }
        return Quarter.UNDEFINED;
    }

    /**
     * Overriding equals method.
     * @see Object#equals(Object)
     * @param o the object to compare the current object with.
     * @return true, if the <b>coordinates of the points coincide</b>, otherwise - false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point2D point2D = (Point2D) o;
        return Math.abs(point2D.getX() - getX()) < Constants.EPSILON.getValue() &&
                Math.abs(point2D.getY() - getY()) < Constants.EPSILON.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }

    /**
     * Overloaded version of {@link #toString toString} method. Allows specifying custom localization
     * to print either commas to separate the whole and fractional parts, or dots.
     * @param locale custom localization.
     * @return string standardized representation of a point.
     */
    public String toString(Locale locale) {
        return String.format(locale, "%c(%.2f; %.2f)", this.name, this.x, this.y);
    }

    /**
     * Calls an {@link #toString(Locale) overloaded version}, only with the default localization.
     * Prints two decimal places. Returns the representation of a point in a standardized
     * mathematical version - <i>{char}(X; Y)</i>.<br>
     * For example, A(2.04;12.00), B(-12.00;9.11), C(1.00;0.00) and so on.
     * @return string standardized representation of a point.
     */
    @Override
    public String toString() {
        return toString(Locale.getDefault());
    }

    /**
     * Implemented comparator method. Gets the distance to the center of the coordinate system
     * and therefore determines the distance to the furthest point - this point will be the largest.
     * @param o other point object.
     * @return 1, if this point further from the coordinate center than other point;<br>
     *         0, if the distances from the coordinate center are the same for two points;<br>
     *         -1, if this point closer to the coordinate center than other point.<br>
     */
    @Override
    public int compareTo(Point2D o) {
        final Point2D center = new Point2D();
        final Segment2D firstPointSegment = new Segment2D(this, center);
        final Segment2D secondPointSegment = new Segment2D(o, center);
        if (firstPointSegment.length() > secondPointSegment.length()) {
            return 1;
        } else {
            if (firstPointSegment.length() == secondPointSegment.length()) {
                return 0;
            }
        }
        return -1;
    }
}
