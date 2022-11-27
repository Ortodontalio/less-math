package com.ortodontalio.lessmath.geometry.plane.model;

import com.ortodontalio.lessmath.exceptions.LineOperationException;
import com.ortodontalio.lessmath.geometry.plane.calculation.PointProcessor;
import com.ortodontalio.lessmath.geometry.plane.configuration.Constants;

import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;

/**
 * A class, representing a line in the general equation, represented as <code>Ax + By + C = 0</code> in
 * two-dimensional space. Uses a <i>rectangular coordinate system</i>. To represent the line in the
 * general equation, the coefficients {@link #a A}, {@link #b B} and {@link #c C} are present in the class.
 * These coefficients can take any values except zero, otherwise the equation takes either the identity 0=0,
 * or the nonsense n=0 (for example, 9=0), so there is no possibility in the class to create lines with both
 * zero parameters {@link #a A} and {@link #b B}. All class properties are <b>immutable</b>, however, the class
 * is semi-immutable, since it can be extended.
 * Objects of this class can be sorted, and <i>the largest line</i> will be the one whose distance to the
 * center of the coordinate system will be the largest. To copy data from one line to another,
 * use {@link #Line2D(Line2D) the copy constructor}. To get a string representation,
 * {@link EquationBuilder an inner class } is used, containing the necessary methods for checking each coefficient.
 * Creates an equation depending on the value of each coefficient. Zero coefficients are not taken into account
 * (for example, <i>5x + 12 = 0, 4y = 0</i>).<br>
 * Supports the following mathematical operations:
 * <ul>
 *  <li>{@link #includesPoint(Point2D) determine} whether a line includes some point;</li>
 *  <li>{@link #isParallelToX() determine} whether a line is parallel to the OX axis;</li>
 *  <li>{@link #isParallelToY() determine} whether a line is parallel to the OY axis;</li>
 *  <li>{@link #passesOrigin() determine} whether a line passes through the center of coordinates;</li>
 *  <li>{@link #getYView() convert} the equation of a line to an equation resolved with respect to the ordinate
 *  (with an angular coefficient);</li>
 *  <li>{@link #shiftOrigin(float, float) shift the center of coordinates} and calculate the new equation of a line;</li>
 *  <li>{@link #rotateAxes(float) rotate the coordinate axes} and calculate the new equation of a line.</li>
 * </ul>
 *
 * @since 0.1
 * @author Ortodontalio
 */
public class Line2D implements Serializable, Comparable<Line2D> {
    /**
     * Coefficient A (with x) in the general equation.
     */
    private final float a;
    /**
     * Coefficient B (with y) in the general equation.
     */
    private final float b;
    /**
     * Coefficient C in the general equation.
     */
    private final float c;
    /**
     * {@link EquationBuilder An internal class} object used to construct a string representation of a line.
     */
    private final transient EquationBuilder equation;
    private static final long serialVersionUID = 20L;

    /**
     * Initialization constructor. Assign values directly to coefficients. Prohibits the creation of
     * lines with zero values of coefficients A and B at the same time.
     * @param a is A coefficient;
     * @param b is B coefficient;
     * @param c is C coefficient.
     *
     * @throws LineOperationException if an attempt is made to create a line with zero parameters A and B.
     */
    public Line2D(final float a, final float b, final float c) {
        if (a == 0 && b == 0) {
            throw new LineOperationException(LineOperationException.INCORRECT_CREATION);
        }
        this.a = a;
        this.b = b;
        this.c = c;
        this.equation = new EquationBuilder();
    }

    /**
     * Modeling constructor. Creates a line from a segment. Uses the derived formula from the
     * second-order determinant, which allows to find the equation of a line on two points:<br>
     * <code>(x2-x1)(y-y1) - (x-x1)(y2-y1) = 0.</code>
     * @param segment is a segment object [A;B].
     */
    public Line2D(final Segment2D segment) {
        final float x1 = segment.getA().getX();
        final float x2 = segment.getB().getX();
        final float y1 = segment.getA().getY();
        final float y2 = segment.getB().getY();
        this.a = y1 - y2;
        this.b = x2 - x1;
        this.c = x1*y2 - x2*y1;
        this.equation = new EquationBuilder();
    }

    /**
     * Copy constructor, that transfers parameters from another line.
     * @param line is another line, whose parameters need to be moved to a new line.
     */
    public Line2D(final Line2D line) {
        this.a = line.getA();
        this.b = line.getB();
        this.c = line.getC();
        this.equation = new EquationBuilder();
    }

