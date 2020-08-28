package com.lme.robots.world;

import static com.lme.robots.geom.Coordinates.of;
import static com.lme.robots.positioning.Orientation.N;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import com.lme.robots.Robot;
import com.lme.robots.positioning.RobotPosition;
import org.junit.Test;

public class WorldSurfaceTest {

    @Test
    public void shouldCreateWorldAndDeployRobot() {
        final WorldSurface worldSurface = new WorldSurface(of(3,7));
        final Robot robot = worldSurface.deployRobot(new RobotPosition(of(2,3), N));
        assertThat(robot, notNullValue());
        assertThat(robot.getRobotPosition().getCoordinates().getX(), equalTo(2));
        assertThat(robot.getRobotPosition().getCoordinates().getY(), equalTo(3));
        assertThat(robot.getRobotPosition().getOrientation(), equalTo(N));
        assertThat(worldSurface.getDeployedRobots().size(), equalTo(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldCreateWorldAndNotAllowDeploymentOfMissplacedRobot() {
        final WorldSurface worldSurface = new WorldSurface(of(3,7));
        worldSurface.deployRobot(new RobotPosition(of(9,3), N));
    }

}
