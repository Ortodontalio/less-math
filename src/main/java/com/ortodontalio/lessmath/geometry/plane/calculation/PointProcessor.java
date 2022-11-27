package com.ortodontalio.lessmath.geometry.plane.calculation;

import com.ortodontalio.lessmath.geometry.plane.model.Point2D;
import com.ortodontalio.lessmath.geometry.plane.model.Line2D;
import com.ortodontalio.lessmath.geometry.plane.model.LineY2D;

/**
 * Utility class for mathematical calculations using {@link Point2D points}. Formulas from higher mathematics
 * are mainly used. Supports the following mathematical operations:
 * <ul>
 *  <li>{@link #lieOnSameLine(Point2D, Point2D, Point2D) determine} whether the points lie on the same line;</li>
 *  <li>{@link #lieOnSameSides(Point2D, Point2D, Line2D) determine} whether the points lie on the same sides;</li>
 *  <li>{@link #distanceToLine(Point2D, Line2D) determine} the distance from the point to the specified line.</li>
 * </ul>
 *
 * @since 0.1
 * @author Ortodontalio
 */
public class PointProcessor {

    /**
     * Default constructor. Defined as private to prohibit the creation of objects of this class,
     * since it doesn't contain any other methods besides static ones.
     */
    private PointProcessor() {}

    /**
     * Method, that determines whether the specified three points lie on the same line. In order for three points to
     * lie on the same line, the following condition must be met: <br>
     * <code>((x2-x1)(y3-y1) - (x3-x1)(y2-y1)) = 0</code>, where x1,y1 - coordinates of the
     * first point, x2,y2 - coordinates of the second point, x3,y3 - coordinates of the last one point.
     * @param first is the first point, f.e, A(x1;y1).
     * @param second is the second point, f.e B(x2;y2).
     * @param third is the second point, f.e C(x3;y3).
     * @return true, if the points lie on the same line, otherwise - false.
     */
    public static boolean lieOnSameLine(final Point2D first, final Point2D second, final Point2D third) {
        final float x1 = first.getX();
        final float x2 = second.getX();
        final float x3 = third.getX();
        final float y1 = first.getY();
        final float y2 = second.getY();
        final float y3 = third.getY();
        return x1*(y2 - y3) + x2*(y3 - y1) + x3*(y1 - y2) == 0;
    }

    /**
     * Method, that determines whether the specified two points lie on the same sides of the specified line.
     * In order for two points to lie on the same sides of the line, the following condition must be met: <br>
     * <code>Ax1 + By1 + C1 and Ax2 + By2 + C2 have the same signs</code>, where A, B, C are the coefficients of the
     * line, x1,y1 - coordinates of the first point, x2,y2 - coordinates of the second point.
     * @param first is the first point, f.e, A(x1;y1).
     * @param second is the second point, f.e B(x2;y2).
     * @param line is the line relative to which the relative positions of the points are determined.
     * @return true, if the points lie on the same sides, otherwise - false.
     */
    public static boolean lieOnSameSides(final Point2D first, final Point2D second, final Line2D line) {
        final float firstPointValue = line.getA()*first.getX() + line.getB()*first.getY() + line.getC();
        final float secondPointValue = line.getA()*second.getX() + line.getB()*second.getY() + line.getC();
        return (firstPointValue >= 0 && secondPointValue >= 0) || (firstPointValue < 0 && secondPointValue < 0);
    }

    /**
     * Method, that determines the distance from the point to the specified line. Uses the following formula
     * to calculate the distance: <br>
     * <code>|(Ax + By + C)/sqrt(A^2 + B^2)|</code>, where A, B, C are the coefficients of the line, x,y -
     * coordinates of the point.
     * @param point is the point from which to calculate the distance to the line.
     * @param line is the line to which to calculate the distance from the point.
     * @return the distance between the point and the line.
     */
    public static float distanceToLine(final Point2D point, final Line2D line) {
        final float denominator = (float) Math.sqrt(Math.pow(line.getA(), 2) + Math.pow(line.getB(), 2));
        final float numerator = line.getA()*point.getX() + line.getB()*point.getY() + line.getC();
        return Math.abs(numerator / denominator);
    }

    /**
     * Overloaded version of {@link #distanceToLine(Point2D, Line2D) distanceToLine method}. Unlike the other version
     * of the method, it counts the distance for a line in the equation with angular coefficient. Uses the following
     * formula to calculate the distance: <br>
     * <code>|(y - Kx - B)/sqrt(A^2 + B^2)|</code>, where A, B, K are the coefficients of the line, x,y -
     * coordinates of the point.
     * @param point is the point from which to calculate the distance to the line.
     * @param line is the line to which to calculate the distance from the point.
     * @return the distance between the point and the line.
     */
    public static float distanceToLine(final Point2D point, final LineY2D line) {
        final float denominator = (float) Math.sqrt(Math.pow(line.getK(), 2) + 1);
        final float numerator = point.getY() - line.getK()*point.getX() - line.getB();
        return Math.abs(numerator / denominator);
    }
}