    /**
     * Getter-method for getting the value of the {@link #a a} field.
     * @return coefficient A in the general equation.
     */
    public float getA() {
        return this.a;
    }

    /**
     * Getter-method for getting the value of the {@link #b b} field.
     * @return coefficient B in the general equation.
     */
    public float getB() {
        return this.b;
    }

    /**
     * Getter-method for getting the value of the {@link #c c} field.
     * @return coefficient C in the general equation.
     */
    public float getC() {
        return this.c;
    }

    /**
     * Method, that determines whether a point belongs to this line. To do this, uses the general equation
     * of the line - <code>Ax + By + C = 0</code>.
     * @param point the object of the point, for which it is necessary to determine its belonging to the line.
     * @return true, if the point belongs to the line, otherwise - false.
     */
    public boolean includesPoint(Point2D point) {
        return (this.a*point.getX() + this.b*point.getY() + this.c) == 0;
    }

    /**
     * Method, that determines whether this line is parallel to the OХ axis. To fulfill this condition,
     * the coefficient {@link #a A} must be zero in order for the equation to become solvable with respect to Y -
     * <code>By + C = 0</code>
     * @return true, if this line is parallel to the OХ axis, otherwise - false.
     */
    public boolean isParallelToX() {
        return this.a == 0;
    }

    /**
     * Method, that determines whether this line is parallel to the OY axis. To fulfill this condition,
     * the coefficient {@link #b B} must be zero in order for the equation to become solvable with respect to X -
     * <code>Ax + C = 0</code>
     * @return true, if this line is parallel to the OY axis, otherwise - false.
     */
    public boolean isParallelToY() {
        return this.b == 0;
    }

    /**
     * Method, that determines whether this line passes through the center of the coordinate system.
     * To fulfill this condition, the coefficient {@link #c C} must be zero, i.e. there must be no shift.
     * @return true, if this line passes through the center of the coordinate system, otherwise - false.
     */
    public boolean passesOrigin() {
        return this.c == 0;
    }

    /**
     * Method, that converts the equation of a line from a general one to an equation
     * resolved with respect to the ordinate, i.e. from <code>Ax + By + C = 0</code> to <code>y = kx + b</code>.
     * Restricts the conversation, if it turns out that the line is parallel to the OY axis.
     * @return an object of the {@link LineY2D LineY2D class}, whose parameters correspond to the parameters
     * of the current line.
     * @throws LineOperationException if the line is parallel to the OY axis.
     */
    public LineY2D getYView() {
        if (isParallelToY()) {
            throw new LineOperationException(LineOperationException.B_NOT_NULL);
        }
        final float k = -(this.a/this.b);
        final float b_coeff = -(this.c/this.b);
        return new LineY2D(k, b_coeff);
    }

    /**
     * Method, that calculates the new line's parameters, when the center of the coordinate system is shifted
     * to new coordinates. Uses the transformed general equation of the line, with the following replacement:
     * <code>x = x' + x0, y = y' + y0</code>, where x, y = current line's x and y coordinates, x',y' = new
     * line's x and y coordinates, x0,y0 - coordinates of new origin (default coordinates - O(0;0)).
     * @param x coordinate X of new origin.
     * @param y coordinate Y of new origin.
     * @return new {@link Line2D line object} with shifted origin.
     */
    public Line2D shiftOrigin(final float x, final float y) {
        return new Line2D(this.a, this.b, this.a*x + this.b*y + this.c);
    }

    /**
     * Method, that calculates the new line's parameters, when the axes are rotated by a certain angle.
     * Uses the transformed general equation of the line, with the following replacement:
     * <code>x = x'cosa - y'sina, y = x'sina + y'cosa</code>, where x, y = current line's x and y coordinates, x',y' = new
     * line's x and y coordinates, a - axis rotation angle.
     * @param deg the angle by which the OX and OY axes should be rotated, in degrees.
     * @return new {@link Line2D line object} with rotated axes.
     */
    public Line2D rotateAxes(final float deg) {
        final float radians = (float) Math.toRadians(deg);
        final float cosA = (float) Math.cos(radians);
        final float sinA = (float) Math.sin(radians);
        return new Line2D(this.a*cosA + this.b*sinA, this.b*cosA - this.a*sinA, this.c);
    }

