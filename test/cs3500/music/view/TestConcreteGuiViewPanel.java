package cs3500.music.view;

import org.junit.Test;

import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicModelImpl;
import cs3500.music.model.Note;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by yifanxing on 3/19/16.
 */
public class TestConcreteGuiViewPanel {
  //mary-little-lamb
  MusicModel<Note> model = new MusicModelImpl.Builder().addNote(0, 2, 1, 64, 72)
          .addNote(0, 7, 1, 55, 70)
          .addNote(2, 4, 1, 62, 72).addNote(4, 6, 1, 60, 71).addNote(6, 8, 1, 62, 79)
          .addNote(8, 15, 1, 55,
                  79).addNote(8, 10, 1, 64, 85).addNote(10, 12, 1, 64, 78)
          .addNote(12, 15, 1, 64, 74)
          .addNote(16, 24, 1, 55, 77).addNote(16, 18, 1, 62, 75).addNote(18, 20, 1, 62, 77)
          .addNote(20, 24,
                  1, 62, 75).addNote(24, 26, 1, 55, 79).addNote(24, 26, 1, 64, 82)
          .addNote(26, 28, 1, 67,
                  84).addNote(28, 32, 1, 67, 75).addNote(32, 40, 1, 55, 78)
          .addNote(32, 34, 1, 64, 73)
          .addNote(34, 36, 1, 62, 69).addNote(36, 38, 1, 60, 71).addNote(38, 40, 1, 62, 80)
          .addNote(40, 48,
                  1, 55, 79).addNote(40, 42, 1, 64, 84).addNote(42, 44, 1, 64, 76)
          .addNote(44, 46, 1, 64,
                  74).addNote(46, 48, 1, 64, 77).addNote(48, 56, 1, 55, 78)
          .addNote(48, 50, 1, 62, 75)
          .addNote(50, 52, 1, 62, 74).addNote(52, 54, 1, 64, 81).addNote(54, 56, 1, 62, 70)
          .addNote(56, 64,
                  1, 52, 72).addNote(56, 64, 1, 60, 73)
          .build();

  ConcreteGuiViewPanel mary_little_lamb = new ConcreteGuiViewPanel(model);
  GuiViewFrame guiViewFrame = new GuiViewFrame(mary_little_lamb);
  MockGuiPanel mock = new MockGuiPanel(model);

  @Test
  public void testGuiViewFrameContains() {
    // The pitch name, G5
    assertTrue(guiViewFrame.contains(0, 40));
    // The beat number, 0
    assertTrue(guiViewFrame.contains(40, 0));
    // The highest of the first notes
    assertTrue(guiViewFrame.contains(40, 85));
    // The top left grid rectangle
    assertTrue(guiViewFrame.contains(40, 25));
    // The top end of the beat cursor
    assertTrue(guiViewFrame.contains(40, 27));
  }

  @Test
  public void testPrintPitches() {
    // Should print from G5 to E4
    assertEquals("G5 (0, 40)\n" +
            "F#5 (0, 60)\n" +
            "F5 (0, 80)\n" +
            "E5 (0, 100)\n" +
            "D#5 (0, 120)\n" +
            "D5 (0, 140)\n" +
            "C#5 (0, 160)\n" +
            "C5 (0, 180)\n" +
            "B4 (0, 200)\n" +
            "A#4 (0, 220)\n" +
            "A4 (0, 240)\n" +
            "G#4 (0, 260)\n" +
            "G4 (0, 280)\n" +
            "F#4 (0, 300)\n" +
            "F4 (0, 320)\n" +
            "E4 (0, 340)\n", mock.printPitches());
  }

  @Test
  public void testPrintBeatHeader() {
    // Should print from 0 to 64 with \t after every number
    assertEquals("0\t4\t8\t12\t16\t20\t24\t28\t32\t36\t40\t44\t48" +
            "\t52\t56\t60\t64\t",
            mock.printBeatHeader());
  }

