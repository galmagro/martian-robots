package com.lme.robots.positioning;

import java.util.Optional;

import com.lme.robots.geom.Coordinates;

/**
 * Models robot's position.
 */
public class RobotPosition {

    private final Coordinates position;

    private final Orientation orientation;

    public RobotPosition(final Coordinates position, final Orientation orientation) {
        this.position = Optional.ofNullable(position)
                .orElseThrow(() -> new IllegalArgumentException("mandatory robot position"));
        this.orientation = Optional.ofNullable(orientation)
                .orElseThrow(() -> new IllegalArgumentException("mandatory robot orientation"));
    }

    public Coordinates getPosition() {
        return position;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public RobotPosition turnLeft() {
        return new RobotPosition(getPosition(), orientation.getLeft());
    }

    public RobotPosition turnRight() {
        return new RobotPosition(getPosition(), orientation.getRight());
    }

    public RobotPosition moveForward() {
        int newX, newY;
        switch (orientation) {
            case N:
                newY = position.getY() + 1;
                newX = position.getX();
                break;
            case S:
                newY = position.getY() - 1;
                newX = position.getX();
                break;
            case E:
                newX = position.getX() + 1;
                newY = position.getY();
                break;
            case W:
                newX = position.getX() - 1;
                newY = position.getY();
                break;
            default:
                newX = position.getX();
                newY = position.getY();
        }

        return new RobotPosition(Coordinates.of(newX, newY), orientation);
    }
}
