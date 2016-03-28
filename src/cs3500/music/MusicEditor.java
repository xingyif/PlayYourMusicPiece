package cs3500.music;

import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicModelImpl;
import cs3500.music.model.Note;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.MusicView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

public class MusicEditor {
  public static void main(String[] args) throws IOException, InvalidMidiDataException {
    CompositionBuilder<MusicModel<Note>> builder = new MusicModelImpl.Builder();
    Readable fileReader = null;

    Scanner input = new Scanner(System.in);
    String fileName;

    while(fileReader == null) {
      System.out.print("Enter a file name: ");
      try {
        fileName = input.next();
        fileReader = new FileReader(new File(fileName));
      } catch (FileNotFoundException f) {
        System.out.append("Try again: bad file name\n");
      } catch (NoSuchElementException n) {
        System.out.append("Try again: input a file name\n");
      }
    }
    MusicModel<Note> model = MusicReader.parseFile(fileReader, builder);

    MusicView view = null;
    while(view == null) {
      System.out.print("\nEnter \"console\", \"visual\", or \"midi\" to select a view: ");
      switch(input.next()) {
        case "console":
          try {
            view =  new MusicView.ViewFactory(model).create("console");
          } catch (MidiUnavailableException e) {
            e.printStackTrace();
          }
          break;
        case "visual":
          try {
            view =  new MusicView.ViewFactory(model).create("visual");
          } catch (MidiUnavailableException e) {
            e.printStackTrace();
          }
          break;
        case "midi":
          try {
            view =  new MusicView.ViewFactory(model).create("midi");
          } catch (MidiUnavailableException e) {
            e.printStackTrace();
          }
          break;
        default:
          System.out.append("Not a view type");
      }
    }

    view.displayView();
  }
}
