package com.lme.robots.geom;

/**
 * Value class representing a set of coordinates.
 */
public class Coordinates {

    private final int x;

    private final int y;

    public Coordinates(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public static Coordinates of(int x, int y) {
        return new Coordinates(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isWithinBoundaries(final Rectangle surfaceRectangle) {
        final Coordinates lowerLeft = surfaceRectangle.getLowerLeft();
        final Coordinates upperRight = surfaceRectangle.getUpperRight();
        return x >= lowerLeft.getX() && x <= upperRight.getX()
                && y >= lowerLeft.getY() && y <= upperRight.getY();
    }
}
