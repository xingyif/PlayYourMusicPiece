package cs3500.music.view;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.ShortMessage;

import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicModelImpl;
import cs3500.music.model.Note;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by yifanxing on 3/22/16.
 */
public class TestMockMidiDevice {

  MidiViewImpl midiView;
  MockMidiDevice mockMidiDevice = new MockMidiDevice();
  MockReceiver mockReceiver;

  CompositionBuilder<MusicModel<Note>> builder = new MusicModelImpl.Builder();
  FileReader fileReader;
  MusicModel<Note> model1;

  public void initializeFileReader() {
    try {
      fileReader = new FileReader(new File("mary-little-lamb.txt"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    model1 = MusicReader.parseFile(fileReader, builder);

    try {
      mockReceiver = (MockReceiver) mockMidiDevice.getReceiver();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    try {
      midiView = new MidiViewImpl(model1, mockMidiDevice);
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void TestMystery() {
    // initializes the conditions
    this.initializeFileReader();
    midiView.displayView();
    assertEquals("note ON 1 64 72 0\n" +
            "note OFF 1 64 72 400000\n" +
            "note ON 1 55 70 0\n" +
            "note OFF 1 55 70 1400000\n" +
            "note ON 1 62 72 400000\n" +
            "note OFF 1 62 72 800000\n" +
            "note ON 1 60 71 800000\n" +
            "note OFF 1 60 71 1200000\n" +
            "note ON 1 62 79 1200000\n" +
            "note OFF 1 62 79 1600000\n" +
            "note ON 1 55 79 1600000\n" +
            "note OFF 1 55 79 3000000\n" +
            "note ON 1 64 85 1600000\n" +
            "note OFF 1 64 85 2000000\n" +
            "note ON 1 64 78 2000000\n" +
            "note OFF 1 64 78 2400000\n" +
            "note ON 1 64 74 2400000\n" +
            "note OFF 1 64 74 3000000\n" +
            "note ON 1 55 77 3200000\n" +
            "note OFF 1 55 77 4800000\n" +
            "note ON 1 62 75 3200000\n" +
            "note OFF 1 62 75 3600000\n" +
            "note ON 1 62 77 3600000\n" +
            "note OFF 1 62 77 4000000\n" +
            "note ON 1 62 75 4000000\n" +
            "note OFF 1 62 75 4800000\n" +
            "note ON 1 55 79 4800000\n" +
            "note OFF 1 55 79 5200000\n" +
            "note ON 1 64 82 4800000\n" +
            "note OFF 1 64 82 5200000\n" +
            "note ON 1 67 84 5200000\n" +
            "note OFF 1 67 84 5600000\n" +
            "note ON 1 67 75 5600000\n" +
            "note OFF 1 67 75 6400000\n" +
            "note ON 1 55 78 6400000\n" +
            "note OFF 1 55 78 8000000\n" +
            "note ON 1 64 73 6400000\n" +
            "note OFF 1 64 73 6800000\n" +
            "note ON 1 62 69 6800000\n" +
            "note OFF 1 62 69 7200000\n" +
            "note ON 1 60 71 7200000\n" +
            "note OFF 1 60 71 7600000\n" +
            "note ON 1 62 80 7600000\n" +
            "note OFF 1 62 80 8000000\n" +
            "note ON 1 55 79 8000000\n" +
            "note OFF 1 55 79 9600000\n" +
            "note ON 1 64 84 8000000\n" +
            "note OFF 1 64 84 8400000\n" +
            "note ON 1 64 76 8400000\n" +
            "note OFF 1 64 76 8800000\n" +
            "note ON 1 64 74 8800000\n" +
            "note OFF 1 64 74 9200000\n" +
            "note ON 1 64 77 9200000\n" +
            "note OFF 1 64 77 9600000\n" +
            "note ON 1 55 78 9600000\n" +
            "note OFF 1 55 78 11200000\n" +
            "note ON 1 62 75 9600000\n" +
            "note OFF 1 62 75 10000000\n" +
            "note ON 1 62 74 10000000\n" +
            "note OFF 1 62 74 10400000\n" +
            "note ON 1 64 81 10400000\n" +
            "note OFF 1 64 81 10800000\n" +
            "note ON 1 62 70 10800000\n" +
            "note OFF 1 62 70 11200000\n" +
            "note ON 1 52 72 11200000\n" +
            "note OFF 1 52 72 12800000\n" +
            "note ON 1 60 73 11200000\n" +
            "note OFF 1 60 73 12800000\n", mockMidiDevice.getOutput());
  }

  @Test
  public void TestSend() {

    StringBuilder stringBuilder = new StringBuilder();
    StringBuilder stringBuilder2 = new StringBuilder();

    MockReceiver mockReceiver = new MockReceiver(stringBuilder);
    MockReceiver mockReceiver2 = new MockReceiver(stringBuilder2);

    MidiMessage message = null;
    MidiMessage message2 = null;
    try {
      message = new ShortMessage(ShortMessage.NOTE_ON, 1, 2, 3);
      message2 = new ShortMessage(ShortMessage.NOTE_OFF, 1, 2, 3);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }

    long timeStamp = 10000;

    mockReceiver.send(message, timeStamp);
    mockReceiver2.send(message2, timeStamp);


    assertEquals("note ON 1 2 3 10000\n", mockReceiver.output.toString());
    assertEquals("note OFF 1 2 3 10000\n", mockReceiver2.output.toString());


  }

}