package com.msjones.gcodeanalyser;

import javax.vecmath.Point3d;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GCodeProcessor {
    public Point3d actualCoordinates;
    public Point3d travelWayAxis;
    public double fullTravelWay;
    public String unit;

    public GCodeProcessor() {
        this.initialize();
    }

    private void initialize() {
        actualCoordinates = new Point3d(0d, 0d, 0d);
        travelWayAxis = new Point3d(0d, 0d, 0d);
        fullTravelWay = 0d;
        unit = "";
    }

    public void processLine(String line) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        line = line.replaceAll("\\s*;.*", "");
        if (line.isEmpty()) {
            return;
        }


        String[] commandSplit = line.split(" ", 2);
        String command = commandSplit[0];
        try {
            Class<?> commandClass = Class.forName("com.msjones.gcodeanalyser.gcode." + command);
            Constructor<?> cons1 = commandClass.getConstructor(this.getClass());

            Method method = commandClass.getMethod("processCommand", String.class);
            method.invoke(cons1.newInstance(this), commandSplit.length > 1 ? commandSplit[1] : null);
        } catch (ClassNotFoundException e) {
            System.out.println("Ignoring line. No Handler found for Class " + command);
        }
    }

}
