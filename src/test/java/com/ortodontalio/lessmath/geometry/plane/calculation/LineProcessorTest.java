package com.ortodontalio.lessmath.geometry.plane.calculation;

import com.ortodontalio.lessmath.exceptions.LineOperationException;
import com.ortodontalio.lessmath.geometry.plane.model.Line2D;
import com.ortodontalio.lessmath.geometry.plane.model.LineY2D;
import com.ortodontalio.lessmath.geometry.plane.model.Point2D;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LineProcessorTest {

    @SuppressWarnings("deprecation")
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testAreParallel() {
        final Line2D firstLine = new Line2D(2, -7, 12);
        final Line2D secondLine = new Line2D(1, -3.5f, 10);
        final Line2D thirdLine = new Line2D(3,2, -6);
        assertTrue(LineProcessor.areParallel(firstLine, secondLine));
        assertFalse(LineProcessor.areParallel(firstLine, thirdLine));
    }

    @Test
    public void testAreParallelYView() {
        final LineY2D firstLine = new LineY2D(3, -5);
        final LineY2D secondLine = new LineY2D(3, 4);
        final LineY2D thirdLine = new LineY2D(6, -5);
        assertTrue(LineProcessor.areParallel(firstLine, secondLine));
        assertFalse(LineProcessor.areParallel(firstLine, thirdLine));
    }

    @Test
    public void testArePerpendicular() {
        final Line2D firstLine = new Line2D(2, 5, -8);
        final Line2D secondLine = new Line2D(5, -2, -3);
        final Line2D thirdLine = new Line2D(3,2, -6);
        assertTrue(LineProcessor.arePerpendicular(firstLine, secondLine));
        assertFalse(LineProcessor.arePerpendicular(firstLine, thirdLine));
    }

    @Test
    public void testArePerpendicularYView() {
        final LineY2D firstLine = new LineY2D(3, 0);
        final LineY2D secondLine = new LineY2D(-1/3f, 0);
        final LineY2D thirdLine = new LineY2D(1/3f, 0);
        assertTrue(LineProcessor.arePerpendicular(firstLine, secondLine));
        assertFalse(LineProcessor.arePerpendicular(firstLine, thirdLine));
    }

    @Test
    public void testFindIntersectionExists() {
        final Line2D firstLine = new Line2D(2, -1, -3);
        final Line2D secondLine = new Line2D(-3, -1, 2);
        assertEquals(new Point2D('A',1, -1), LineProcessor.findIntersection('A', firstLine, secondLine));
    }

    @Test
    public void testFindIntersectionNone() {
        expectedException.expect(LineOperationException.class);
        expectedException.expectMessage(LineOperationException.NO_POINTS);
        final Line2D firstLine = new Line2D(2, -7, 12);
        final Line2D secondLine = new Line2D(1, -3.5f, 10);
        LineProcessor.findIntersection('A', firstLine, secondLine);
        assertTrue(LineProcessor.areParallel(firstLine, secondLine));
    }

    @Test
    public void testFindIntersectionInfinity() {
        expectedException.expect(LineOperationException.class);
        expectedException.expectMessage(LineOperationException.INFINITY_POINTS);
        final Line2D firstLine = new Line2D(3, 2, -6);
        final Line2D secondLine = new Line2D(6, 4, -12);
        LineProcessor.findIntersection('A', firstLine, secondLine);
        assertEquals(firstLine, secondLine);
    }

    @Test
    public void testFindAngleBetween() {
        final Line2D firstLine = new Line2D(1, -3.4f, 16);
        final Line2D secondLine = new Line2D(-3, 2, 12);
        assertEquals(39.920, LineProcessor.findAngleBetween(firstLine, secondLine), 0.001);
    }

    @Test
    public void testFindAngleBetweenYView() {
        final LineY2D firstLine = new LineY2D(2, -3);
        final LineY2D secondLine = new LineY2D(-3, 2);
        assertEquals(45.0, LineProcessor.findAngleBetween(firstLine, secondLine), 0.001);
    }

    @Test
    public void testFindLineByAngle() {
        final LineY2D line = new LineY2D(2, -3);
        final float angle = 45;
        final LineY2D expected = new LineY2D(-3, 0);
        assertEquals(expected, LineProcessor.findLineByAngle(angle, line));
    }

    @Test
    public void testFindParallelLinePassingThroughPointYView() {
        final LineY2D line = new LineY2D(5/7f, -4/7f);
        final Point2D point = new Point2D('A', -2, 5);
        final LineY2D expected = new LineY2D(5/7f, 45/7f);
        assertEquals(expected, LineProcessor.findParallelLinePassingThroughPoint(point, line));
    }

    @Test
    public void testFindParallelLinePassingThroughPoint() {
        final Line2D line = new Line2D(5, -7, -4);
        final Point2D point = new Point2D('A', -2, 5);
        final Line2D expected = new Line2D(5, -7, 45);
        assertEquals(expected, LineProcessor.findParallelLinePassingThroughPoint(point, line));
    }

    @Test
    public void testFindPerpendicularLinePassingThroughPointYView() {
        final LineY2D line = new LineY2D(4/9f, -1/3f);
        final Point2D point = new Point2D('A', 2, -1);
        final LineY2D expected = new LineY2D(-9/4f, 3.5f);
        assertEquals(expected, LineProcessor.findPerpendicularLinePassingThroughPoint(point, line));
    }

    @Test
    public void testFindPerpendicularLinePassingThroughPoint() {
        final Line2D line = new Line2D(0, 2, 1);
        final Point2D point = new Point2D('A', -3, -2);
        final Line2D expected = new Line2D(2, 0, 6);
        assertEquals(expected, LineProcessor.findPerpendicularLinePassingThroughPoint(point, line));
    }
}