    /**
     * Overriding equals method. To determine equivalence, uses the condition of parallelism of
     * lines with additional equality of free terms:<br>
     * <code>A1/A2 = B1/B2 = C1/C2</code>
     * @see Object#equals(Object)
     * @param o the object to compare the current object with.
     * @return true, if the <b>all proportions of coefficients are equal</b>, otherwise - false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line2D line2D = (Line2D) o;
        return Math.abs(this.a*line2D.getB() - line2D.getA()*this.b) < Constants.EPSILON.getValue() &&
                Math.abs(this.a*line2D.getC() - line2D.getA()*this.c) < Constants.EPSILON.getValue() &&
                Math.abs(this.b*line2D.getC() - line2D.getB()*this.c) < Constants.EPSILON.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getA(), getB(), getC());
    }

    /**
     * Overloaded version of {@link #toString toString} method. Allows specifying custom localization
     * to print either commas to separate the whole and fractional parts, or dots.
     * @param locale custom localization.
     * @return string line representation in the general equation.
     */
    public String toString(Locale locale) {
        return equation.fullEquation(locale);
    }

    /**
     * Calls an {@link #toString(Locale) overloaded version}, only with the default localization.
     * Prints two decimal places. Returns the representation of line in general equation - <i>Ax + By + C = 0</i>.<br>
     * For example, 5.23x + 5.00y - 6.11 = 0, 6.99y + 5.01 = 0, 9.12x = 0 and so on.
     * @return string standardized representation of a point.
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
    public int compareTo(Line2D o) {
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

    /**
     * Private inner class, designed to construct a string representation of a {@link Line2D} object.
     * It contains {@link #fullEquation(Locale) the main construction method} and private ones
     * that create an equation depending on the parameter values. If some parameter is missing,
     * it does not take it into account in the final equation.
     */
    private final class EquationBuilder {

        /**
         * Private auxiliary method for constructing a general equation of a straight line.
         * Checks whether {@link #a the coefficient A} is equal to zero, in this case skips it.
         * @param locale custom localization.
         * @param equation an equation object constructed step by step in each auxiliary method.
         * @return the object of the string representation of {@link Line2D}, obtained by analyzing
         * {@link #a the parameter A}.
         */
        private StringBuilder convertA(final Locale locale, final StringBuilder equation) {
            if (a == 0) {
                return equation;
            }
            return equation.append(String.format(locale, "%.2fx ", a));
        }

        /**
         * Private auxiliary method for constructing a general equation of a straight line.
         * Checks whether {@link #b the coefficient B} is equal to zero, in this case skips it.
         * @param locale custom localization.
         * @param equation an equation object constructed step by step in each auxiliary method.
         * @return the object of the string representation of {@link Line2D}, obtained by analyzing
         * {@link #b the parameter B}.
         */
        private StringBuilder convertB(final Locale locale, final StringBuilder equation) {
            if (a != 0) {
                if (b > 0) {
                    return equation.append(String.format(locale, "+ %.2fy ", b));
                }
                if (b < 0) {
                    return equation.append(String.format(locale, "- %.2fy ", -b));
                }
                return equation;
            }
            if (b != 0) {
                return equation.append(String.format(locale, "%.2fy ", b));
            }
            return equation;
        }

        /**
         * Private auxiliary method for constructing a general equation of a straight line.
         * Checks whether {@link #c the coefficient C} is equal to zero, in this case skips it.
         * @param locale custom localization.
         * @param equation an equation object constructed step by step in each auxiliary method.
         * @return the object of the string representation of {@link Line2D}, obtained by analyzing
         * {@link #c the parameter C}.
         */
        private StringBuilder convertC(final Locale locale, final StringBuilder equation) {
            if (a == 0 && b == 0) {
                return equation.append(String.format(locale, "%.2f = 0", c));
            } else {
                if (c > 0) {
                    return equation.append(String.format(locale, "+ %.2f = 0", c));
                }
                return equation.append(String.format(locale, "- %.2f = 0", -c));
            }
        }

        /**
         * Method, that calls private additional methods to construct a string representation of {@link Line2D a line object}.
         * When creating a string, zero parameters are omitted, for example, the full equation is <code>Ax + By + C = 0</code>.
         * If {@link Line2D#a param A} is zero, then the full equation will be <code>By + C = 0</code>.
         * The same situation with others parameters. Note, that A and B parameters can not be zero at the same time.
         * @param locale custom localization.
         * @return string line representation in the general equation.
         */
        public String fullEquation(Locale locale) {
            return convertC(locale, convertB(locale, convertA(locale, new StringBuilder()))).toString();
        }
    }
}
