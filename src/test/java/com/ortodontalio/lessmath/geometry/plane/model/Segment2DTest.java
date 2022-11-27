package com.ortodontalio.lessmath.geometry.plane.model;

import com.ortodontalio.lessmath.geometry.plane.configuration.Constants;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Segment2DTest {

    @Test
    public void testFindSplitterPoint() {
        final Point2D firstPoint = new Point2D('A', 6f, -4f);
        final Point2D secondPoint = new Point2D();
        final Segment2D segment = new Segment2D(firstPoint, secondPoint);
        final Point2D splitterPoint = segment.findSplitterPoint('K', 2, 3);
        final Point2D truePoint = new Point2D('K', 3.6f, -2.4f);
        assertEquals(truePoint, splitterPoint);
    }

    @Test
    public void testLength() {
        final Point2D firstPoint = new Point2D('A', -2.3f, 4);
        final Point2D secondPoint = new Point2D('B', 8.5f, 0.7f);
        final Segment2D segment = new Segment2D(firstPoint, secondPoint);
        assertEquals(11.292918, segment.length(), Constants.EPSILON.getValue());
    }

}