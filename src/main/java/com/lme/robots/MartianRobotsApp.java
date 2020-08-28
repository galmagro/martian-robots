package com.lme.robots;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.lme.robots.parser.MartianRobotsParser;
import com.lme.robots.world.WorldSurface;

public class MartianRobotsApp {

    public static void main(String[] args) {
        final MartianRobotsParser martianRobotsParser = new MartianRobotsParser(System.in);
        final Optional<WorldSurface> worldSurface = martianRobotsParser.parseWorld();
        worldSurface.ifPresent(surface -> {
            while (martianRobotsParser.hasNext()){
                final Optional<Robot> robot = martianRobotsParser
                        .parseRobotPosition()
                        .map(surface::deployRobot);
                final List<RobotInstruction> instructions = robot.map(rob -> martianRobotsParser.parseRobotInstructions()).orElse(Collections.emptyList());
                instructions.forEach(robotInstruction -> robot.get().executeInstruction(robotInstruction));
            }

            surface.getDeployedRobots().forEach(MartianRobotsApp::printRobotOutput);

        });

        if(!worldSurface.isPresent()){
            System.out.println("no world surface defined");
        }
    }

    private static void printRobotOutput(final Robot robot) {
        System.out.println(robot.getRobotOutput());
    }

}
