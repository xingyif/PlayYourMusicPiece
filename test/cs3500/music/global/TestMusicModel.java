package cs3500.music.global;

import org.junit.Test;

import java.util.ArrayList;
import java.util.TreeMap;

import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicModelImpl;
import cs3500.music.model.Note;
import cs3500.music.model.Pitch;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by yifanxing on 3/3/16.
 * Created by Alex on 3/19/2016.
 * This test file is designed to test the methods in the interface of the
 * music model
 */
public class TestMusicModel {

    Note note1 = new Note(0, 1, 3, Pitch.C, 0, 0);
    Note note2 = new Note(0, 6, 3, Pitch.C_Sharp, 1, 10);
    Note note3 = new Note(1, 3, 7, Pitch.D, 5, 6);
    Note note4 = new Note(2, 7, 3, Pitch.A, 7, 8);
    Note note5 = new Note(3, 8, 4, Pitch.A_Sharp, 3, 9);
    Note note6 = new Note(3, 7, 5, Pitch.B, 0, 0);
    Note note7 = new Note(6, 10, 6, Pitch.D_Sharp, 0, 0);
    Note note8 = new Note(10, 32, 3, Pitch.E, 0, 0);
    Note note9 = new Note(50, 54, 3, Pitch.F, 10, 2);
    Note note10 = new Note(10, 11, 3, Pitch.F_Sharp, 0, 0);
    Note note11 = new Note(0, 1, 3, Pitch.G, 0, 0);
    Note note12 = new Note(60, 66, 4, Pitch.G_Sharp, 10, 20);
    Note note14 = new Note(0, 9, 10, Pitch.C, 100, 102);
    Note note16 = new Note(0, 9, 10, Pitch.A_Sharp, 100, 102);
    Note note17 = new Note(8, 30, 3, Pitch.E, 0, 0);
    Note note18 = new Note(18, 23, 3, Pitch.F_Sharp, 0, 0);


    MusicModel<Note> music1 = new MusicModelImpl.Builder().build();
    MusicModel<Note> music2 = new MusicModelImpl.Builder().build();

    @Test
    public void TestAddNote() {
      music1.add(note1);
      music1.add(note1); // replace note 1 to check if add replace a duplicate note

      music1.add(note2);

      assertEquals(true, music1.getMusic().containsKey(note1.getStartBeat()));
      assertEquals(true, music1.getMusic().containsKey(note2.getStartBeat()));
      assertEquals(false, music1.getMusic().containsKey(note3.getStartBeat()));
      assertEquals(note2.getPitchValue(), music1.getHighestPitchValue());
      assertEquals(36, music1.getLowestPitchValue());

      music1.add(note3);
      music1.add(note4);
      music1.add(note5);
      music1.add(note6);
      assertEquals(true, music1.getMusic().containsKey(note3.getStartBeat()));
      assertEquals(true, music1.getMusic().containsKey(note4.getStartBeat()));
      assertEquals(true, music1.getMusic().containsKey(note5.getStartBeat()));
      assertEquals(true, music1.getMusic().containsKey(note6.getStartBeat()));
      assertEquals(note3.getPitchValue(), music1.getHighestPitchValue());
      assertEquals(36, music1.getLowestPitchValue());


      music1.add(note7);
      music1.add(note8);
      music1.add(note9);
      music1.add(note10);
      music1.add(note11);
      music1.add(note12);
      assertEquals(true, music1.getMusic().containsKey(note7.getStartBeat()));
      assertEquals(true, music1.getMusic().containsKey(note8.getStartBeat()));
      assertEquals(true, music1.getMusic().containsKey(note9.getStartBeat()));
      assertEquals(true, music1.getMusic().containsKey(note10.getStartBeat()));
      assertEquals(true, music1.getMusic().containsKey(note11.getStartBeat()));
      assertEquals(true, music1.getMusic().containsKey(note12.getStartBeat()));
      assertEquals(note3.getPitchValue(), music1.getHighestPitchValue());
      assertEquals(36, music1.getLowestPitchValue());
      assertEquals(8, music1.getMusic().size());

    }

