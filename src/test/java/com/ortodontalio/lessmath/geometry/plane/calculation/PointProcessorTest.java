package com.ortodontalio.lessmath.geometry.plane.calculation;

import com.ortodontalio.lessmath.geometry.plane.model.Line2D;
import com.ortodontalio.lessmath.geometry.plane.model.Point2D;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class PointProcessorTest {

    @Test
    public void testLiesOnSameLineTrue() {
        final Point2D firstPoint = new Point2D('A', -2, 5);
        final Point2D secondPoint = new Point2D('B', 4, 3);
        final Point2D thirdPoint = new Point2D('C', 16, -1);
        assertTrue(PointProcessor.lieOnSameLine(firstPoint, secondPoint, thirdPoint));
    }

    @Test
    public void testLiesOnSameLineFalse() {
        final Point2D firstPoint = new Point2D('A', -2, 6);
        final Point2D secondPoint = new Point2D('B', 2, 5);
        final Point2D thirdPoint = new Point2D('C', 5, 3);
        assertFalse(PointProcessor.lieOnSameLine(firstPoint, secondPoint, thirdPoint));
    }

    @Test
    public void testLieDifferentSidesTrue() {
        final Point2D firstPoint = new Point2D('A', 2, -6);
        final Point2D secondPoint = new Point2D('B', -4, -2);
        final Line2D line = new Line2D(3, 5, -1);
        assertTrue(PointProcessor.lieOnSameSides(firstPoint, secondPoint, line));
    }

    @Test
    public void testLieDifferentSidesFalse() {
        final Point2D firstPoint = new Point2D();
        final Point2D secondPoint = new Point2D('A', 5, 5);
        final Line2D line = new Line2D(1, 1, -8);
        assertFalse(PointProcessor.lieOnSameSides(firstPoint, secondPoint, line));
    }

    @Test
    public void testDistanceToLine() {
        final Point2D point = new Point2D('A', -1, 1);
        final Line2D line = new Line2D(3, -4, 5);
        assertEquals(2/5f, PointProcessor.distanceToLine(point, line), 0.001);
    }

    @Test
    public void testDistanceToLineZero() {
        final Point2D point = new Point2D('A', 1, 2);
        final Line2D line = new Line2D(3, -4, 5);
        assertEquals(0, PointProcessor.distanceToLine(point, line), 0.001);
    }
}