package com.lme.robots.geom;

import java.util.Optional;

/**
 * Represents a boundary rectangle.
 */
public class Rectangle {

    private final Coordinates lowerLeft;

    private final Coordinates upperRight;

    public Rectangle(final Coordinates lowerLeft, final Coordinates upperRight) {
        this.lowerLeft = Optional.ofNullable(lowerLeft).orElse(new Coordinates(0,0));
        this.upperRight = Optional.ofNullable(upperRight)
                .orElseThrow(() -> new IllegalArgumentException("upperRight coordinates must be provided"));
        if(this.lowerLeft.getX() < 0 || this.lowerLeft.getY() < 0) {
            throw new IllegalArgumentException("negative lower left boundaries not allowed");
        }

        if(this.lowerLeft.getX() > this.upperRight.getX() || this.lowerLeft.getY() > this.upperRight.getY()) {
            throw new IllegalArgumentException("invalid upper right coordinates");
        }
    }

    public Rectangle(final Coordinates upperRight) {
        this(null, upperRight);
    }

    public Coordinates getLowerLeft() {
        return lowerLeft;
    }

    public Coordinates getUpperRight() {
        return upperRight;
    }
}
