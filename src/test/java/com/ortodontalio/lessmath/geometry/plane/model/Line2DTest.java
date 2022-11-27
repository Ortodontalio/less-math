package com.ortodontalio.lessmath.geometry.plane.model;

import com.ortodontalio.lessmath.exceptions.LineOperationException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Line2DTest {

    @SuppressWarnings("deprecation")
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testIncludePoint() {
        final Point2D firstPoint = new Point2D('A', 1, 5);
        final Point2D secondPoint = new Point2D('B', 3, 9);
        final Segment2D segment = new Segment2D(firstPoint, secondPoint);
        final Line2D line = new Line2D(segment);
        assertTrue(line.includesPoint(firstPoint));
    }

    @Test
    public void testIncorrectLineCreation() {
        expectedException.expect(LineOperationException.class);
        expectedException.expectMessage(LineOperationException.INCORRECT_CREATION);
        new Line2D(0, 0, 4);
    }

    @Test
    public void testParallelMethods() {
        final Line2D firstLine = new Line2D(2, -4.5f, 1);
        final Line2D secondLine = new Line2D(0,-11,1e-3f);
        final Line2D thirdLine = new Line2D(19.2f, 0, 2);
        final Line2D fourthLine = new Line2D(9,2,0);
        assertFalse(firstLine.isParallelToX());
        assertTrue(secondLine.isParallelToX());
        assertTrue(thirdLine.isParallelToY());
        assertTrue(fourthLine.passesOrigin());
    }

    @Test
    public void testGetYView() {
        final Line2D line = new Line2D(2,-4,5);
        final LineY2D expected = new LineY2D(0.5f,1.25f);
        assertEquals(expected, line.getYView());
    }

    @Test
    public void testShiftOrigin() {
        final Line2D line = new Line2D(2, -4, 6);
        final Line2D expected = new Line2D(2,-4,4);
        assertEquals(expected, line.shiftOrigin(1, 1));
        assertEquals(line, line.shiftOrigin(0, 0));
    }

    @Test
    public void testRotateAxes() {
        final Line2D line = new Line2D(2, -4, 6);
        final Line2D expected = new Line2D(-1.4142135f,-4.2426405f,6);
        assertEquals(expected, line.rotateAxes(45));
        assertEquals(line, line.rotateAxes(0));
    }

    @Test
    public void testToString() {
        final Point2D firstPoint = new Point2D('A', 1, 5);
        final Point2D secondPoint = new Point2D('B', 3, 9);
        final Segment2D segment = new Segment2D(firstPoint, secondPoint);
        final Line2D line = new Line2D(segment);
        assertEquals("-4,00x + 2,00y - 6,00 = 0", line.toString());
        assertEquals("-4.00x + 2.00y - 6.00 = 0", line.toString(Locale.CHINA));
    }

}