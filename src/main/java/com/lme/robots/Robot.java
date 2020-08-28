package com.lme.robots;

import java.util.Optional;

import com.lme.robots.positioning.RobotPosition;

public class Robot {

    private RobotPosition robotPosition;

    private boolean lost = false;

    public Robot(final RobotPosition robotPosition) {
        this.robotPosition = Optional.ofNullable(robotPosition).orElseThrow(() -> new IllegalArgumentException("invalid starting position"));
    }

    public RobotPosition getRobotPosition() {
        return robotPosition;
    }

    public boolean isLost() {
        return lost;
    }

    private void turnLeft() {
        this.robotPosition = this.robotPosition.turnLeft();
    }

    private void turnRight() {
        this.robotPosition = this.robotPosition.turnRight();
    }
}