  @Test
  public void testPrintAllNotes() {
    // Should print "Green, (x, y), length, height"
    // followed by "Black, (x, y), length, height"
    assertEquals("Green, (40, 85), 40, 20\n" +
            "Black, (40, 85), 20, 20\n" +
            "Green, (40, 265), 140, 20\n" +
            "Black, (40, 265), 20, 20\n" +
            "Green, (80, 125), 40, 20\n" +
            "Black, (80, 125), 20, 20\n" +
            "Green, (120, 165), 40, 20\n" +
            "Black, (120, 165), 20, 20\n" +
            "Green, (160, 125), 40, 20\n" +
            "Black, (160, 125), 20, 20\n" +
            "Green, (200, 265), 140, 20\n" +
            "Black, (200, 265), 20, 20\n" +
            "Green, (200, 85), 40, 20\n" +
            "Black, (200, 85), 20, 20\n" +
            "Green, (240, 85), 40, 20\n" +
            "Black, (240, 85), 20, 20\n" +
            "Green, (280, 85), 60, 20\n" +
            "Black, (280, 85), 20, 20\n" +
            "Green, (360, 265), 160, 20\n" +
            "Black, (360, 265), 20, 20\n" +
            "Green, (360, 125), 40, 20\n" +
            "Black, (360, 125), 20, 20\n" +
            "Green, (400, 125), 40, 20\n" +
            "Black, (400, 125), 20, 20\n" +
            "Green, (440, 125), 80, 20\n" +
            "Black, (440, 125), 20, 20\n" +
            "Green, (520, 265), 40, 20\n" +
            "Black, (520, 265), 20, 20\n" +
            "Green, (520, 85), 40, 20\n" +
            "Black, (520, 85), 20, 20\n" +
            "Green, (560, 25), 40, 20\n" +
            "Black, (560, 25), 20, 20\n" +
            "Green, (600, 25), 80, 20\n" +
            "Black, (600, 25), 20, 20\n" +
            "Green, (680, 265), 160, 20\n" +
            "Black, (680, 265), 20, 20\n" +
            "Green, (680, 85), 40, 20\n" +
            "Black, (680, 85), 20, 20\n" +
            "Green, (720, 125), 40, 20\n" +
            "Black, (720, 125), 20, 20\n" +
            "Green, (760, 165), 40, 20\n" +
            "Black, (760, 165), 20, 20\n" +
            "Green, (800, 125), 40, 20\n" +
            "Black, (800, 125), 20, 20\n" +
            "Green, (840, 265), 160, 20\n" +
            "Black, (840, 265), 20, 20\n" +
            "Green, (840, 85), 40, 20\n" +
            "Black, (840, 85), 20, 20\n" +
            "Green, (880, 85), 40, 20\n" +
            "Black, (880, 85), 20, 20\n" +
            "Green, (920, 85), 40, 20\n" +
            "Black, (920, 85), 20, 20\n" +
            "Green, (960, 85), 40, 20\n" +
            "Black, (960, 85), 20, 20\n" +
            "Green, (1000, 265), 160, 20\n" +
            "Black, (1000, 265), 20, 20\n" +
            "Green, (1000, 125), 40, 20\n" +
            "Black, (1000, 125), 20, 20\n" +
            "Green, (1040, 125), 40, 20\n" +
            "Black, (1040, 125), 20, 20\n" +
            "Green, (1080, 85), 40, 20\n" +
            "Black, (1080, 85), 20, 20\n" +
            "Green, (1120, 125), 40, 20\n" +
            "Black, (1120, 125), 20, 20\n" +
            "Green, (1160, 325), 160, 20\n" +
            "Black, (1160, 325), 20, 20\n" +
            "Green, (1160, 165), 160, 20\n" +
            "Black, (1160, 165), 20, 20\n", mock.printAllNotes());
  }

