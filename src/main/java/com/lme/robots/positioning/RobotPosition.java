package com.lme.robots.positioning;

import java.util.Optional;

import com.lme.robots.geom.Coordinates;

/**
 * Models robot's position.
 */
public class RobotPosition {

    private final Coordinates coordinates;

    private final Orientation orientation;

    public RobotPosition(final Coordinates coordinates, final Orientation orientation) {
        this.coordinates = Optional.ofNullable(coordinates)
                .orElseThrow(() -> new IllegalArgumentException("mandatory robot coordinates"));
        this.orientation = Optional.ofNullable(orientation)
                .orElseThrow(() -> new IllegalArgumentException("mandatory robot orientation"));
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public RobotPosition turnLeft() {
        return new RobotPosition(getCoordinates(), orientation.getLeft());
    }

    public RobotPosition turnRight() {
        return new RobotPosition(getCoordinates(), orientation.getRight());
    }

    public RobotPosition moveForward() {
        int newX, newY;
        switch (orientation) {
            case N:
                newY = coordinates.getY() + 1;
                newX = coordinates.getX();
                break;
            case S:
                newY = coordinates.getY() - 1;
                newX = coordinates.getX();
                break;
            case E:
                newX = coordinates.getX() + 1;
                newY = coordinates.getY();
                break;
            case W:
                newX = coordinates.getX() - 1;
                newY = coordinates.getY();
                break;
            default:
                newX = coordinates.getX();
                newY = coordinates.getY();
        }

        return new RobotPosition(Coordinates.of(newX, newY), orientation);
    }
}
