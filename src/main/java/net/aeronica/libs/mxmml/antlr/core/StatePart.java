package net.aeronica.libs.mxmml.antlr.core;


import static net.aeronica.libs.mxmml.antlr.core.MMLUtil.clamp;

public class StatePart
{
    private int volume;
    private boolean volumeArcheAge;
    private int octave;
    private int mmlLength;
    private boolean dotted;
    private long runningTicks;
    private boolean tied;

    StatePart() {this.init();}

    public void init()
    {
        volume = 8;
        volumeArcheAge = false;
        octave = 4;
        mmlLength = 4;
        dotted = false;
        runningTicks = 0;
        tied = false;
    }

    @Override
    public String toString()
    {
        return "@PartState: oct=" + octave + ", vol=" + volume + ", mmlLength=" + mmlLength + " ,runningTicks=" + runningTicks + ", tied=" + tied;
    }

    public int getVolume()
    {
        if (this.volumeArcheAge)
            return volume;
        else
            return clamp(0, 127, volume * 127 / 15);
    }

    public void setVolume(int volume)
    {
        this.volume = clamp(0, 127, volume);
        if (this.volume > 15)
            volumeArcheAge = true;
    }

    public int getOctave() {return octave;}

    public void setOctave(int octave) {this.octave = clamp(1, 8, octave);}

    /**
     * You can <<<< an octave to 0, but you can't
     * set octave to 0 via the octave command: o0
     */
    void downOctave() {this.octave = clamp(0, 8, this.octave - 1);}

    void upOctave() {this.octave = clamp(1, 8, this.octave + 1);}

    int getMMLLength() {return mmlLength;}

    boolean isDotted() {return dotted;}

    void setMMLLength(int mmlLength, boolean dotted)
    {
        this.mmlLength = clamp(1, 64, mmlLength);
        this.dotted = dotted;
    }

    void accumulateTicks(long n) {this.runningTicks = this.runningTicks + n;}

    long getRunningTicks() {return runningTicks;}

    boolean isTied() {return tied;}

    void setTied(boolean tied) {this.tied = tied;}
}
