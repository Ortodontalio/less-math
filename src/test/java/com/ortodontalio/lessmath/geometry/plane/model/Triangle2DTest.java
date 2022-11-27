package com.ortodontalio.lessmath.geometry.plane.model;

import com.ortodontalio.lessmath.geometry.plane.configuration.Constants;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Triangle2DTest {

    @Test
    public void testArea() {
        final Point2D a = new Point2D('A', 1, 3);
        final Point2D b = new Point2D('B', 2, -5);
        final Point2D c = new Point2D('C', -8, 4);
        final Triangle2D triangle = new Triangle2D(a, b, c);
        assertEquals(35.5f, triangle.area(), Constants.EPSILON.getValue());
    }

    @Test
    public void testGetFirstSideMedian() {
        final Point2D a = new Point2D('A', 0, 0);
        final Point2D b = new Point2D('B', 4, 4);
        final Point2D c = new Point2D('C', 5, 0);
        final Triangle2D triangle = new Triangle2D(a, b, c);
        final Point2D expectedK = new Point2D('K', 2, 2);
        final Segment2D expectedMedian = triangle.getFirstSideMedian('K');
        assertEquals(c, expectedMedian.getA());
        assertEquals(expectedK, expectedMedian.getB());
    }

    @Test
    public void testGetSecondSideMedian() {
        final Point2D a = new Point2D('A', 0, 0);
        final Point2D b = new Point2D('B', 0, 5);
        final Point2D c = new Point2D('C', 4, 3);
        final Triangle2D triangle = new Triangle2D(a, b, c);
        final Point2D expectedK = new Point2D('K', 2, 4);
        final Segment2D expectedMedian = triangle.getSecondSideMedian('K');
        assertEquals(a, expectedMedian.getA());
        assertEquals(expectedK, expectedMedian.getB());
    }

    @Test
    public void testGetThirdSideMedian() {
        final Point2D a = new Point2D('A', 0, 0);
        final Point2D b = new Point2D('B', 1, 2);
        final Point2D c = new Point2D('C', 0, 6);
        final Triangle2D triangle = new Triangle2D(a, b, c);
        final Point2D expectedK = new Point2D('K', 0, 3);
        final Segment2D expectedMedian = triangle.getThirdSideMedian('K');
        assertEquals(b, expectedMedian.getA());
        assertEquals(expectedK, expectedMedian.getB());
    }

    @Test
    public void testGetAngles() {
        final Point2D a = new Point2D('A', 0, 0);
        final Point2D b = new Point2D('B', 0, 10);
        final Point2D c = new Point2D('C', 10, 0);
        final Triangle2D triangle = new Triangle2D(a, b, c);
        assertEquals(90, triangle.getFirstAngle(), Constants.EPSILON.getValue());
        assertEquals(45, triangle.getSecondAngle(), Constants.EPSILON.getValue());
        assertEquals(45, triangle.getThirdAngle(), Constants.EPSILON.getValue());
    }

    @Test
    public void testIsRectangular() {
        final Point2D a = new Point2D('A', 0, 0);
        final Point2D b = new Point2D('B', 0, 4);
        final Point2D c = new Point2D('C', 6, 0);
        final Point2D d = new Point2D('D', 1, 3);
        final Triangle2D firstTriangle = new Triangle2D(a, b, c);
        final Triangle2D secondTriangle = new Triangle2D(a, b, d);
        assertTrue(firstTriangle.isRectangular());
        assertFalse(secondTriangle.isRectangular());
    }

    @Test
    public void testIsIsosceles() {
        final Point2D a = new Point2D('A', 0, 0);
        final Point2D b = new Point2D('B', 3, 3);
        final Point2D c = new Point2D('C', 6, 0);
        final Point2D d = new Point2D('D', 3, 0);
        final Triangle2D firstTriangle = new Triangle2D(a, b, c);
        final Triangle2D secondTriangle = new Triangle2D(a, b, d);
        assertTrue(firstTriangle.isIsosceles());
        assertTrue(secondTriangle.isIsosceles());
    }

    @Test
    public void testIsEquilateral() {
        final Point2D a = new Point2D('A', 0, 0);
        final Point2D b = new Point2D('B', 3, 3);
        final Point2D c = new Point2D('C', 6, 0);
        final Point2D d = new Point2D('D', 3, 5.1961524f);
        final Triangle2D firstTriangle = new Triangle2D(a, b, c);
        final Triangle2D secondTriangle = new Triangle2D(a, d, c);
        assertTrue(secondTriangle.isEquilateral());
        assertFalse(firstTriangle.isEquilateral());
    }

    @Test
    public void testToString() {
        final Point2D a = new Point2D('A', 0, 0);
        final Point2D b = new Point2D('B', 0, 5);
        final Point2D c = new Point2D('C', 5, 0);
        final Triangle2D triangle = new Triangle2D(a, b, c);
        assertEquals("ABC(12,50)", triangle.toString());
    }
}