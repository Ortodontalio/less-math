package com.ortodontalio.lessmath.geometry.plane.calculation;

import com.ortodontalio.lessmath.exceptions.LineOperationException;
import com.ortodontalio.lessmath.geometry.plane.model.Point2D;
import com.ortodontalio.lessmath.geometry.plane.model.Line2D;
import com.ortodontalio.lessmath.geometry.plane.model.LineY2D;

/**
 * Utility class for mathematical calculations using {@link Line2D lines}. Formulas from higher mathematics
 * are mainly used. Supports the following mathematical operations:
 * <ul>
 *  <li>{@link #areParallel(Line2D, Line2D) determine} whether the lines are parallel;</li>
 *  <li>{@link #arePerpendicular(Line2D, Line2D) determine} whether the lines are perpendicular;</li>
 *  <li>{@link #findIntersection(char, Line2D, Line2D) determine} the intersection point of two lines;</li>
 *  <li>{@link #findAngleBetween(Line2D, Line2D) determine} the angle (in degrees) between two lines;</li>
 *  <li>{@link #findLineByAngle(float, LineY2D) determine} a new line having the specified angle with the line;</li>
 *  <li>{@link #findParallelLinePassingThroughPoint(Point2D, Line2D) determine} a new line passing through the
 *  specified point parallel to the line;</li>
 *  <li>{@link #findPerpendicularLinePassingThroughPoint(Point2D, Line2D) determine} a new line passing through the
 *  specified point perpendicular to the line.</li>
 * </ul>
 *
 * @since 0.1
 * @author Ortodontalio
 */
public class LineProcessor {

    /**
     * Default constructor. Defined as private to prohibit the creation of objects of this class,
     * since it doesn't contain any other methods besides static ones.
     */
    private LineProcessor() {}

    /**
     * Method, that determines whether the lines are parallel.
     * In order for the two lines to be parallel, the following condition must be met: <br>
     * <code>A1B2 - A2B1 = 0</code>, where A1, B1 are the coefficients of the first line, A2, B2 are the coefficients
     * of the second line.
     * @param first is the first line.
     * @param second is the second line.
     * @return true, if the lines are parallel, otherwise - false.
     */
    public static boolean areParallel(final Line2D first, final Line2D second) {
        return (first.getA()*second.getB() - second.getA()*first.getB()) == 0;
    }

    /**
     * Overloaded version of {@link #areParallel(Line2D, Line2D) areParallel method}. Unlike the other version
     * of the method, it defines parallelism for lines in the equation with angular coefficient.
     * In order for the two lines to be parallel, their {@link LineY2D#getK() angular coefficients} should be equal:
     * <br><code>K1 = K2</code>
     * @param first is the first line.
     * @param second is the second line.
     * @return true, if the lines are parallel, otherwise - false.
     */
    public static boolean areParallel(final LineY2D first, final LineY2D second) {
        return first.getK() == second.getK();
    }

    /**
     * Method, that determines whether the lines are perpendicular.
     * In order for the two lines to be perpendicular, the following condition must be met: <br>
     * <code>A1A2 + B1B2 = 0</code>, where A1, B1 are the coefficients of the first line, A2, B2 are the coefficients
     * of the second line.
     * @param first is the first line.
     * @param second is the second line.
     * @return true, if the lines are perpendicular, otherwise - false.
     */
    public static boolean arePerpendicular(final Line2D first, final Line2D second) {
        return (first.getA()*second.getA() + first.getB()*second.getB()) == 0;
    }

    /**
     * Overloaded version of {@link #arePerpendicular(Line2D, Line2D) arePerpendicular method}. Unlike the other version
     * of the method, it defines perpendicular for lines in the equation with angular coefficient.
     * In order for the two lines to be perpendicular, the following condition must be met:
     * <br><code>K1K2 = -1</code>, where K1,K2 are angular coefficients of the lines.
     * @param first is the first line.
     * @param second is the second line.
     * @return true, if the lines are perpendicular, otherwise - false.
     */
    public static boolean arePerpendicular(final LineY2D first, final LineY2D second) {
        return first.getK()*second.getK() == -1;
    }

    /**
     * Method, that determines the intersection point of two lines. It is necessary to solve the following system
     * of equations to find this point: <br>
     * <code>A1x + B1y + C1 = 0 and A2x + B2y + C2 = 0</code>, where A1, B1, C1 are the coefficients of the first line,
     * A2, B2, C2 are the coefficients of the second line and x, y are the coordinates of the point should be found.
     * @param name is the name of the point should be found.
     * @param first is the first line.
     * @param second is the second line.
     * @return the intersection point of two lines.
     *
     * @throws LineOperationException if the first line coincides with the second, i.e. there are infinitely many
     * points of intersection or two lines are parallel to each other, i.e. there are no such points.
     */
    public static Point2D findIntersection(final char name, final Line2D first, final Line2D second) {
        if (first.equals(second)) {
            throw new LineOperationException(LineOperationException.INFINITY_POINTS);
        }
        if (areParallel(first, second)) {
            throw new LineOperationException(LineOperationException.NO_POINTS);
        }
        final float determinant = first.getA()*second.getB() - second.getA()*first.getB();
        final float x = (second.getC()*first.getB() - first.getC()*second.getB()) / determinant;
        final float y = (first.getC()*second.getA() - second.getC()* first.getA()) / determinant;
        return new Point2D(name, x, y);
    }

