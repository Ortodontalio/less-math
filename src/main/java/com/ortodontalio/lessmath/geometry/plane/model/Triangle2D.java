package com.ortodontalio.lessmath.geometry.plane.model;

import java.io.Serializable;
import java.util.Locale;

/**
 * A class, representing an elementary triangle shape in two-dimensional space. Uses a
 * <i>rectangular coordinate system</i>. It is created using three segments that must be connected to each other,
 * forming a triangle. During the construction of a triangle, its internal angles and {@link #area area} are also
 * calculated. All class properties are <b>immutable</b>, however, the class is semi-immutable, since it can be
 * extended. Objects of this class can be sorted, and <i>the largest triangle</i> has the largest area. To copy data from
 * one triangle to another, use {@link #Triangle2D(Triangle2D) the copy constructor}.
 * Supports the following mathematical operations:
 * <ul>
 *  <li>{@link #getFirstSideMedian(char) get} a segment-median that is drawn from the vertex of the triangle
 *  to the opposite side;</li>
 *  <li>{@link #isRectangular() determine} that the triangle is rectangular;</li>
 *  <li>{@link #isIsosceles() determine} that the triangle is isosceles;</li>
 *  <li>{@link #isEquilateral() determine} that the triangle is equilateral.</li>
 * </ul>
 *
 * @since 0.1
 * @author Ortodontalio
 */
public class Triangle2D implements Serializable {

    private final Segment2D firstSide;
    private final Segment2D secondSide;
    private final Segment2D thirdSide;
    private final float firstAngle;
    private final float secondAngle;
    private final float thirdAngle;
    private final float area;
    private static final long serialVersionUID = 40L;

    /**
     * Initialization constructor. It takes three points, which connects to each other, forming a triangle,
     * and the resulting connections are converted into segments. Calculates the area of the triangle,
     * as well as the angles between the sides.
     * @param a is the first side of the triangle.
     * @param b is the second side of the triangle.
     * @param c is the third side of the triangle.
     */
    public Triangle2D(Point2D a, Point2D b, Point2D c) {
        this.firstSide = new Segment2D(a, b);
        this.secondSide = new Segment2D(b, c);
        this.thirdSide = new Segment2D(c, a);
        this.area = initializeArea();
        this.firstAngle = initializeAngle(this.thirdSide, this.firstSide);
        this.secondAngle = initializeAngle(this.firstSide, this.secondSide);
        this.thirdAngle = initializeAngle(this.secondSide, this.thirdSide);
    }

    /**
     * Copy constructor, that transfers segments from another triangle.
     * @param triangle is another triangle, whose sides need to be moved to a new triangle.
     */
    public Triangle2D(Triangle2D triangle) {
        this.firstSide = triangle.getFirstSide();
        this.secondSide = triangle.getSecondSide();
        this.thirdSide = triangle.getThirdSide();
        this.area = initializeArea();
        this.firstAngle = initializeAngle(this.thirdSide, this.firstSide);
        this.secondAngle = initializeAngle(this.firstSide, this.secondSide);
        this.thirdAngle = initializeAngle(this.secondSide, this.thirdSide);
    }

    /**
     * Getter-method for getting a copy of value of the {@link #firstSide first side} field.
     * @return the first side of the triangle as a 2D segment.
     */
    public Segment2D getFirstSide() {
        return new Segment2D(firstSide);
    }

    /**
     * Getter-method for getting a copy of value of the {@link #secondSide second side} field.
     * @return the second side of the triangle as a {@link Segment2D 2D segment}.
     */
    public Segment2D getSecondSide() {
        return new Segment2D(secondSide);
    }

    /**
     * Getter-method for getting a copy of value of the {@link #thirdSide third side} field.
     * @return the third side of the triangle as a 2D segment.
     */
    public Segment2D getThirdSide() {
        return new Segment2D(thirdSide);
    }

    /**
     * Private method for initialization of such fields, as {@link #firstAngle first angle},
     * {@link #secondAngle second angle} and {@link #thirdAngle the last one angle}. Uses the following formula
     * to calculate angles:
     * <code>S = 0.5ABsinc</code>, where S - the triangle area, A and B - two adjacent sides of the triangle,
     * and sinc - the sin of the angle between them.
     *
     * @param firstSide is the first side between which the desired angle is located.
     * @param otherSide is the second side between which the desired angle is located.
     * @return the calculated angle of the triangle.
     */
    private float initializeAngle(Segment2D firstSide, Segment2D otherSide) {
        final float firstLength = firstSide.length();
        final float secondLength = otherSide.length();
        return (float) Math.toDegrees(Math.asin((2*this.area)/(firstLength*secondLength)));
    }

