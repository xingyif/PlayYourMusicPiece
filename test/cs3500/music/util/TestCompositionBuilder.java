package cs3500.music.util;

import org.junit.Test;

import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicModelImpl;
import cs3500.music.model.Note;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Alex on 3/21/2016.
 */
public class TestCompositionBuilder {




  @Test
  public void testBuild() {
    MusicModel<Note> model = new MusicModelImpl.Builder().build();
    // checks if the music model contains every field
    assertEquals(0, model.getHighestPitchValue());
    assertEquals(1000000, model.getTempo());
    assertEquals(1000, model.getLowestPitchValue());
    // an empty music model should have no notes, therefor 0 duration
    assertEquals(0, model.getCurrentBeatNumber());
    assertEquals(0, model.getDuration());
    assertEquals(0, model.getAllNotes().size());
    assertEquals(0, model.getCurrentPlayingNotes(0).size());
    assertEquals(0, model.getMusic().size());
    assertEquals(false, model.getMusic().containsKey(2));
  }

  @Test
  public void testSetTempo() {
    MusicModel<Note> model = new MusicModelImpl.Builder().setTempo(200000).build();
    assertEquals(200000, model.getTempo());
    MusicModel<Note> model2 = new MusicModelImpl.Builder().setTempo(9000000).setTempo(0).build();
    assertEquals(0, model2.getTempo());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetTempo_Exception() {
    // throws an IllegalArgument Exception "tempo can't be negative"
    MusicModel<Note> model = new MusicModelImpl.Builder().setTempo(-1).build();
  }

  @Test
  public void testAddNote() {
    MusicModel<Note> model = new MusicModelImpl.Builder().addNote(0, 1, 1, 30, 1).build();
    assertEquals(1, model.getMusic().size());
    assertEquals(true, model.getMusic().containsKey(0));
    assertEquals(30, model.getHighestPitchValue());
    assertEquals(30, model.getLowestPitchValue());
    assertEquals(1000000, model.getTempo());
    assertEquals(1, model.getDuration());
    assertEquals(1, model.getCurrentPlayingNotes(0).size());
    assertEquals(1, model.getAllNotes().size());
    assertEquals(0, model.getCurrentBeatNumber());

    MusicModel<Note> model1 = new MusicModelImpl.Builder().addNote(0, 2, 1, 64, 72)
            .addNote(0, 7, 1, 55, 70)
            .addNote(2, 4, 1, 62, 72).addNote(7, 10, 1, 56, 77).setTempo(200).build();
    assertEquals(3, model1.getMusic().size());
    assertEquals(true, model1.getMusic().containsKey(0));
    assertEquals(true, model1.getMusic().containsKey(2));
    assertEquals(true, model1.getMusic().containsKey(7));
    assertEquals(false, model1.getMusic().containsKey(3));
    assertEquals(64, model1.getHighestPitchValue());
    assertEquals(55, model1.getLowestPitchValue());
    assertEquals(200, model1.getTempo());
    assertEquals(10, model1.getDuration());
    assertEquals(2, model1.getCurrentPlayingNotes(0).size());
    assertEquals(1, model1.getCurrentPlayingNotes(2).size());
    assertEquals(1, model1.getCurrentPlayingNotes(7).size());
    assertEquals(4, model1.getAllNotes().size());
    assertEquals(0, model1.getCurrentBeatNumber());
  }
}