    @Test
    public void TestRemoveNote() {

      //music1 is empty now
      assertEquals(0, this.music1.getHighestPitchValue());
      assertEquals(0, this.music1.getMusic().size());


      // initialize the music1
      this.TestAddNote();
      assertEquals(8, music1.getMusic().size());
      assertEquals(3, music1.getMusic().get(note1.getStartBeat()).size());

      music1.removeNote(note1);
      assertEquals(8, music1.getMusic().size());
      assertEquals(2, music1.getMusic().get(note1.getStartBeat()).size());

      music1.removeNote(note2);
      assertEquals(8, music1.getMusic().size());
      assertEquals(1, music1.getMusic().get(note2.getStartBeat()).size());

      music1.removeNote(note3);
      assertEquals(8, music1.getMusic().size());
      assertEquals(0, music1.getMusic().get(note3.getStartBeat()).size());

      music1.removeNote(note6);
      assertEquals(8, music1.getMusic().size());
      assertEquals(1, music1.getMusic().get(note6.getStartBeat()).size());

      music1.removeNote(note3);
      assertEquals(8, music1.getMusic().size());
      assertEquals(0, music1.getMusic().get(note3.getStartBeat()).size());


      music1.removeNote(note9);
      assertEquals(8, music1.getMusic().size());
      assertEquals(0, music1.getMusic().get(note9.getStartBeat()).size());


      music1.removeNote(note12);
      assertEquals(8, music1.getMusic().size());
      assertEquals(0, music1.getMusic().get(note12.getStartBeat()).size());

      music1.getMusic().clear();

    }

    @Test(expected = IllegalArgumentException.class)
    public void TestRemoveException() {
      music1.add(note1);

      this.music1.removeNote(note3);

      music1.getMusic().clear();

    }
    @Test
    public void TestGetHightestPitch_GetLowestPitchValue() {
      music1.add(note1);

      assertEquals(36, music1.getHighestPitchValue());
      assertEquals(36, music1.getLowestPitchValue());
      music1.add(note2);
      assertEquals(37, music1.getHighestPitchValue());
      assertEquals(36, music1.getLowestPitchValue());
      music1.add(note3);
      music1.add(note4);
      assertEquals(86, music1.getHighestPitchValue());
      assertEquals(36, music1.getLowestPitchValue());
      music1.add(note5);
      music1.add(note6);
      music1.add(note7);
      music1.add(note8);
      assertEquals(86, music1.getHighestPitchValue());
      assertEquals(36, music1.getLowestPitchValue());
    }
    @Test
    public void TestGetDuration() {
      music1.add(note1);
      assertEquals(1, music1.getDuration());
      music1.add(note1);
      assertEquals(1, music1.getDuration());
      music1.add(note2);
      assertEquals(6, music1.getDuration());
      music1.add(note3);
      assertEquals(6, music1.getDuration());
      music1.add(note4);
      assertEquals(7, music1.getDuration());
      music1.add(note12);
      assertEquals(66, music1.getDuration());

    }

    @Test
    public void TestMoveNote() {
      TestAddNote();
      // move a note that exist in three elements arrayList
      assertEquals(8, music1.getMusic().size());
      assertEquals(3, music1.getMusic().get(note1.getStartBeat()).size());
      music1.moveNote(note1, 8, 10);
      // check the old arrayList
      assertEquals(2, music1.getMusic().get(note2.getStartBeat()).size());
      // check the old arrayList
      assertEquals(false, music1.getMusic().get(note2.getStartBeat()).contains(note1));
      assertEquals(1, music1.getMusic().get(note1.getStartBeat()).size());
      // checks the new arrayList
      assertEquals(8, note1.getStartBeat());
      assertEquals(9, note1.getEndBeat());
      assertEquals(true, music1.getMusic().containsKey(8));

      // move a note that exists in one element arrayList, only himself
      ArrayList<Note> list = music1.getMusic().get(note3.getStartBeat());
      music1.moveNote(note3, 100, 0);
      assertEquals(0, list.size());
      // check the old arrayList
      assertEquals(1, music1.getMusic().get(note3.getStartBeat()).size());
      // checks the new arrayList
      assertEquals(100, note3.getStartBeat());
      assertEquals(102, note3.getEndBeat());
      assertEquals(true, music1.getMusic().containsKey(100));

      // move a note to a place where already has a note
      ArrayList<Note> list2 = music1.getMusic().get(note12.getStartBeat());
      music1.moveNote(note12, 10, 1); // note10's place
      assertEquals(0, list.size());
      // check the old arrayList
      assertEquals(2, music1.getMusic().get(note12.getStartBeat()).size());
      // checks the new arrayList
      assertEquals(10, note12.getStartBeat());
      assertEquals(16, note12.getEndBeat());
      assertEquals(true, music1.getMusic().containsKey(10));

      music1.getMusic().clear();
    }

