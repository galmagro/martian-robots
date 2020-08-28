package com.lme.robots;

import static com.lme.robots.positioning.Orientation.E;
import static com.lme.robots.positioning.Orientation.N;
import static com.lme.robots.positioning.Orientation.S;
import static com.lme.robots.positioning.Orientation.W;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import com.lme.robots.geom.Coordinates;
import com.lme.robots.positioning.RobotPosition;
import com.lme.robots.world.WorldSurface;
import org.junit.Test;

public class RobotInstructionTest {

    private final WorldSurface surface = new WorldSurface(Coordinates.of(5,3));

    @Test
    public void testLeftInstruction() {
        final Robot robot = surface.deployRobot(new RobotPosition(Coordinates.of(1,1), N));
        robot.executeInstruction(new TurnLeft());
        final RobotPosition finalPosition = robot.getRobotPosition();
        assertThat(finalPosition.getOrientation(), equalTo(W));
    }

    @Test
    public void testRightInstruction() {
        final Robot robot = surface.deployRobot(new RobotPosition(Coordinates.of(1,1), N));
        robot.executeInstruction(new TurnRight());
        final RobotPosition finalPosition = robot.getRobotPosition();
        assertThat(finalPosition.getOrientation(), equalTo(E));
    }

    @Test
    public void testRobotInstructions() {
        final Robot robot = surface.deployRobot(new RobotPosition(Coordinates.of(1,1), E));
        //RF RF RF RF
        robot.executeInstruction(new TurnRight());
        robot.executeInstruction(new MoveForward(surface));

        robot.executeInstruction(new TurnRight());
        robot.executeInstruction(new MoveForward(surface));

        robot.executeInstruction(new TurnRight());
        robot.executeInstruction(new MoveForward(surface));

        robot.executeInstruction(new TurnRight());
        robot.executeInstruction(new MoveForward(surface));

        final RobotPosition finalPosition = robot.getRobotPosition();
        assertThat(finalPosition.getOrientation(), equalTo(E));
        assertThat(finalPosition.getCoordinates().getX(), equalTo(1));
        assertThat(finalPosition.getCoordinates().getY(), equalTo(1));
    }

    @Test
    public void testRobotInstructions2() {
        final Robot robot = surface.deployRobot(new RobotPosition(Coordinates.of(0,3), W));
        //LL FF FL FL FL
        robot.executeInstruction(new TurnLeft());
        robot.executeInstruction(new TurnLeft());

        robot.executeInstruction(new MoveForward(surface));
        robot.executeInstruction(new MoveForward(surface));

        robot.executeInstruction(new MoveForward(surface));
        robot.executeInstruction(new TurnLeft());

        robot.executeInstruction(new MoveForward(surface));
        robot.executeInstruction(new TurnLeft());

        robot.executeInstruction(new MoveForward(surface));
        robot.executeInstruction(new TurnLeft());

        final RobotPosition finalPosition = robot.getRobotPosition();
        assertThat(finalPosition.getOrientation(), equalTo(N));
        assertThat(finalPosition.getCoordinates().getX(), equalTo(3));
        assertThat(finalPosition.getCoordinates().getY(), equalTo(3));
    }

    @Test
    public void testLostRobot() {
        final Robot robot = surface.deployRobot(new RobotPosition(Coordinates.of(3,2), N));

        //FRRFLLFFRRFLL
        robot.executeInstruction(new MoveForward(surface));
        robot.executeInstruction(new TurnRight());
        robot.executeInstruction(new TurnRight());
        robot.executeInstruction(new MoveForward(surface));
        robot.executeInstruction(new TurnLeft());
        robot.executeInstruction(new TurnLeft());
        robot.executeInstruction(new MoveForward(surface));
        robot.executeInstruction(new MoveForward(surface));
        robot.executeInstruction(new TurnRight());
        robot.executeInstruction(new TurnRight());
        robot.executeInstruction(new MoveForward(surface));
        robot.executeInstruction(new TurnLeft());
        robot.executeInstruction(new TurnLeft());

        final RobotPosition finalPosition = robot.getRobotPosition();
        assertThat(finalPosition.getOrientation(), equalTo(N));
        assertThat(finalPosition.getCoordinates().getX(), equalTo(3));
        assertThat(finalPosition.getCoordinates().getY(), equalTo(3));
        assertTrue(robot.isLost());
    }

}
