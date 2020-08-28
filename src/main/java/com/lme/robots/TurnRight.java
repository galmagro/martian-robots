package com.lme.robots;

public class TurnRight implements RobotInstruction {

    @Override
    public void execute(final Robot robot) {
        if (!robot.isLost()) {
            robot.setRobotPosition(robot.getRobotPosition().turnRight());
        }
    }
}
