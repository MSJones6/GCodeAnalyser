package com.msjones.gcodeanalyser.gcode;

import com.msjones.gcodeanalyser.GCodeProcessor;

public class G20 extends AGCode {
    public G20(GCodeProcessor processor) {
        super(processor);
    }

    public void processCommand(String commands) {
        processor.unit = "inch";
    }
}
