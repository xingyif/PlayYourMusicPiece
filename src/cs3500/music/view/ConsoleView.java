package cs3500.music.view;

import java.util.ArrayList;

import cs3500.music.model.MusicModel;
import cs3500.music.model.Note;
import cs3500.music.model.Pitch;

/**
 * Created by Alex on 3/21/2016.
 */
public class ConsoleView implements MusicView {

  MusicModel<Note> model;

  ConsoleView(MusicModel<Note> model) {
    this.model = model;
  }

  @Override
  public void initialize() {
    // do nothing
  }

  /**
   * Prints out textual representation of the music to the console.
   */
  @Override
  public void displayView() {
    System.out.print(this.printMusic());
  }

  /**
   * @return the first line of the music piece, all the pitches
   */
  public String printFirstLine() {
    // adds all the notes to a list
    StringBuilder result = new StringBuilder();


    // gets the width of the largest beat, i.e. 1000 has a width of 4
    int maxBeatWidth = String.valueOf(this.model.getDuration()).length();

    // add a space for for every column of max beat width
    for (int i = 0; i < maxBeatWidth; i++) {
      result.append(" ");
    }

    // print the first line, all the pitches
    for (int i = this.model.getLowestPitchValue();
         i <= this.model.getHighestPitchValue(); i++) {

      int pitchVal = i % 12;
      Pitch pitch1 = Pitch.findPitch(pitchVal);

      int octaveValue = (int) Math.floor(i / 12);
      String pitchString = pitch1.toString() + String.valueOf(octaveValue);
      int pitchStringLength = pitchString.length();

      result.append(this.printPitch(pitchStringLength, pitchString));
    }
    return result.toString() + "\n";

  }

  /**
   * @return the pitch printed as a string
   */
  public String printPitch(int pitchStringLength, String pitchString) {
    String result;
    switch (pitchStringLength) {
      case 2:
        result = "  " + pitchString + " ";
        break;
      case 3:
        result = " " + pitchString + " ";
        break;
      default:
        result = " " + pitchString;
        break;
    }
    return result;

  }

  public String printMusic() {
    ArrayList<Note> listOfNote = this.model.getAllNotes();

    // adds the beat number for all rows
    StringBuilder allRow = new StringBuilder();

    String temp = "";
    for (int j = 0; j < (this.model.getHighestPitchValue() -
            this.model.getLowestPitchValue() + 1) * 5; j++) {
      temp += " ";
    }


    // gets the width of the largest beat, i.e. 1000 has a width of 4
    int maxBeatWidth = String.valueOf(this.model.getDuration()).length();
    String beatNumber = "";
    for (int i = 0; i <= this.model.getDuration(); i++) {
      // prints space for the first line
      beatNumber = String.valueOf(i);
      while (beatNumber.length() < maxBeatWidth) {
        beatNumber = " " + beatNumber;
      }
      allRow.append(beatNumber + temp + "\n");
    }
    String s = allRow.toString();
    for (int beatWidth = 0; beatWidth < maxBeatWidth; beatWidth++) {
      int firstLineLength = this.printFirstLine().length();

      for (Note note : listOfNote) {
        char[] chars = s.toCharArray();
        int indexOfChar = maxBeatWidth + (note.getPitchValue() - this.model
                .getLowestPitchValue()) * 5;
        int rowNum_X = (firstLineLength) * (note.getStartBeat());
        chars[rowNum_X + indexOfChar + 2] = 'X';

        for (int m = 0; m <= note.getEndBeat() - note.getStartBeat() - 1; m++) {
          int rowNum = (firstLineLength) * (note.getStartBeat() + 1 + m);
          chars[rowNum + indexOfChar + 2] = '|';
        }
        s = String.valueOf(chars);
      }
    }

    return this.printFirstLine() + s;
  }
}
