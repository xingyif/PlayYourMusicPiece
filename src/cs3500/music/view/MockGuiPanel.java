package cs3500.music.view;

import java.util.ArrayList;

import cs3500.music.model.MusicModel;
import cs3500.music.model.Note;
import cs3500.music.model.Pitch;

/**
 * Created by Alex on 3/23/2016.
 */
public class MockGuiPanel extends ConcreteGuiViewPanel {

  MusicModel<Note> model;

  MockGuiPanel(MusicModel<Note> model) {
    super(model);
    this.model = model;
  }

  /**
   * Prints all pitch values from the lowest pitch to highest pitch
   *  in a vertical line along the left border of the frame.
   */
  public String printPitches() {
    int lowest = this.model.getLowestPitchValue();
    int highest = this.model.getHighestPitchValue();

    int pitchCounter = highest;
    int rowHeight = NOTE_PIXEL_WIDTH*2;
    StringBuilder sb = new StringBuilder();
    while (pitchCounter >= lowest) {
      Pitch p = Pitch.findPitch(pitchCounter % 12);
      sb.append(p.toString() + String.valueOf(pitchCounter / 12)
              + " (0, "
              + String.valueOf(rowHeight) + ")\n");
      rowHeight += NOTE_PIXEL_WIDTH;
      pitchCounter -= 1;
    }
    return sb.toString();
  }

  /**
   * Prints a line at the top of the view that contains the beginning beat
   * number for every measure in the composition.
   */
  public String printBeatHeader() {
    int musicLength = this.model.getDuration();

    StringBuilder sb = new StringBuilder();
    for (int measure = 0; measure <= musicLength/4; measure += 1) {
      sb.append(String.valueOf(measure*4) + "\t");
    }
    return sb.toString();
  }

  /**
   * prints the notes as rectangles and place them according to its startBeat
   * and pitchValue
   */
  public String printAllNotes() {
    ArrayList<Note> allNotes = this.model.getAllNotes();

    int duration;
    StringBuilder sb = new StringBuilder();
    for (Note note : allNotes) {
      duration = note.getEndBeat() - note.getStartBeat();
      // THE GREEN RECTANGLE
      sb.append("Green, (");
      sb.append(String.valueOf(PITCH_COLUMN_WIDTH + note.getStartBeat() *NOTE_PIXEL_WIDTH));
      sb.append(", ");
      sb.append(String.valueOf(NOTE_PIXEL_WIDTH + 5 + NOTE_PIXEL_WIDTH *(this.model
              .getHighestPitchValue() - note.getPitchValue())));
      sb.append("), ");
      sb.append(String.valueOf(NOTE_PIXEL_WIDTH*(duration)));
      sb.append(", ");
      sb.append(String.valueOf(NOTE_PIXEL_WIDTH));
      sb.append("\n");

      // THE BLACK SQUARE
      sb.append("Black, (");
      sb.append(String.valueOf(PITCH_COLUMN_WIDTH + note.getStartBeat() *NOTE_PIXEL_WIDTH));
      sb.append(", ");
      sb.append(String.valueOf(NOTE_PIXEL_WIDTH + 5 + NOTE_PIXEL_WIDTH *(this.model
              .getHighestPitchValue() - note.getPitchValue())));
      sb.append("), ");
      sb.append(String.valueOf(NOTE_PIXEL_WIDTH));
      sb.append(", ");
      sb.append(String.valueOf(NOTE_PIXEL_WIDTH));
      sb.append("\n");
    }

    return sb.toString();
  }

  /**
   * prints the grid lines of the music, print a line at each 4 beats(a
   * measure), and at each pitch
   */
  public String printGrid() {
    int musicLength = this.model.getDuration();
    int lowest = this.model.getLowestPitchValue();
    int highest = this.model.getHighestPitchValue();

    StringBuilder sb = new StringBuilder("Rectangles for staff at coordinates: (x, y)\n");
    for (int p = 0; p <= highest-lowest; p += 1) {
      for (int m = 0; m <= musicLength/4 - 1; m += 1) {
        sb.append("(");
        sb.append(String.valueOf(PITCH_COLUMN_WIDTH + m*NOTE_PIXEL_WIDTH*4));
        sb.append(", ");
        sb.append(String.valueOf(NOTE_PIXEL_WIDTH + 5 + p*NOTE_PIXEL_WIDTH));
        sb.append(")  ");
      }
      sb.append("\n");
    }
    return sb.toString();
  }

  /**
   * prints the cursor which keeps track of the current beat
   */
  public String printCursor() {
    int highest = this.model.getHighestPitchValue();
    int lowest = this.model.getLowestPitchValue();
    int beatNum = this.model.getCurrentBeatNumber();

    StringBuilder sb = new StringBuilder("Beat cursor details: ");
    sb.append("Color = Red | Begins at (");
    sb.append(String.valueOf(PITCH_COLUMN_WIDTH + beatNum*NOTE_PIXEL_WIDTH));
    sb.append(", ");
    sb.append(String.valueOf(NOTE_PIXEL_WIDTH + 7));
    sb.append(") and ends at (");
    sb.append(String.valueOf(PITCH_COLUMN_WIDTH + beatNum*NOTE_PIXEL_WIDTH));
    sb.append(", ");
    sb.append(String.valueOf(NOTE_PIXEL_WIDTH + 3 + NOTE_PIXEL_WIDTH*(highest-lowest+1)));
    sb.append(")\n");

    return sb.toString();
  }
}
