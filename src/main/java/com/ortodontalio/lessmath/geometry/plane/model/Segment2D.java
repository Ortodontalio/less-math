package com.ortodontalio.lessmath.geometry.plane.model;

import com.ortodontalio.lessmath.exceptions.SegmentOperationException;
import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;

/**
 * A class, representing a segment in two-dimensional space. Uses a <i>rectangular coordinate system</i>.
 * The segment consists of {@link Point2D two points} - the ends of the segment. All class properties are
 * <b>immutable</b>, however, the class is semi-immutable, since it can be extended.
 * {@link #toString() A string representation} of the segment is constructed from the names of the points
 * and their coordinates. In addition, the class contains a characteristic {@link #length length}, which is
 * automatically calculated when creating a segment. To copy data from one segment to another, use
 * {@link #Segment2D(Segment2D) the copy constructor}.<br>
 * Supports the following mathematical operations:
 * <ul>
 *  <li>{@link #findSplitterPoint(char, int, int) determine} the point, that divides the segment in the specified
 *  ratio.</li>
 * </ul>
 *
 * @since 0.1
 * @author Ortodontalio
 */
public class Segment2D implements Serializable, Comparable<Segment2D> {

    /**
     * The first point is the end of the segment.
     */
    private final Point2D a;
    /**
     * The second point is the end of the segment.
     */
    private final Point2D b;
    /**
     * Length of the segment.
     */
    private final float length;
    private static final long serialVersionUID = 10L;

    /**
     * Initialization constructor. Assign values directly to the ends of segment. Prohibits the creation of a
     * zero segment - a segment with zero length.
     * @param a is the first point is the end of the segment.
     * @param b is the second point is the end of the segment.
     *
     * @throws SegmentOperationException if an attempt is made to create a segment with zero length.
     */
    public Segment2D(final Point2D a, final Point2D b) {
        if (a.equals(b)) {
            throw new SegmentOperationException(SegmentOperationException.INCORRECT_CREATION);
        }
        this.a = a;
        this.b = b;
        this.length = initializeLength();
    }

    /**
     * Copy constructor, that transfers points from another segment.
     * @param segment is another segment, whose points need to be moved to a new segment.
     */
    public Segment2D(final Segment2D segment) {
        this.a = segment.getA();
        this.b = segment.getB();
        this.length = initializeLength();
    }

    /**
     * Getter-method for getting a copy of value of the {@link #a A} field.
     * @return the first point is the end of the segment.
     */
    public Point2D getA() {
        return new Point2D(this.a.getName(), this.a);
    }

    /**
     * Getter-method for getting a copy of value of the {@link #b B} field.
     * @return the second point is the end of the segment.
     */
    public Point2D getB() {
        return new Point2D(this.b.getName(), this.b);
    }

    /**
     * Method, that searches for a point on a segment that divides this segment in the specified ratio -
     * <code>numerator/denominator</code>. Uses the following mathematical formulas: <br>
     * <code>x = (m2x1 + m1x2)/(m1 + m2);</code><br>
     * <code>y = (m2y1 + m1y2)/(m1 + m2),</code><br>
     * where m1 is numerator and m2 is denominator. Use the ratio 1/1 to find the middle of the segment.
     * @param name is letter designation of the found point.
     * @param numerator is the upper term of the ratio.
     * @param denominator is the lower term of the ratio.
     * @return {@link Point2D a new point object}, containing the coordinates of the point, that divides the
     * segment in the specified ratio.
     */
    public Point2D findSplitterPoint(char name, int numerator, int denominator) {
        final float x = (denominator*a.getX() + numerator*b.getX()) / (numerator + denominator);
        final float y = (denominator*a.getY() + numerator*b.getY()) / (numerator + denominator);
        return new Point2D(name, x, y);
    }

    /**
     * Private initialization method for {@link #length the length field}. Uses the following formula:
     * <code>length = √(x2-x1)²+(y2-y1)²</code>
     * @return the length of the segment found by the formula.
     */
    private float initializeLength() {
        return (float) Math.sqrt(
                Math.pow(this.b.getX() - this.a.getX(), 2) + Math.pow(this.b.getY() - this.a.getY(), 2)
        );
    }

    /**
     * Getter-method for getting the value of the {@link #length length} field.
     * @return the length of the segment.
     */
    public float length() {
        return this.length;
    }

    /**
     * Overriding equals method.
     * @see Object#equals(Object)
     * @param o the object to compare the current object with.
     * @return true, if the <b>length of the segments coincide</b>, otherwise - false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Segment2D segment2D = (Segment2D) o;
        return Float.compare(segment2D.length, length) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(length);
    }

    /**
     * Calls an {@link #toString(Locale) overloaded version}, only with the default localization.
     * Prints two decimal places. Returns the representation of a segment in the next version -
     * <i>{char-A}{char-B}(X1; Y1; X2; Y2)</i>.<br>
     * For example, if the segment is created using the following points A(2.04;12.00) and B(12.32;7.00), then
     * AB(2.04;12.00;12.32;7.00) and so on.
     * @return string standardized representation of a segment.
     */
    @Override
    public String toString() {
        return toString(Locale.getDefault());
    }

    /**
     * Overloaded version of {@link #toString toString} method. Allows specifying custom localization
     * to print either commas to separate the whole and fractional parts, or dots.
     * @param locale custom localization.
     * @return string specific representation of a segment.
     */
    public String toString(Locale locale) {
        return String.format(locale, "%c%c(%.2f;%.2f;%.2f;%.2f)", this.a.getName(), this.b.getName(),
                this.a.getX(), this.a.getY(), this.b.getX(), this.b.getY());
    }

    /**
     * Implemented comparator method. Uses the value of {@link #length length} field to compare the segments.
     * @param o other segment object.
     * @return 1, if this segment has a longer length than the other segment;<br>
     *         0, if these segments have the same length;<br>
     *         -1, if this segment has a shorter length than the other segment.<br>
     */
    @Override
    public int compareTo(Segment2D o) {
        if (this.length > o.length()) {
            return 1;
        } else {
            if (this.length == o.length()) {
                return 0;
            }
        }
        return -1;
    }
}
