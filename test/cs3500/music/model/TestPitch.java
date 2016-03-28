package cs3500.music.model;

import junit.framework.TestCase;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by yifanxing on 3/5/16.
 */
public class TestPitch {

  @Test
  public void TestToString() {
    TestCase.assertEquals("A", Pitch.A.toString());
    assertEquals("B", Pitch.B.toString());
    assertEquals("C", Pitch.C.toString());
    assertEquals("C#", Pitch.C_Sharp.toString());
    assertEquals("D#", Pitch.D_Sharp.toString());
    assertEquals("G#", Pitch.G_Sharp.toString());

  }

  @Test
  public void TestGetPitch() {

    assertEquals(9, Pitch.A.getPitch());
    assertEquals(10, Pitch.A_Sharp.getPitch());
    assertEquals(0, Pitch.C.getPitch());
    assertEquals(1, Pitch.C_Sharp.getPitch());
    assertEquals(3, Pitch.D_Sharp.getPitch());
    assertEquals(2, Pitch.D.getPitch());
    assertEquals(6, Pitch.F_Sharp.getPitch());
  }

  @Test
  public void TestFindPitch() {

    assertEquals(Pitch.A, Pitch.findPitch(9));
    assertEquals(Pitch.A_Sharp, Pitch.findPitch(10));
    assertEquals(Pitch.C, Pitch.findPitch(0));
    assertEquals(Pitch.C_Sharp, Pitch.findPitch(1));
    assertEquals(Pitch.D_Sharp, Pitch.findPitch(3));
    assertEquals(Pitch.E, Pitch.findPitch(4));
    assertEquals(Pitch.F_Sharp, Pitch.findPitch(6));

  }


}
