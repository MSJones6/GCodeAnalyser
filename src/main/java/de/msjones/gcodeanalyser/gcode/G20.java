package de.msjones.gcodeanalyser.gcode;

import de.msjones.gcodeanalyser.GCodeProcessor;

public class G20 extends AGCode {
    public G20(GCodeProcessor processor) {
        super(processor);
    }

    public void processCommand(String commands) {
        processor.unit = "inch";
    }
}