    /**
     * Private method for initialization of the triangle {@link #area area}. Uses the following formula
     * to calculate an area:
     * <code>S = 0.5((x1-x3)(x2-x3) - (y2-y3)(y1-y3))</code>, where S - the triangle square, x1,y1 - coordinates of the
     * first point of the triangle, x2,y2 - coordinates of the second point, x3,y3 - coordinates of the last one point.
     * @return the calculated area of the triangle.
     */
    private float initializeArea() {
        final float ax = firstSide.getA().getX();
        final float ay = firstSide.getA().getY();
        final float bx = firstSide.getB().getX();
        final float by = firstSide.getB().getY();
        final float cx = secondSide.getB().getX();
        final float cy = secondSide.getB().getY();
        return (float)Math.abs(0.5*((ax - cx)*(by - cy) - (bx - cx)*(ay - cy)));
    }

    /**
     * Getter-method for getting a copy of value of the {@link #area area} field.
     * @return an area of the triangle.
     */
    public float area() {
        return this.area;
    }

    /**
     * Getter-method for getting a copy of value of the {@link #firstAngle first angle} field.
     * @return the first angle between {@link #thirdSide the third side} and {@link #firstSide the first side} of the
     * triangle in degrees.
     */
    public float getFirstAngle() {
        return firstAngle;
    }

    /**
     * Getter-method for getting a copy of value of the {@link #secondAngle second angle} field.
     * @return the second angle between {@link #firstSide the first side} and {@link #secondSide the second side} of the
     * triangle in degrees.
     */
    public float getSecondAngle() {
        return secondAngle;
    }

    /**
     * Getter-method for getting a copy of value of the {@link #thirdAngle third angle} field.
     * @return the third angle between {@link #secondSide the second side} and {@link #thirdSide the third side} of the
     * triangle in degrees.
     */
    public float getThirdAngle() {
        return thirdAngle;
    }

    /**
     * Method for obtaining a median, the first point of which is {@link Segment2D#getB() the second point} of
     * {@link #secondSide the second side} and the second point is the middle of {@link #firstSide the first side}.
     * @param name is the name of the middle point of {@link #firstSide the first side}.
     * @return the first median as a {@link Segment2D 2D segment}.
     */
    public Segment2D getFirstSideMedian(final char name) {
        return new Segment2D(secondSide.getB(), firstSide.findSplitterPoint(name,1,1));
    }

    /**
     * Method for obtaining a median, the first point of which is {@link Segment2D#getA() the first point} of
     * {@link #firstSide the first side} and the second point is the middle of {@link #secondSide the second side}.
     * @param name is the name of the middle point of {@link #secondSide the second side}.
     * @return the second median as a {@link Segment2D 2D segment}.
     */
    public Segment2D getSecondSideMedian(final char name) {
        return new Segment2D(firstSide.getA(), secondSide.findSplitterPoint(name,1,1));
    }

    /**
     * Method for obtaining a median, the first point of which is {@link Segment2D#getB() the second point} of
     * {@link #firstSide the first side} and the second point is the middle of {@link #thirdSide the third side}.
     * @param name is the name of the middle point of {@link #thirdSide the third side}.
     * @return the third median as a {@link Segment2D 2D segment}.
     */
    public Segment2D getThirdSideMedian(final char name) {
        return new Segment2D(firstSide.getB(), thirdSide.findSplitterPoint(name,1,1));
    }

    /**
     * Method, that determines whether the triangle is rectangular.
     * @return true, if <b>one of its angles is 90 degrees</b>, otherwise return false.
     */
    public boolean isRectangular() {
        return this.firstAngle == 90 || this.secondAngle == 90 || this.thirdAngle == 90;
    }

    /**
     * Method, that determines whether the triangle is isosceles.
     * @return true, if <b>any of its two sides are equal</b>, otherwise return false.
     */
    public boolean isIsosceles() {
        return this.firstSide.equals(this.secondSide) || this.firstSide.equals(this.thirdSide) ||
                this.secondSide.equals(this.thirdSide);
    }

    /**
     * Method, that determines whether the triangle is equilateral.
     * @return true, if <b>all of its sides are equal</b>, otherwise return false.
     */
    public boolean isEquilateral() {
        return this.firstSide.equals(this.secondSide) && this.firstSide.equals(this.thirdSide);
    }

    /**
     * Calls an {@link #toString(Locale) overloaded version}, only with the default localization.
     * Prints two decimal places. Returns the representation of the triangle in the next version -
     * <i>{char-A}{char-B}{char-C}(area)</i>.<br>
     * For example, if the segment is created using the following points A(1.00;3.00), B(2.00;-5.00) and
     * C(-8.00;4.00), then ABC(35.50) and so on.
     * @return string standardized representation of the triangle.
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
        return String.format(locale, "%c%c%c(%.2f)",firstSide.getA().getName(), secondSide.getA().getName(),
                thirdSide.getA().getName(), this.area);
    }

}
