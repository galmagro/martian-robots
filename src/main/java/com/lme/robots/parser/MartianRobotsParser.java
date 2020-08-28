package com.lme.robots.parser;

import static com.lme.robots.geom.Coordinates.of;
import static java.lang.Integer.parseInt;
import static java.util.Collections.emptyList;
import static java.util.Optional.empty;
import static java.util.regex.Pattern.compile;
import static java.util.stream.Collectors.toList;

import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.lme.robots.MoveForward;
import com.lme.robots.RobotInstruction;
import com.lme.robots.TurnLeft;
import com.lme.robots.TurnRight;
import com.lme.robots.geom.Coordinates;
import com.lme.robots.positioning.Orientation;
import com.lme.robots.positioning.RobotPosition;
import com.lme.robots.world.WorldSurface;

public class MartianRobotsParser {

    private final Scanner scanner;

    private final static Pattern WORLD_LINE_PATTERN = compile("(\\d) (\\d)");

    private final static Pattern ROBOT_INITIAL_POSITION_PATTERN = compile("(\\d) (\\d) ([NSEW])");

    private final static Pattern ROBOT_INSTRUCTIONS_PATTERN = compile("[FRL]+");

    private WorldSurface currentWorld;

    public MartianRobotsParser(final InputStream source) {
        this.scanner = new Scanner(source);
    }

    public Optional<WorldSurface> parseWorld() {
        String upperCoordinatesLine = scanner.findInLine(WORLD_LINE_PATTERN);
        while (upperCoordinatesLine == null && scanner.hasNext()){
            scanner.nextLine();
            upperCoordinatesLine = scanner.findInLine(WORLD_LINE_PATTERN);
        }
        final Matcher worldLineMatcher = WORLD_LINE_PATTERN.matcher(upperCoordinatesLine);
        if(worldLineMatcher.matches()) {
            final int x = parseInt(worldLineMatcher.group(1));
            final int y = parseInt(worldLineMatcher.group(2));
            this.currentWorld = new WorldSurface(of(x, y));
            return Optional.of(this.currentWorld);
        }
        return empty();
    }

    public Optional<RobotPosition> parseRobotPosition() {
        String positionLine = scanner.findInLine(ROBOT_INITIAL_POSITION_PATTERN);
        while (positionLine == null && scanner.hasNext()) {
            scanner.nextLine();
            positionLine = scanner.findInLine(ROBOT_INITIAL_POSITION_PATTERN);
        }
        final Matcher robotPositionMatcher = ROBOT_INITIAL_POSITION_PATTERN.matcher(positionLine);
        if(robotPositionMatcher.matches()){
            final int x = parseInt(robotPositionMatcher.group(1));
            final int y = parseInt(robotPositionMatcher.group(2));
            final Orientation orientation = Orientation.valueOf(robotPositionMatcher.group(3));
            return Optional.of(new RobotPosition(Coordinates.of(x, y), orientation));
        }
        return empty();
    }

    public List<RobotInstruction> parseRobotInstructions() {
        String instructionsLine = scanner.findInLine(ROBOT_INSTRUCTIONS_PATTERN);
        while (instructionsLine == null && scanner.hasNext()) {
            scanner.nextLine();
            instructionsLine = scanner.findInLine(ROBOT_INSTRUCTIONS_PATTERN);
        }
        final Matcher robotInstructionsMatcher = ROBOT_INSTRUCTIONS_PATTERN.matcher(instructionsLine);
        if(robotInstructionsMatcher.matches()){
            return instructionsLine.chars().mapToObj(i -> (char) i)
                    .map(this::fromInstructionChar)
                    .filter(Objects::nonNull)
                    .collect(toList());

        }
        return emptyList();
    }

    private RobotInstruction fromInstructionChar(final char instructionChar) {
        switch (instructionChar){
            case 'R':
                return new TurnRight();
            case 'L':
                return new TurnLeft();
            case 'F':
                return new MoveForward(currentWorld);
            default:
                return null;

        }
    }

    public boolean hasNext() {
        return scanner.hasNextLine();
    }

    public void skipEmptyLines() {
        while (scanner.hasNext("\\s")){
            scanner.next("\\s");
        }
    }
}
