package com.lme.robots;

import com.lme.robots.geom.Coordinates;
import com.lme.robots.positioning.RobotPosition;
import com.lme.robots.world.WorldSurface;

public class MoveForward implements RobotInstruction {

    private WorldSurface surface;

    public MoveForward(final WorldSurface surface) {
        this.surface = surface;
    }

    @Override
    public void execute(final Robot robot) {
        if (!robot.isLost()) {
            final RobotPosition targetPosition = robot.getRobotPosition().moveForward();
            final Coordinates targetCoordinates = targetPosition.getCoordinates();
            robot.setLost(! targetCoordinates.isWithinBoundaries(surface.getBoundaries()));
            if(!robot.isLost()) {
                robot.setRobotPosition(targetPosition);
            }
        }
    }
}
