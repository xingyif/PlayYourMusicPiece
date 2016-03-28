package cs3500.music.model;

/**
 * Created by yifanxing on 3/1/16.
 * Represents a node
 */
public class Note {
  private int startBeat;
  private int endBeat;
  private int pitchValue;
  private int instrument;
  private int volume;


  /**
   * the default constructor that constructs a currentNote
   * @param startBeat represents the start beat of the currentNote
   * @param endBeat represents the end beat of the currentNote
   * @param octave represents the octave of the currentNote
   * @param pitch represents the pitch of the currentNote
   * @param instrument represents the instrument that plays the music
   */
  public Note(int startBeat, int endBeat, int octave, Pitch pitch, int instrument, int volume) {
    if (startBeat < 0 || endBeat < 0 || octave < 0 || volume < 0) {
      throw new IllegalArgumentException("Invalid currentNote: input cannot be less than 0");
    }
    this.startBeat = startBeat;
    this.endBeat = endBeat;
    this.pitchValue = octave * 12 + pitch.getPitch();
    this.instrument = instrument;
    this.volume = volume;
  }

  /**
   * allows the user edit the length currentNote
   * @param startBeat
   * @param endBeat
   */
  public void editNoteLength(int startBeat, int endBeat) {
    this.startBeat = startBeat;
    this.endBeat = endBeat;
  }

  @Override
  public boolean equals(Object o) {
    return (o instanceof Note) &&
            this.startBeat == ((Note) o).startBeat &&
            this.endBeat == ((Note) o).endBeat &&
            this.pitchValue == ((Note) o).pitchValue &&
            this.instrument == ((Note) o).instrument &&
            this.volume == ((Note) o).volume;

  }

  @Override
  public int hashCode() {
    int result = startBeat;
    result = 31 * result + endBeat;
    result = 31 * result + pitchValue;
    result = 31 * result + instrument;
    result = 31 * result + volume;
    return result;
  }

  /**
   *
   * @return the start beat of the note
   */
  public int getStartBeat() {
    return this.startBeat;
  }

  /**
   *
   * @return the end beat of the note
   */
  public int getEndBeat() {
    return this.endBeat;
  }

  /**
   *
   * @return the pitch value
   */
 public int getPitchValue() {
   return this.pitchValue;
 }

  /**
   *
   * @return the instrument
   */
  public int getInstrument() {
    return this.instrument;
  }

  /**
   *
   * @return the volume
   */
  public int getVolume() {
    return this.volume;
  }
}