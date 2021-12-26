package de.msjones.gcodeanalyser.gcode;

import de.msjones.gcodeanalyser.GCodeProcessor;

public abstract class AGCode {
    protected GCodeProcessor processor;

    public abstract void processCommand(String commands);

    public AGCode(GCodeProcessor processor){
        this.processor = processor;
    }
}
