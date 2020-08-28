package com.lme.robots.world;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.lme.robots.Robot;
import com.lme.robots.geom.Coordinates;
import com.lme.robots.geom.Rectangle;
import com.lme.robots.positioning.RobotPosition;

public class WorldSurface {

    private final Rectangle boundaries;

    private List<Robot> deployedRobots = new LinkedList<>();

    public WorldSurface(final Coordinates upperRightCoordinates) {
        this.boundaries = new Rectangle(upperRightCoordinates);
    }

    public Rectangle getBoundaries() {
        return boundaries;
    }

    public Robot deployRobot(final RobotPosition initialPosition) {
        if(!initialPosition.getCoordinates().isWithinBoundaries(boundaries)) {
            throw new IllegalArgumentException("invalid initial coordinates for robot");
        }
        final Robot robot = new Robot(initialPosition);
        deployedRobots.add(robot);
        return robot;
    }

    public List<Robot> getDeployedRobots() {
        return Collections.unmodifiableList(deployedRobots);
    }
}
