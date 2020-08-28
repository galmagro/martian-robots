package com.lme.robots.parser;

import static com.lme.robots.positioning.Orientation.E;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;

import com.lme.robots.MoveForward;
import com.lme.robots.RobotInstruction;
import com.lme.robots.TurnRight;
import com.lme.robots.positioning.RobotPosition;
import com.lme.robots.world.WorldSurface;
import org.junit.Test;

public class MartianRobotsParserTest {

    private MartianRobotsParser parser;

    @Test
    public void testReadWorldSurface() {
        final String contents = "5 3";
        parser = new MartianRobotsParser(new ByteArrayInputStream(contents.getBytes()));
        final Optional<WorldSurface> worldSurfaceOpt = parser.parseWorld();
        assertTrue(worldSurfaceOpt.isPresent());
        final WorldSurface surface = worldSurfaceOpt.get();
        assertThat(surface.getBoundaries().getUpperRight().getX(), equalTo(5));
        assertThat(surface.getBoundaries().getUpperRight().getY(), equalTo(3));
    }

    @Test
    public void testReadWorldAndRobotPosition() {
        final String contents = "5 3\n" +
                "1 1 E";
        parser = new MartianRobotsParser(new ByteArrayInputStream(contents.getBytes()));
        final Optional<WorldSurface> worldSurfaceOpt = parser.parseWorld();
        assertTrue(worldSurfaceOpt.isPresent());

        final Optional<RobotPosition> robotPositionOpt = parser.parseRobotPosition();
        assertTrue(robotPositionOpt.isPresent());
        final RobotPosition robotPosition = robotPositionOpt.get();
        assertThat(robotPosition.getCoordinates().getX(), equalTo(1));
        assertThat(robotPosition.getCoordinates().getY(), equalTo(1));
        assertThat(robotPosition.getOrientation(), equalTo(E));
    }

    @Test
    public void testReadWorldRobotAndInstructions() {
        final String contents = "5 3\n" +
                "1 1 E\n" +
                "RFRFRFRF";
        parser = new MartianRobotsParser(new ByteArrayInputStream(contents.getBytes()));
        final Optional<WorldSurface> worldSurfaceOpt = parser.parseWorld();
        assertTrue(worldSurfaceOpt.isPresent());

        final Optional<RobotPosition> robotPositionOpt = parser.parseRobotPosition();
        assertTrue(robotPositionOpt.isPresent());

        final List<RobotInstruction> instructions = parser.parseRobotInstructions();
        assertFalse(instructions.isEmpty());
        assertThat(instructions.size(), equalTo(8));
        assertThat(instructions.get(0), instanceOf(TurnRight.class));
        assertThat(instructions.get(1), instanceOf(MoveForward.class));
        assertThat(instructions.get(2), instanceOf(TurnRight.class));
        assertThat(instructions.get(3), instanceOf(MoveForward.class));
        assertThat(instructions.get(4), instanceOf(TurnRight.class));
        assertThat(instructions.get(5), instanceOf(MoveForward.class));
        assertThat(instructions.get(6), instanceOf(TurnRight.class));
        assertThat(instructions.get(7), instanceOf(MoveForward.class));

    }

}
