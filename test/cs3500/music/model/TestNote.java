package cs3500.music.model;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by yifanxing on 3/5/16.
 */
public class TestNote {

  Note note1 = new Note(0, 1, 3, Pitch.C, 0, 0);
  Note note2 = new Note(0, 6, 3, Pitch.C_Sharp, 1, 10);
  Note note4 = new Note(2, 7, 3, Pitch.A, 7, 8);
  Note note5 = new Note(3, 8, 4, Pitch.A_Sharp, 3, 9);
  Note note8 = new Note(10, 32, 3, Pitch.E, 0, 0);
  Note note12 = new Note(60, 66, 4, Pitch.G_Sharp, 10, 20);

  @Test
  public void TestEditNoteLength() {
    this.note1.editNoteLength(5, 6);
    assertEquals(5, this.note1.getStartBeat());
    assertEquals(6, this.note1.getEndBeat());

  }
  @Test
  public void TestEquals_HashCode() {

    assertEquals(false, this.note2.equals(note1));
    assertEquals(true, this.note2.equals(note2));
    assertEquals(this.note2.hashCode(), this.note2.hashCode());
    assertEquals(false, this.note2.hashCode() == note1.hashCode());


  }

  @Test
  public void TestGets() {
    assertEquals(0, this.note1.getStartBeat());
    assertEquals(1, this.note2.getInstrument());
    assertEquals(7, this.note4.getEndBeat());
    assertEquals(3, this.note5.getStartBeat());
    assertEquals(40, this.note8.getPitchValue());
    assertEquals(20, this.note12.getVolume());

  }

}