    @Test(expected = IllegalArgumentException.class)
    public void TesMoveNote_Exception() {
      music1.add(note1);

      music1.moveNote(note2, 3, 4);
      assertEquals(1, this.music1.getMusic().size());
      music1.moveNote(note11, 3, 4);
      assertEquals(1, this.music1.getMusic().size());
    }

    @Test
    public void TestEditNote() {
      TestAddNote();
      this.music1.editNote(note1, 2, 3);

      assertEquals(2, note1.getStartBeat());
      assertEquals(3, note1.getEndBeat());

      this.music1.editNote(note2, 2, 3);
      assertEquals(2, note2.getStartBeat());
      assertEquals(3, note2.getEndBeat());

      this.music1.editNote(note4, 6, 7);
      assertEquals(6, note4.getStartBeat());
      assertEquals(7, note4.getEndBeat());
      music1.getMusic().clear();

    }
    @Test
    public void TestPlaySimultaneously() {
      this.music1.add(note1);
      this.music2.add(note1);
      this.music1.playSimultaneously(music2);

      assertEquals(1, this.music1.getDuration());
      assertEquals(36, this.music1.getHighestPitchValue());
      assertEquals(36, this.music1.getLowestPitchValue());
      assertEquals(1, this.music1.getMusic().size());
      assertEquals(note1.getStartBeat(), (int)this.music1.getMusic().firstKey());

      this.music1.add(note2);
      this.music2.add(note10);
      this.music1.playSimultaneously(music2);

      assertEquals(11, this.music1.getDuration());
      assertEquals(42, this.music1.getHighestPitchValue());
      assertEquals(36, this.music1.getLowestPitchValue());
      assertEquals(2, this.music1.getMusic().size());
      assertEquals(note1.getStartBeat(), (int)this.music1.getMusic().firstKey());
      assertEquals(note10.getStartBeat(), (int)this.music1.getMusic().lastKey());

      // two overlap notes, both exist in the music
      this.music1.add(note8);
      this.music2.add(note17);
      this.music1.playSimultaneously(music2);

      assertEquals(32, this.music1.getDuration());
      assertEquals(42, this.music1.getHighestPitchValue());
      assertEquals(36, this.music1.getLowestPitchValue());
      assertEquals(3, this.music1.getMusic().size());
      assertEquals(true, this.music1.getMusic().containsKey(note8.getStartBeat()));
      assertEquals(note10.getStartBeat(), (int)this.music1.getMusic().lastKey());
      assertEquals(32, this.music1.getDuration());

      music1.getMusic().clear();
    }

    @Test
    public void TestPlayConsecutively() {
      this.music1.add(note1);
      this.music2.add(note1);
      this.music1.playConsecutively(this.music2);
      int note1Length = note1.getEndBeat() - note1.getStartBeat();

      ArrayList<Note> list = music1.getMusic().get(this.music1.getMusic().lastKey());
      Note addedNote = list.get(list.size() - 1);
      assertEquals(1, addedNote.getStartBeat());
      assertEquals(2, note1.getEndBeat());

      assertEquals(true, this.music1.getMusic().containsKey(note2.getStartBeat() + note1Length));

      this.music1.add(note8);
      this.music2.add(note17);
      music1.playConsecutively(music2);
      assertEquals(64, music1.getDuration());
      assertEquals(10, note8.getStartBeat());
      assertEquals(42, note17.getStartBeat());
      assertEquals(64, note17.getEndBeat());
      this.music1.playConsecutively(music2);

      music1.getMusic().clear();

    }

