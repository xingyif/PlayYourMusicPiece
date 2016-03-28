package cs3500.music.view;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import cs3500.music.model.MusicModel;
import cs3500.music.model.Note;
import cs3500.music.model.Pitch;

/**
 * A dummy view that simply draws a string 
 */
public class ConcreteGuiViewPanel extends JPanel {

  public static final int NOTE_PIXEL_WIDTH = 20;
  public static final int PITCH_COLUMN_WIDTH = 40;

  protected MusicModel<Note> model;

  public ConcreteGuiViewPanel(MusicModel<Note> model) {
    this.model = model;
  }

  @Override
  public void paintComponent(Graphics g){
    this.printBeatHeader(g);
    this.printPitches(g);
    this.printAllNotes(g);
    this.printGrid(g);
    this.printCursor(g);
  }

  /**
   * Prints all pitch values from the lowest pitch to highest pitch
   *  in a vertical line along the left border of the frame.
   * @param g a Graphics instance to draw pitch values
   */
  public void printPitches(Graphics g) {
    int lowest = this.model.getLowestPitchValue();
    int highest = this.model.getHighestPitchValue();

    int pitchCounter = highest;
    int rowHeight = NOTE_PIXEL_WIDTH*2;
    while (pitchCounter >= lowest) {
      Pitch p = Pitch.findPitch(pitchCounter % 12);
      g.drawString(p.toString() + String.valueOf(pitchCounter / 12),
              0,
              rowHeight);
      rowHeight += NOTE_PIXEL_WIDTH;
      pitchCounter -= 1;
    }
  }

  /**
   * Prints a line at the top of the view that contains the beginning beat
   * number for every measure in the composition.
   * @param g the Graphics object
   */
  public void printBeatHeader(Graphics g) {
    int musicLength = this.model.getDuration();

    g.drawString("     ", 0, 0);
    for (int measure = 0; measure <= musicLength/4; measure += 1) {
      g.drawString(this.measureSpacing(g, measure*4),
              PITCH_COLUMN_WIDTH + measure*NOTE_PIXEL_WIDTH*4,
              NOTE_PIXEL_WIDTH);
    }
  }

  /**
   *
    * @param g the graphic object
   * @param measureNum the integer value of the current measure
   * @return the string value of the measure with spaces, every string
   * value of measure should have an equal length
   */
  public String measureSpacing(Graphics g, int measureNum) {
    String result = String.valueOf(measureNum);
    while (g.getFontMetrics().stringWidth(result) < NOTE_PIXEL_WIDTH*4) {
      result += " ";
    }

    return result;
  }

  /**
   * prints the notes as rectangles and place them according to its startBeat
   * and pitchValue
   * @param g the graphic object
   */
  public void printAllNotes(Graphics g) {
    ArrayList<Note> allNotes = this.model.getAllNotes();

    int duration;
    for (Note note : allNotes) {
      duration = note.getEndBeat() - note.getStartBeat();
      g.setColor(new Color(0, 255, 0));
      g.fillRect(PITCH_COLUMN_WIDTH + note.getStartBeat()*NOTE_PIXEL_WIDTH,
              NOTE_PIXEL_WIDTH + 5 +
                      NOTE_PIXEL_WIDTH*(this.model.getHighestPitchValue() - note.getPitchValue()),
              NOTE_PIXEL_WIDTH*(duration),
              NOTE_PIXEL_WIDTH);
      g.setColor(new Color(0, 0, 0));
      g.fillRect(PITCH_COLUMN_WIDTH + note.getStartBeat()*NOTE_PIXEL_WIDTH,
              NOTE_PIXEL_WIDTH + 5 +
                      NOTE_PIXEL_WIDTH*(this.model.getHighestPitchValue() - note.getPitchValue()),
              NOTE_PIXEL_WIDTH,
              NOTE_PIXEL_WIDTH);
    }
  }

  /**
   * prints the grid lines of the music, print a line at each 4 beats(a
   * measure), and at each pitch
   * @param g the graphic object
   */
  public void printGrid(Graphics g) {
    int musicLength = this.model.getDuration();
    int lowest = this.model.getLowestPitchValue();
    int highest = this.model.getHighestPitchValue();
    Graphics2D g2 = (Graphics2D) g;

    g2.setColor(Color.black);
    g2.setStroke(new BasicStroke(2));
    for (int m = 0; m <= musicLength/4 - 1; m += 1) {
      for (int p = 0; p <= highest-lowest; p += 1) {
        g2.drawRect(PITCH_COLUMN_WIDTH + m*NOTE_PIXEL_WIDTH*4,
                NOTE_PIXEL_WIDTH + 5 + p*NOTE_PIXEL_WIDTH,
                NOTE_PIXEL_WIDTH*4,
                NOTE_PIXEL_WIDTH);
      }
    }
  }

  /**
   * prints the cursor which keeps track of the current beat
   * @param g the graphic object
   */
  public void printCursor(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    int highest = this.model.getHighestPitchValue();
    int lowest = this.model.getLowestPitchValue();
    int beatNum = this.model.getCurrentBeatNumber();
    g2.setColor(new Color(255, 0, 0));
    g2.setStroke(new BasicStroke(3));
    g2.drawLine(PITCH_COLUMN_WIDTH + beatNum*NOTE_PIXEL_WIDTH,
            NOTE_PIXEL_WIDTH + 7,
            PITCH_COLUMN_WIDTH + beatNum*NOTE_PIXEL_WIDTH,
            NOTE_PIXEL_WIDTH + 3 + NOTE_PIXEL_WIDTH*(highest-lowest+1));
  }
}
