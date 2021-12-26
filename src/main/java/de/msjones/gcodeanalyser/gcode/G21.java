package de.msjones.gcodeanalyser.gcode;

import de.msjones.gcodeanalyser.GCodeProcessor;

public class G21 extends AGCode {
    public G21(GCodeProcessor processor) {
        super(processor);
    }

    public void processCommand(String commands) {
        processor.unit = "mm";
    }
}