    @Test
    public void TestPlayConsecutively2() {
      this.music1.add(note1);
      this.music1.add(note2);
      this.music2.add(note3); // 7-9
      this.music2.add(note12); // 66-72
      assertEquals(6, music1.getDuration());
      assertEquals(66, music2.getDuration());


      this.music1.playConsecutively(this.music2);
      int note12Length = note12.getEndBeat() - note12.getStartBeat();

      assertEquals(75, music1.getDuration());

      ArrayList<Note> list2 = music1.getMusic().get(this.music1.getMusic().lastKey());
      Note addedNote2 = list2.get(list2.size() - 1);
      assertEquals(69, addedNote2.getStartBeat());
      assertEquals(75, note12.getEndBeat());
      assertEquals(75, this.music1.getDuration());
      assertEquals(true, this.music1.getMusic().containsKey(note12.getStartBeat()));

      music1.getMusic().clear();


    }

    @Test
    public void TestGetAllNotes() {
      music1.add(note1);
      music1.add(note2);

      ArrayList<Note> listOfNote = new ArrayList<>();
      listOfNote.add(note1);
      listOfNote.add(note2);

      assertEquals(listOfNote, music1.getAllNotes());
    }


    @Test
    public void TestListOfNote() {
      this.music1.add(note1);
      this.music1.add(note1);
      this.music1.add(note2);
      assertEquals(2, this.music1.getAllNotes().size());

      this.music1.add(note12);
      assertEquals(3, this.music1.getAllNotes().size());
      music1.getMusic().clear();


    }


    @Test
    public void TestClearMusicPiece() {
      this.music1.add(note1);
      this.music1.add(note2);
      this.music1.clearMusicPiece();
      assertEquals(false, this.music1.getMusic().containsKey(note1.getStartBeat()));
      assertEquals(false, this.music1.getMusic().containsKey(note2.getStartBeat()));
      assertEquals(0, this.music1.getMusic().size());

      this.TestAddNote();
      this.music1.clearMusicPiece();
      assertEquals(false, this.music1.getMusic().containsKey(note1.getStartBeat()));
      assertEquals(false, this.music1.getMusic().containsKey(note2.getStartBeat()));
      assertEquals(false, this.music1.getMusic().containsKey(note3.getStartBeat()));
      assertEquals(false, this.music1.getMusic().containsKey(note4.getStartBeat()));
      assertEquals(false, this.music1.getMusic().containsKey(note5.getStartBeat()));
      assertEquals(false, this.music1.getMusic().containsKey(note6.getStartBeat()));
      assertEquals(false, this.music1.getMusic().containsKey(note7.getStartBeat()));
      assertEquals(false, this.music1.getMusic().containsKey(note8.getStartBeat()));
      assertEquals(false, this.music1.getMusic().containsKey(note9.getStartBeat()));
      assertEquals(false, this.music1.getMusic().containsKey(note10.getStartBeat()));
      assertEquals(false, this.music1.getMusic().containsKey(note11.getStartBeat()));
      assertEquals(false, this.music1.getMusic().containsKey(note12.getStartBeat()));
      assertEquals(false, this.music1.getMusic().containsKey(note14.getStartBeat()));
      assertEquals(false, this.music1.getMusic().containsKey(note16.getStartBeat()));
      assertEquals(0, this.music1.getMusic().size());
    }

    @Test
    public void testGetCurrentPlayingNotes() {
      music1.getMusic().clear();

      music1.add(note1); // 0-1
      music1.add(note2); // 0-6
      music1.add(note3); // 1-3
      music1.add(note4); // 2-7

      ArrayList<Note> listAtBeat0 = new ArrayList<>();
      listAtBeat0.add(note1);
      listAtBeat0.add(note2);
      ArrayList<Note> listAtBeat1 = new ArrayList<>();
      listAtBeat1.add(note3);
      ArrayList<Note> listAtBeat2 = new ArrayList<>();
      listAtBeat2.add(note4);
      ArrayList<Note> listAtBeat3 = new ArrayList<>();

      ArrayList<Note> current0 = music1.getCurrentPlayingNotes(0);
      ArrayList<Note> current1 = music1.getCurrentPlayingNotes(1);
      ArrayList<Note> current2 = music1.getCurrentPlayingNotes(2);
      ArrayList<Note> current3 = music1.getCurrentPlayingNotes(3);

      assertEquals(listAtBeat0, current0);
      assertEquals(listAtBeat1, current1);
      assertEquals(listAtBeat2, current2);
      assertEquals(listAtBeat3, current3);
    }

