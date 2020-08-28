package com.lme.robots;

import java.util.Optional;

import com.lme.robots.geom.Coordinates;
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

    public void executeInstruction(final RobotInstruction instruction) {
        instruction.execute(this);
    }

    void setRobotPosition(final RobotPosition newPosition) {
        this.robotPosition = newPosition;
    }

    void setLost(final boolean isLost) {
        this.lost = isLost;
    }

    //could be moved to a separate logic to handle printing logic
    public String getRobotOutput() {
        final Coordinates coordinates = getRobotPosition().getCoordinates();
        if(isLost()){
            return String.format("%d %d %s LOST", coordinates.getX(), coordinates.getY(), getRobotPosition().getOrientation());
        } else {
            return String.format("%d %d %s", coordinates.getX(), coordinates.getY(), getRobotPosition().getOrientation());
        }
    }
}
