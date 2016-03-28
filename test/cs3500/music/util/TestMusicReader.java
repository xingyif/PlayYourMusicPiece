package cs3500.music.util;

import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;

import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicModelImpl;
import cs3500.music.model.Note;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Alex on 3/21/2016.
 */
public class TestMusicReader {

  CompositionBuilder<MusicModel<Note>> builder = new MusicModelImpl.Builder();


  @Test
  public void TestParseFile() {
    Reader in = new StringReader(
            "tempo 200000\n" +
            "note 0 2 1 64 72\n" +
            "note 0 7 1 55 70\n" +
            "note 2 4 1 62 72\n" +
            "note 4 6 1 60 71\n" +
            "note 6 8 1 62 79\n" +
            "note 8 15 1 55 79\n" +
            "note 8 10 1 64 85\n" +
            "note 10 12 1 64 78\n" +
            "note 12 15 1 64 74\n" +
            "note 16 24 1 55 77\n" +
            "note 16 18 1 62 75\n" +
            "note 18 20 1 62 77\n" +
            "note 20 24 1 62 75\n" +
            "note 24 26 1 55 79\n" +
            "note 24 26 1 64 82\n" +
            "note 26 28 1 67 84\n" +
            "note 28 32 1 67 75\n" +
            "note 32 40 1 55 78\n" +
            "note 32 34 1 64 73\n" +
            "note 34 36 1 62 69\n" +
            "note 36 38 1 60 71\n" +
            "note 38 40 1 62 80\n" +
            "note 40 48 1 55 79\n" +
            "note 40 42 1 64 84\n" +
            "note 42 44 1 64 76\n" +
            "note 44 46 1 64 74\n" +
            "note 46 48 1 64 77\n" +
            "note 48 56 1 55 78\n" +
            "note 48 50 1 62 75\n" +
            "note 50 52 1 62 74\n" +
            "note 52 54 1 64 81\n" +
            "note 54 56 1 62 70\n" +
            "note 56 64 1 52 72\n" +
            "note 56 64 1 60 73\n");
    MusicModel<Note> model = MusicReader.parseFile(in, builder);
    assertEquals(26,model.getMusic().size());
    assertEquals(0,model.getCurrentBeatNumber());
    assertEquals(34,model.getAllNotes().size());
    assertEquals(2,model.getCurrentPlayingNotes(0).size());
    assertEquals(64,model.getDuration());
    assertEquals(67,model.getHighestPitchValue());
    assertEquals(52,model.getLowestPitchValue());
    assertEquals(200000,model.getTempo());

  }


  @Test
  public void TestParseFile2() {
    Reader in = new StringReader(
            "tempo 200000\n");
    MusicModel<Note> model = MusicReader.parseFile(in, builder);
    assertEquals(0,model.getMusic().size());
    assertEquals(0,model.getCurrentBeatNumber());
    assertEquals(0,model.getAllNotes().size());
    assertEquals(0,model.getCurrentPlayingNotes(0).size());
    assertEquals(0,model.getDuration());
    assertEquals(0,model.getHighestPitchValue());
    assertEquals(1000,model.getLowestPitchValue());
    assertEquals(200000,model.getTempo());

  }



  @Test
  public void TestParseFile3() {
    Reader in = new StringReader(
            "note 1 2 3 4 5\n");
    MusicModel<Note> model = MusicReader.parseFile(in, builder);
    assertEquals(1,model.getMusic().size());
    assertEquals(0,model.getCurrentBeatNumber());
    assertEquals(1,model.getAllNotes().size());
    assertEquals(0,model.getCurrentPlayingNotes(0).size());
    assertEquals(1,model.getCurrentPlayingNotes(1).size());
    assertEquals(2,model.getDuration());
    assertEquals(4,model.getHighestPitchValue());
    assertEquals(4,model.getLowestPitchValue());
    assertEquals(1000000,model.getTempo());

  }

  // "Malformed tempo line: "
  @Test(expected = IllegalArgumentException.class)
  public void TestParseFile_Exception() {
    Reader in = new StringReader(
            "tempo ...lalala\n");
    MusicReader.parseFile(in, builder);
    Reader in2 = new StringReader(
            "tempo hua\n");
    MusicReader.parseFile(in2, builder);

  }

  // "Malformed note line: "
  @Test(expected = IllegalArgumentException.class)
  public void TestParseFile_Exception2() {
    Reader in = new StringReader(
            "note 1 2 3 3 ..\n");
    MusicReader.parseFile(in, builder);
    Reader in2 = new StringReader(
            "note ...1 2 3\n");
    MusicReader.parseFile(in2, builder);
    Reader in3 = new StringReader(
            "note 1 2   3 3\n");
    MusicReader.parseFile(in3, builder);
  }


  // "Bad line type: "
  @Test(expected = IllegalArgumentException.class)
  public void TestParseFile_Exception3() {
    Reader in = new StringReader(
            "...\n");
    MusicReader.parseFile(in, builder);
    Reader in2 = new StringReader(
            "bla\n");
    MusicReader.parseFile(in2, builder);
    Reader in3 = new StringReader(
            "222\n");
    MusicReader.parseFile(in3, builder);
  }


}