    /**
     * Method, that determine the angle (in degrees) between two lines. Uses the following formula
     * to calculate the angle: <br>
     * <code>tga = (A1B2 - A2B1)/(A1A2 + B1B2)</code>, where A1, B1 are the coefficients of the first line and
     * A2, B2 are the coefficients of the second line.
     * @param first is the first line, between which the desired angle is located.
     * @param second is the second line, between which the desired angle is located.
     * @return the angle (in degrees) between two lines.
     */
    public static float findAngleBetween(final Line2D first, final Line2D second) {
        if (arePerpendicular(first, second)) {
            return 90f;
        }
        if (areParallel(first, second)) {
            return 0;
        }
        final float a1 = first.getA();
        final float a2 = second.getA();
        final float b1 = first.getB();
        final float b2 = second.getB();
        final float tan = (a1*b2 - a2*b1)/(a1*a2 + b1*b2);
        return (float) Math.toDegrees(Math.atan(tan));
    }

    /**
     * Overloaded version of {@link #findAngleBetween(Line2D, Line2D) findAngleBetween method}. Unlike the other version
     * of the method, it determines the angle between the lines in the equation with angular coefficient, i.e. the
     * formula for the desired angle becomes:<br>
     * <code>tga = (K2 - K1)/(1 + K1K2)</code>, where K1, K2 are angular coefficients of the first and second lines.
     * @param first is the first line, between which the desired angle is located.
     * @param second is the second line, between which the desired angle is located.
     * @return the angle (in degrees) between two lines.
     */
    public static float findAngleBetween(final LineY2D first, final LineY2D second) {
        if (arePerpendicular(first, second)) {
            return 90f;
        }
        if (areParallel(first, second)) {
            return 0;
        }
        final float k1 = first.getK();
        final float k2 = second.getK();
        final float tan = (k2 - k1)/(1 + k1*k2);
        return (float) Math.toDegrees(Math.atan(tan));
    }

    /**
     * Method, that determines a new line having the specified angle with the specified one line.
     * Uses the following formula to find this line: <br>
     * <code>tga = (K2 - K1)/(1 + K1K2)</code>, where K1 is the angular coefficient of the first line and
     * K2 is the angular coefficient of the desired line.
     * @param degrees is the angle (in degrees) between the specified line and the desired one.
     * @param line is the original line.
     * @return the line having the specified angle with the specified one line.
     */
    public static LineY2D findLineByAngle(float degrees, final LineY2D line) {
        final float k1 = line.getK();
        final float tan = (float) Math.tan(Math.toRadians(degrees));
        final float k2 = (-tan - k1)/(tan*k1 - 1);
        return new LineY2D(k2, 0);
    }

    /**
     * Method, that determines a new line passing through the specified point parallel to the specified line.
     * A line passing through the specified point M(x1;y1) and parallel to the specified line Ax + By + C = 0 is
     * represented by the equation: <br>
     * <code>A(x - x1) + B(y - y1) = 0</code>
     * @param point is the point, through which the line parallel to the specified line passes.
     * @param line is the line parallel to which the desired line passes.
     * @return a new line passing through the specified point parallel to the specified line.
     */
    public static Line2D findParallelLinePassingThroughPoint(final Point2D point, final Line2D line) {
        return new Line2D(line.getA(), line.getB(), -line.getA()*point.getX() - line.getB()*point.getY());
    }

    /**
     * Overloaded version of {@link #findParallelLinePassingThroughPoint(Point2D, Line2D)
     * findParallelLinePassingThroughPoint method}. Unlike the other version of the method, it determines
     * a new line passing through the specified point parallel to the specified line in the equation with
     * angular coefficient, i.e. the formula for the desired line becomes:<br>
     * <code>y - y1 = k(x - x1)</code>
     * @param point is the point, through which the line parallel to the specified line passes.
     * @param line is the line parallel to which the desired line passes.
     * @return a new line passing through the specified point parallel to the specified line.
     */
    public static LineY2D findParallelLinePassingThroughPoint(final Point2D point, final LineY2D line) {
        return new LineY2D(line.getK(), -point.getX()*line.getK() + point.getY());
    }

    /**
     * Method, that determines a new line passing through the specified point perpendicular to the specified line.
     * A line passing through the specified point M(x1;y1) and perpendicular to the specified line Ax + By + C = 0 is
     * represented by the equation: <br>
     * <code>A(y - y1) + B(x - x1) = 0</code>
     * @param point is the point, through which the line perpendicular to the specified line passes.
     * @param line is the line perpendicular to which the desired line passes.
     * @return a new line passing through the specified point perpendicular to the specified line.
     */
    public static Line2D findPerpendicularLinePassingThroughPoint(final Point2D point, final Line2D line) {
        return new Line2D(line.getB(), line.getA() != 0 ? -line.getA() : 0,
                line.getA()*point.getY() - line.getB()*point.getX());
    }

    /**
     * Overloaded version of {@link #findPerpendicularLinePassingThroughPoint(Point2D, Line2D)
     * findParallelLinePassingThroughPoint method}. Unlike the other version of the method, it determines
     * a new line passing through the specified point perpendicular to the specified line in the equation with
     * angular coefficient, i.e. the formula for the desired line becomes:<br>
     * <code>y - y1 = -1/k(x - x1)</code>
     * @param point is the point, through which the line perpendicular to the specified line passes.
     * @param line is the line perpendicular to which the desired line passes.
     * @return a new line passing through the specified point perpendicular to the specified line.
     */
    public static LineY2D findPerpendicularLinePassingThroughPoint(final Point2D point, final LineY2D line) {
        return new LineY2D(-1/line.getK(), point.getX()*(1/line.getK()) + point.getY());
    }
}
