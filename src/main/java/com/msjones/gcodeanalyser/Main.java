package com.msjones.gcodeanalyser;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            throw new Exception("You have to give the gcode file.");
        }
        Main main = new Main();
        main.start(args[0]);
    }

    private void start(String gcodeFileName) throws FileNotFoundException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        // initialize Variables
        File gcodeFile = new File(gcodeFileName);
        readFile(gcodeFile);
    }

    private void readFile(File gcodeFile) throws FileNotFoundException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Scanner scanner = new Scanner(gcodeFile);
        GCodeProcessor processor = new GCodeProcessor();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            processor.processLine(line);
        }

        printResults(processor);
    }

    private void printResults(GCodeProcessor processor){
        System.out.println("Full travel way: " + processor.fullTravelWay + " " + processor.unit);
        System.out.println("Travel way x: " + processor.travelWayAxis.x + " " + processor.unit);
        System.out.println("Travel way y: " + processor.travelWayAxis.y + " " + processor.unit);
        System.out.println("Travel way z: " + processor.travelWayAxis.z + " " + processor.unit);
    }
}
