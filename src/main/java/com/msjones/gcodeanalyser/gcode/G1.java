package com.msjones.gcodeanalyser.gcode;

import com.msjones.gcodeanalyser.GCodeProcessor;

import javax.vecmath.Point3d;
import java.security.InvalidParameterException;

/**
 * Handles lines starting with G1.
 * <p>
 * e.g.: G1 Xnnn Ynnn Znnn Ennn Fnnn Snnn
 */
public class G1 extends AGCode {
    Point3d destinationPoint;
    Double e;
    double f;
    double s;

    public G1(GCodeProcessor processor) {
        super(processor);
    }

    public void processCommand(String commands) {
        destinationPoint = new Point3d(processor.actualCoordinates);

        String[] commandArray = commands.split("\\s+");
        for (String command : commandArray) {
            switch (command.toUpperCase().substring(0, 1)) {
                case "X":
                    destinationPoint.x = Double.parseDouble(command.substring(1));
                    break;
                case "Y":
                    destinationPoint.y = Double.parseDouble(command.substring(1));
                    break;
                case "Z":
                    destinationPoint.z = Double.parseDouble(command.substring(1));
                    break;
                case "E":
                    e = Double.parseDouble(command.substring(1));
                    break;
                case "F":
                    f = Double.parseDouble(command.substring(1));
                    break;
                case "S":
                    s = Double.parseDouble(command.substring(1));
                    break;
                default:
                    throw new InvalidParameterException("Invalid Parameter.");
            }
        }

        calculateAndAddFullDistance();
        calculateAndAddAxisDistance();

        processor.actualCoordinates = this.destinationPoint;
    }

    private void calculateAndAddFullDistance() {
        double distance = destinationPoint.distance(processor.actualCoordinates);
        processor.fullTravelWay += distance;
    }

    private void calculateAndAddAxisDistance() {
        processor.travelWayAxis.x += Math.abs(processor.actualCoordinates.x - this.destinationPoint.x);
        processor.travelWayAxis.y += Math.abs(processor.actualCoordinates.y - this.destinationPoint.y);
        processor.travelWayAxis.z += Math.abs(processor.actualCoordinates.z - this.destinationPoint.z);
    }
}