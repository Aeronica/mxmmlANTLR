package net.aeronica.libs.mxmml.antlr.core;

public class StructTiedNotes
{
    int midiNote;
    long startingTicks;
    long lengthTicks;
    int volume;

    public String toString()
    {
        return "midi:" + midiNote + ", startingTicks:" + startingTicks + ", lengthTicks:" + lengthTicks + ", volume:" + volume + "\n";
    }
}