  @Test
  public void testGetMusic() {
    this.TestAddNote();
    TreeMap<Integer, ArrayList<Note>> copyMusic1 = this.music1.getMusic();

    // checks if the clone contains all the notes within the previous map
    assertEquals(8, copyMusic1.size());
    assertEquals(true, copyMusic1.containsKey(note1.getStartBeat()));
    assertEquals(true, copyMusic1.containsKey(note2.getStartBeat()));
    assertEquals(true, copyMusic1.containsKey(note3.getStartBeat()));
    assertEquals(true, copyMusic1.containsKey(note4.getStartBeat()));
    assertEquals(true, copyMusic1.containsKey(note5.getStartBeat()));
    assertEquals(true, copyMusic1.containsKey(note6.getStartBeat()));
    assertEquals(true, copyMusic1.containsKey(note7.getStartBeat()));
    assertEquals(true, copyMusic1.containsKey(note8.getStartBeat()));
    assertEquals(true, copyMusic1.containsKey(note9.getStartBeat()));
    assertEquals(true, copyMusic1.containsKey(note10.getStartBeat()));
    assertEquals(true, copyMusic1.containsKey(note11.getStartBeat()));
    assertEquals(true, copyMusic1.containsKey(note12.getStartBeat()));
  }


  @Test
  public void testGetCurrentBeatNumber_and_testUpdateCurrentBeatNumber() {
    assertEquals(0, this.music1.getCurrentBeatNumber());

    music1.add(note1);
    music1.add(note2);
    music1.add(note3);
    music1.add(note4);
    music1.add(note5);
    music1.add(note6);
    music1.add(note7);
    music1.add(note8);
    music1.add(note9);
    music1.add(note10);
    music1.add(note11);
    music1.add(note12);
    this.music1.updateCurrentBeatNumber(12);
    assertEquals(12, this.music1.getCurrentBeatNumber());
    this.music1.updateCurrentBeatNumber(13);
    assertEquals(13, this.music1.getCurrentBeatNumber());
    this.music1.updateCurrentBeatNumber(60);
    assertEquals(60, this.music1.getCurrentBeatNumber());
  }


  @Test(expected = IllegalArgumentException.class)
  public void TestRemoveNote_Exception() {
    // an empty music
    // remove a non-exist note
    this.music1.removeNote(note1);
  }


  @Test(expected = IllegalArgumentException.class)
  public void TestUpdateCurrentBeatNumber() {
    // an empty music
    // given a beat number larger than the current beat number, which is 0
    this.music1.updateCurrentBeatNumber(100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void TestMoveNote_Exception() {
    // an empty music
    // move a non-exist note
    this.music1.moveNote(note1, 1, 20);
  }


  /**
   * test the builder class
   */

  @Test
  public void TestSetTempo_and_GetTempo() {
    assertEquals(1000000, this.music1.getTempo());
    MusicModel<Note> model = new MusicModelImpl.Builder().setTempo(200000)
            .build();
    assertEquals(200000, model.getTempo());

    MusicModel<Note> model2 = new MusicModelImpl.Builder().setTempo(300)
            .build();
    assertEquals(300, model2.getTempo());

    MusicModel<Note> model3 = new MusicModelImpl.Builder().setTempo(900000000)
            .build();
    assertEquals(900000000, model3.getTempo());
  }


  @Test
  public void TestBuilder_AddNote() {
    MusicModel<Note> model = new MusicModelImpl.Builder().addNote(10, 30, 0,
            30, 0).build();
    assertEquals(1000000, model.getTempo());
    assertEquals(true, model.getMusic().containsKey(10));
    assertEquals(1, model.getAllNotes().size());
    assertEquals(30, model.getDuration());
    assertEquals(30, model.getHighestPitchValue());
    assertEquals(30, model.getLowestPitchValue());

    MusicModel<Note> model2 = new MusicModelImpl.Builder().addNote(0, 100, 1, 150, 10)
            .addNote(20, 20, 0, 120, 0).build();
    assertEquals(1000000, model2.getTempo());
    assertEquals(true, model2.getMusic().containsKey(20));
    assertEquals(true, model2.getMusic().containsKey(0));
    assertEquals(2, model2.getAllNotes().size());
    assertEquals(100, model2.getDuration());
    assertEquals(150, model2.getHighestPitchValue());
    assertEquals(120, model2.getLowestPitchValue());
  }

}


