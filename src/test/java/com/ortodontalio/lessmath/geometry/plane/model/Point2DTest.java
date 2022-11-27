package com.ortodontalio.lessmath.geometry.plane.model;

import com.ortodontalio.lessmath.geometry.plane.configuration.Quarter;
import org.junit.Test;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class Point2DTest {

    @Test
    public void testIsCenter() {
        final Point2D truePoint = new Point2D();
        final Point2D falsePoint = new Point2D('A', 2f, -4f);
        final Point2D middlePoint = new Point2D('B', 0, 1f);
        assertTrue(truePoint.isCenter());
        assertFalse(falsePoint.isCenter());
        assertFalse(middlePoint.isCenter());
    }

    @Test
    public void testLiesOnX() {
        final Point2D centerPoint = new Point2D();
        final Point2D falsePoint = new Point2D('A', 2f, -4f);
        final Point2D middlePoint = new Point2D('B', 7f, 0);
        assertTrue(centerPoint.liesOnX());
        assertFalse(falsePoint.liesOnX());
        assertTrue(middlePoint.liesOnX());
    }

    @Test
    public void testLiesOnY() {
        final Point2D centerPoint = new Point2D();
        final Point2D falsePoint = new Point2D('A', 2f, -4f);
        final Point2D middlePoint = new Point2D('B', 0, 7f);
        assertTrue(centerPoint.liesOnY());
        assertFalse(falsePoint.liesOnY());
        assertTrue(middlePoint.liesOnY());
    }

    @Test
    public void testQuarter() {
        final Point2D centerPoint = new Point2D();
        final Point2D firstQuarterPoint = new Point2D('A', 2f, 4f);
        final Point2D secondQuarterPoint = new Point2D('B', -0.213f, 7f);
        final Point2D thirdQuarterPoint = new Point2D('C', -2f, -4f);
        final Point2D fourthQuarterPoint = new Point2D('D', 0.15f, -7f);
        assertEquals(Quarter.UNDEFINED, centerPoint.quarter());
        assertEquals(Quarter.FIRST, firstQuarterPoint.quarter());
        assertEquals(Quarter.SECOND, secondQuarterPoint.quarter());
        assertEquals(Quarter.THIRD, thirdQuarterPoint.quarter());
        assertEquals(Quarter.FOURTH, fourthQuarterPoint.quarter());
    }

    @Test
    public void testEquals() {
        final Point2D firstPoint = new Point2D('A', 2.324f, 5.7453f);
        final Point2D secondPoint = new Point2D('B', 2.324f, 5.7453f);
        final Point2D thirdPoint = new Point2D('A', 3f, 3f);
        assertEquals(firstPoint, secondPoint);
        assertNotEquals(firstPoint, thirdPoint);
    }

    @Test
    public void testToString() {
        final Point2D point = new Point2D('A', -9.6454f, 11.4214f);
        assertEquals("A(-9,65; 11,42)", point.toString());
        assertEquals("A(-9.65; 11.42)", point.toString(Locale.CHINA));
    }

    @Test
    public void testCompareTo() {
        final Point2D firstPoint = new Point2D('A', 4, 9);
        final Point2D secondPoint = new Point2D('B', 1, 3);
        final Point2D thirdPoint = new Point2D('C', 9, 4);
        assertEquals(1, firstPoint.compareTo(secondPoint));
        assertEquals(0, firstPoint.compareTo(thirdPoint));
        assertEquals(-1, secondPoint.compareTo(thirdPoint));
    }
}