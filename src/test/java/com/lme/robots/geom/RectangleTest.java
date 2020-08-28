package com.lme.robots.geom;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class RectangleTest {

    @Test
    public void shouldDefineWellFormedRectangle() {
        final Rectangle rectangle = new Rectangle(Coordinates.of(0,0), Coordinates.of(5,3));
        assertThat(rectangle, notNullValue());
        assertThat(rectangle.getUpperRight().getX(), equalTo(5));
        assertThat(rectangle.getUpperRight().getY(), equalTo(3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAllowRectanglesWithInvalidLowerLeftBoundaries() {
        new Rectangle(Coordinates.of(-2,0), Coordinates.of(5,3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAllowRectanglesWithInvalidBoundariesCombination() {
        new Rectangle(Coordinates.of(4,7), Coordinates.of(10,3));
    }

    @Test
    public void coordinatesWithinRectangleBoundaries() {
        final Rectangle surfaceRectangle = new Rectangle(Coordinates.of(5,3));
        final Coordinates coordinates = Coordinates.of(2,1);
        assertThat(coordinates.isWithinBoundaries(surfaceRectangle), equalTo(true));
        assertThat(Coordinates.of(4,6).isWithinBoundaries(surfaceRectangle), equalTo(false));
    }

}