  @Test
  public void testPrintGrid() {
    assertEquals("Rectangles for staff at coordinates: (x, y)\n" +
            "(40, 25)  (120, 25)  (200, 25)  (280, 25)  (360, 25)  (440, 25)  (520, 25)  (600, " +
            "25)  (680, 25)  (760, 25)  (840, 25)  (920, 25)  (1000, 25)  (1080, 25)  (1160, 25) "
            +
            " (1240, 25)  \n" +
            "(40, 45)  (120, 45)  (200, 45)  (280, 45)  (360, 45)  (440, 45)  (520, 45)  (600, " +
            "45)  (680, 45)  (760, 45)  (840, 45)  (920, 45)  (1000, 45)  (1080, 45)  (1160, 45) "
            +
            " (1240, 45)  \n" +
            "(40, 65)  (120, 65)  (200, 65)  (280, 65)  (360, 65)  (440, 65)  (520, 65)  (600, " +
            "65)  (680, 65)  (760, 65)  (840, 65)  (920, 65)  (1000, 65)  (1080, 65)  (1160, 65) "
            +
            " (1240, 65)  \n" +
            "(40, 85)  (120, 85)  (200, 85)  (280, 85)  (360, 85)  (440, 85)  (520, 85)  (600, " +
            "85)  (680, 85)  (760, 85)  (840, 85)  (920, 85)  (1000, 85)  (1080, 85)  (1160, 85) "
            +
            " (1240, 85)  \n" +
            "(40, 105)  (120, 105)  (200, 105)  (280, 105)  (360, 105)  (440, 105)  (520, 105)  "
            +
            "(600, 105)  (680, 105)  (760, 105)  (840, 105)  (920, 105)  (1000, 105)  (1080, 105)"
            +
            "  (1160, 105)  (1240, 105)  \n" +
            "(40, 125)  (120, 125)  (200, 125)  (280, 125)  (360, 125)  (440, 125)  (520, 125)  "
            +
            "(600, 125)  (680, 125)  (760, 125)  (840, 125)  (920, 125)  (1000, 125)  (1080, 125)"
            +
            "  (1160, 125)  (1240, 125)  \n" +
            "(40, 145)  (120, 145)  (200, 145)  (280, 145)  (360, 145)  (440, 145)  (520, 145)  "
            +
            "(600, 145)  (680, 145)  (760, 145)  (840, 145)  (920, 145)  (1000, 145)  (1080, 145)"
            +
            "  (1160, 145)  (1240, 145)  \n" +
            "(40, 165)  (120, 165)  (200, 165)  (280, 165)  (360, 165)  (440, 165)  (520, 165)  "
            +
            "(600, 165)  (680, 165)  (760, 165)  (840, 165)  (920, 165)  (1000, 165)  (1080, 165)"
            +
            "  (1160, 165)  (1240, 165)  \n" +
            "(40, 185)  (120, 185)  (200, 185)  (280, 185)  (360, 185)  (440, 185)  (520, 185)  "
            +
            "(600, 185)  (680, 185)  (760, 185)  (840, 185)  (920, 185)  (1000, 185)  (1080, 185)"
            +
            "  (1160, 185)  (1240, 185)  \n" +
            "(40, 205)  (120, 205)  (200, 205)  (280, 205)  (360, 205)  (440, 205)  (520, 205)  "
            +
            "(600, 205)  (680, 205)  (760, 205)  (840, 205)  (920, 205)  (1000, 205)  (1080, 205)"
            +
            "  (1160, 205)  (1240, 205)  \n" +
            "(40, 225)  (120, 225)  (200, 225)  (280, 225)  (360, 225)  (440, 225)  (520, 225)  "
            +
            "(600, 225)  (680, 225)  (760, 225)  (840, 225)  (920, 225)  (1000, 225)  (1080, 225)"
            +
            "  (1160, 225)  (1240, 225)  \n" +
            "(40, 245)  (120, 245)  (200, 245)  (280, 245)  (360, 245)  (440, 245)  (520, 245)  "
            +
            "(600, 245)  (680, 245)  (760, 245)  (840, 245)  (920, 245)  (1000, 245)  (1080, 245)"
            +
            "  (1160, 245)  (1240, 245)  \n" +
            "(40, 265)  (120, 265)  (200, 265)  (280, 265)  (360, 265)  (440, 265)  (520, 265)  "
            +
            "(600, 265)  (680, 265)  (760, 265)  (840, 265)  (920, 265)  (1000, 265)  (1080, 265)"
            +
            "  (1160, 265)  (1240, 265)  \n" +
            "(40, 285)  (120, 285)  (200, 285)  (280, 285)  (360, 285)  (440, 285)  (520, 285)  "
            +
            "(600, 285)  (680, 285)  (760, 285)  (840, 285)  (920, 285)  (1000, 285)  (1080, 285)"
            +
            "  (1160, 285)  (1240, 285)  \n" +
            "(40, 305)  (120, 305)  (200, 305)  (280, 305)  (360, 305)  (440, 305)  (520, 305)  "
            +
            "(600, 305)  (680, 305)  (760, 305)  (840, 305)  (920, 305)  (1000, 305)  (1080, 305)"
            +
            "  (1160, 305)  (1240, 305)  \n" +
            "(40, 325)  (120, 325)  (200, 325)  (280, 325)  (360, 325)  (440, 325)  (520, 325)  "
            +
            "(600, 325)  (680, 325)  (760, 325)  (840, 325)  (920, 325)  (1000, 325)  (1080, 325)"
            +
            "  (1160, 325)  (1240, 325)  \n", mock.printGrid());
  }

  @Test
  public void testPrintCursor() {
    assertEquals("Beat cursor details: Color = Red | Begins at (40, 27) and ends at (40, 343)\n",
            mock.printCursor());
  }
}
