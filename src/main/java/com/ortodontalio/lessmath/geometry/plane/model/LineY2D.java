package com.ortodontalio.lessmath.geometry.plane.model;

import com.ortodontalio.lessmath.geometry.plane.calculation.PointProcessor;
import com.ortodontalio.lessmath.geometry.plane.configuration.Constants;

import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;

/**
 * A class, representing a line in the equation with angular coefficient, represented as <code>y = kx + b</code> in
 * two-dimensional space. Uses a <i>rectangular coordinate system</i>. To represent the line in the equation with
 * angular coefficient, the coefficients {@link #k K} and {@link #b B} are present in the class.
 * These coefficients can take any values, the equation with zero coefficients describes a line that coincides
 * with the axis of the abscissa. All class properties are <b>immutable</b>, however, the class is semi-immutable,
 * since it can be extended.
 * Objects of this class can be sorted, and <i>the largest line</i> will be the one whose distance to the
 * center of the coordinate system will be the largest. To copy data from one line to another,
 * use {@link #LineY2D(LineY2D) the copy constructor}.
 * Supports the following mathematical operations:
 * <ul>
 *  <li>{@link #isParallelToX() determine} whether a line is parallel to the OX axis;</li>
 *  <li>{@link #passesOrigin() determine} whether a line passes through the center of coordinates;</li>
 *  <li>{@link #getFullView() convert} the equation of a line with angular coefficient to an equation in the
 *  general equation.</li>
 * </ul>
 *
 * @since 0.1
 * @author Ortodontalio
 */
public class LineY2D implements Serializable, Comparable<LineY2D> {

    /**
     * <i>Angular coefficient</i>. The coefficient at the X coordinate. The tangent of the angle formed by the line
     * with the positive direction of the abscissa axis.
     */
    private final float k;
    /**
     * <i>Initial ordinate</i>. The coefficient, in absolute value is equal to the length of the segment cut off
     * by the line on the ordinate axis.
     */
    private final float b;
    private static final long serialVersionUID = 30L;

    /**
     * Initialization constructor. Assign values directly to coefficients.
     * @param k is K coefficient;
     * @param b is B coefficient;
     */
    public LineY2D(float k, float b) {
        this.k = k;
        this.b = b;
    }

    /**
     * Copy constructor, that transfers parameters from another line.
     * @param line is another line, whose parameters need to be moved to a new line.
     */
    public LineY2D(final LineY2D line) {
        this.k = line.getK();
        this.b = line.getB();
    }

    /**
     * Getter-method for getting the value of the {@link #k K} field.
     * @return coefficient K in the equation with angular coefficient.
     */
    public float getK() {
        return k;
    }

    /**
     * Getter-method for getting the value of the {@link #b B} field.
     * @return coefficient B in the equation with angular coefficient.
     */
    public float getB() {
        return b;
    }

    /**
     * Method, that determines whether this line is parallel to the OХ axis. To fulfill this condition,
     * the coefficient {@link #k K} must be zero.
     * @return true, if this line is parallel to the OХ axis, otherwise - false.
     */
    public boolean isParallelToX() {
        return this.k == 0;
    }

    /**
     * Method, that determines whether this line passes through the center of the coordinate system.
     * To fulfill this condition, the coefficient {@link #b B} must be zero.
     * @return true, if this line passes through the center of the coordinate system, otherwise - false.
     */
    public boolean passesOrigin() {
        return this.b == 0;
    }

    /**
     * Method, that converts the equation with angular coefficient of a line to a general equation,
     * i.e. from <code>y = kx + b</code> to <code>Ax + By + C = 0</code>.
     * @return an object of the {@link Line2D Line2D class}, whose parameters correspond to the parameters
     * of the current line.
     */
    public Line2D getFullView() {
        return new Line2D(this.k, -1, this.b);
    }

    /**
     * Overriding equals method. To determine the equivalence, compares the values of the coefficients of
     * the two lines according to the following formula:<br>
     * <code>K1/K2 = B1/B2</code>
     * @see Object#equals(Object)
     * @param o the object to compare the current object with.
     * @return true, if the <b>all proportions of coefficients are equal</b>, otherwise - false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineY2D lineY2D = (LineY2D) o;
        return Math.abs(lineY2D.getK() - getK()) < Constants.EPSILON.getValue() &&
                Math.abs(lineY2D.getB() - getB()) < Constants.EPSILON.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getK(), getB());
    }

    /**
     * Overloaded version of {@link #toString toString} method. Allows specifying custom localization
     * to print either commas to separate the whole and fractional parts, or dots.
     * @param locale custom localization.
     * @return string specific representation of a line.
     */
    public String toString(Locale locale) {
        final StringBuilder yView = new StringBuilder(String.format(locale, "y = %.2fx ", this.k));
        if (this.b >= 0) {
            yView.append(String.format(locale, "+ %.2f", this.b));
        } else {
            yView.append(String.format(locale, "- %.2f", -this.b));
        }
        return yView.toString();
    }

    /**
     * Calls an {@link #toString(Locale) overloaded version}, only with the default localization.
     * Prints two decimal places. Returns the representation of a line in the next version -
     * <i>y = kx + b</i>.<br>
     * For example, if line is created using the following coefficients k = 9.12 and b = 11.2, then
     * y = 9.12x + 11.20 and so on.
     * @return string standardized representation of a line.
     */
    @Override
    public String toString() {
        return toString(Locale.getDefault());
    }

    /**
     * Implemented comparator method. Gets the distance to the center of the coordinate system
     * and therefore determines the distance to the furthest line - this line <i>will be the largest</i>.
     * @param o other line object.
     * @return 1, if this line further from the coordinate center than other line;<br>
     *         0, if the distances from the coordinate center are the same for two lines;<br>
     *         -1, if this line closer to the coordinate center than other line.<br>
     */
    @Override
    public int compareTo(LineY2D o) {
        final Point2D center = new Point2D();
        final float distanceToThis = PointProcessor.distanceToLine(center, this);
        final float distanceToOther = PointProcessor.distanceToLine(center, o);
        if (distanceToThis > distanceToOther) {
            return 1;
        } else {
            if (distanceToThis == distanceToOther) {
                return 0;
            }
            return -1;
        }
    }
}
