package com.lme.robots.positioning;

import static com.lme.robots.positioning.Orientation.E;
import static com.lme.robots.positioning.Orientation.N;
import static com.lme.robots.positioning.Orientation.W;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import com.lme.robots.geom.Coordinates;
import org.junit.Test;

public class RobotPositionTest {

    private RobotPosition initialPosition = new RobotPosition(Coordinates.of(1, 1), N);

    @Test
    public void testOnePositionChange() {
        final RobotPosition finalPosition = initialPosition
                .turnLeft()
                .moveForward();
        assertThat(finalPosition.getCoordinates().getX() , equalTo(0));
        assertThat(finalPosition.getCoordinates().getY() , equalTo(1));
        assertThat(finalPosition.getOrientation() , equalTo(W));
    }

    @Test
    public void testTwoPositionChanges() {
        final RobotPosition finalPosition = initialPosition
                .turnRight()
                .moveForward()
                .turnLeft()
                .moveForward();
        assertThat(finalPosition.getCoordinates().getX() , equalTo(2));
        assertThat(finalPosition.getCoordinates().getY() , equalTo(2));
        assertThat(finalPosition.getOrientation() , equalTo(N));
    }

    @Test
    public void testThreePositionChanges() {
        final RobotPosition finalPosition = initialPosition
                .turnRight()
                .moveForward()
                .turnRight()
                .moveForward()
                .turnLeft()
                .moveForward();
        assertThat(finalPosition.getCoordinates().getX() , equalTo(3));
        assertThat(finalPosition.getCoordinates().getY() , equalTo(0));
        assertThat(finalPosition.getOrientation() , equalTo(E));
    }

}
