package cs3500.music.view;

import org.junit.Test;

import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicModelImpl;
import cs3500.music.model.Note;
import cs3500.music.model.Pitch;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Alex on 3/21/2016.
 */
public class TestConsoleView {
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

  ConsoleView consoleView = new ConsoleView(music1);
  ConsoleView consoleView2 = new ConsoleView(music2);



  @Test
  public void TestPrintFirstLine() {
    music1.add(note1);
    music1.add(note2);
    assertEquals("   C3  C#3 \n", consoleView.printFirstLine());
    music1.getMusic().clear();
    music1.add(note1);
    music1.add(note1); // replace note 1 to check if add replace a duplicate note

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
    assertEquals("    C3  C#3   D3  D#3   E3   F3  F#3   G3  G#3   A3  A#3   B3   C4  C#4   D4  "
            +
            "D#4   E4   F4  F#4   G4  G#4   A4  A#4   B4   C5  C#5   D5  D#5   " +
            "E5   F5  F#5   G5 " +
            " G#5   A5  A#5   B5   C6  C#6   D6  D#6   E6   F6  F#6   " +
            "G6  G#6   A6  A#6   B6   C7 " +
            " C#7   D7 \n", consoleView.printFirstLine());
    music1.getMusic().clear();
    music2.getMusic().clear();

    music2.add(note12);
    assertEquals("   G#4 \n", consoleView2.printFirstLine());
    music2.add(note14);
    assertEquals("   G#4   A4  A#4   B4   C5  C#5   D5  D#5   E5   F5  F#5   G5  G#5   " +
            "A5  A#5   B5   C6  C#6   D6  D#6   E6   F6  F#6   G6  G#6   A6  A#6   B6   C7  " +
            "C#7   D7  D#7   E7   F7  F#7   G7  G#7   A7  A#7   B7   C8  C#8   D8  D#8   E8  " +
            " F8  F#8   G8  G#8   A8  A#8   B8   C9  C#9   D9  D#9   E9   F9  F#9   G9  G#9  " +
            " A9  A#9   B9  C10 \n", consoleView2.printFirstLine());
    music2.getMusic().clear();

  }

  @Test
  public void TestPrintMusic_Pitch() {
    music2.add(note16);
    assertEquals("  A#10\n", consoleView2.printFirstLine());
  }


  @Test
  public void TestPrintMusic1() {
    this.music1.add(note1);
    this.music1.add(note2);
    assertEquals("   C3  C#3 \n" +
            "0  X    X  \n" +
            "1  |    |  \n" +
            "2       |  \n" +
            "3       |  \n" +
            "4       |  \n" +
            "5       |  \n" +
            "6       |  \n", consoleView.printMusic());
    this.music2.add(note2);
    this.music2.add(note6);
    assertEquals("  C#3   D3  D#3   E3   F3  F#3   G3  G#3   A3  A#3   B3" +
            "   C4  C#4   D4  D#4   E4   F4  F#4   G4  G#4   A4  A#4   B4 " +
            "  C5  C#5   D5  D#5   E5   F5  F#5   G5  G#5   A5  A#5   B5 \n" +
            "0  X                                                                           "
            +
            "                                                                               "
            +
            "                  \n" +
            "1  |                                                                             "
            +
            "                                                                                 "
            +
            "              \n" +
            "2  |                                                                               "
            +
            "                                                                                   "
            +
            "          \n" +
            "3  |                                                                                "
            +
            "                                                                                   "
            +
            "      X  \n" +
            "4  |                                                                                "
            +
            "                                                                                   " +
            "      |  \n" +
            "5  |                                                                               " +
            "                                                                                   " +
            "       |  \n" +
            "6  |                                                                                "
            +
            "" +
            "                                                                                    "
            +
            "     |  \n" +
            "7                                                                                   "
            +
            "                                                                                    "
            +
            "     |  \n", consoleView2.printMusic());
  }

  @Test
  public void TestPrintMusic_Long() {
    music1.add(note1);
    music1.add(note2);
    music1.add(note3);
    music1.add(note4);
    music1.add(note5);
    music1.add(note6);
    music1.add(note7);
    music1.add(note8);
    music1.add(note10);
    music1.add(note11);

    // *Note: the pitches are two long and it exceeds 100 lines, I had to hit "enter"
    // and break the pattern in order to successfully submit.

    assertEquals( "    C3  C#3   D3  D#3   E3   F3  F#3   G3  G#3   A3  A#3   B3   C4  C#4" +
            "   D4  D#4 " +
            "  E4   F4  F#4   G4  G#4   A4  A#4   B4   C5  C#5   D5  D#5   E5   F5  F#5   G5" +
            "  G#5   A5 " +
            " A#5   B5   C6  C#6   D6  D#6   E6   F6  F#6   G6  G#6   A6  A#6   B6   C7  C#7 " +
            "  D7 \n" +
            " 0  X    X                             X                                       " +
            "     " +
            "                                                                               " +
            "          " +
            "                                                                         " +
            "           \n" +
            " 1  |    |                             |                                        " +
            "         " +
            "                                                                               " +
            "         " +
            "                                                                             X  \n" +
            " 2       |                                       X                             " +
            "           " +
            "                                                                                " +
            "          " +
            "                                                                          |  \n" +
            " 3       |                                       |                               " +
            "         " +
            "                        X                                                         " +
            "       X " +
            "                                                                         |  \n" +
            " 4       |                                       |                               " +
            "          " +
            "                       |                                                          " +
            "      |   " +
            "                                                                          \n" +
            " 5       |                                       |                                 " +
            "        " +
            "                       |                                                           " +
            "     | " +
            "                                                                            \n" +
            " 6       |                                       |                               " +
            "                                 |                                               " +
            "                 |                   X                                           " +
            "              \n" +
            " 7                                               |                                 " +
            "                               |                                                  " +
            "              |                   |                                               " +
            "          \n" +
            " 8                                                                              " +
            "    " +
            "                              |                                                  " +
            "    " +
            "                              |                                                  " +
            "       \n" +
            " 9                                                                                " +
            "   " +
            "                                                                                " +
            "     " +
            "                             |                                                  " +
            "       \n" +
            "10                      X         X                                                " +
            "                                                                               " +
            "                                     |                                        " +
            "                 \n" +
            "11                      |         |                                             " +
            "                                                                                 " +
            "                                                                          " +
            "                      \n" +
            "12                      |                                                        " +
            "                                                                            " +
            "                                                                          " +
            "                          \n" +
            "13                      |                                                   " +
            "                                                                       " +
            "                                                                       " +
            "                                       \n" +
            "14                      |                                                 " +
            "                                                                            " +
            "                                                                     " +
            "                                      \n" +
            "15                      |                                                       " +
            "                                                                            " +
            "                                                                          " +
            "                           \n" +
            "16                      |                                                     " +
            "                                                                           " +
            "                                                                            " +
            "                            \n" +
            "17                      |                                                     " +
            "                                                                               " +
            "                                                                             " +
            "                       \n" +
            "18                      |                                                       " +
            "                                                                              " +
            "                                                                              " +
            "                     \n" +
            "19                      |                                                      " +
            "                                                                               " +
            "                                                                            " +
            "                       \n" +
            "20                      |                                                      " +
            "                                                                              " +
            "                                                                               " +
            "                     \n" +
            "21                      |                                                     " +
            "                                                                              " +
            "                                                                              " +
            "                       \n" +
            "22                      |                                                      " +
            "                                                                                " +
            "                                                                               " +
            "                   \n" +
            "23                      |                                                      " +
            "                                                                               " +
            "                                                                               " +
            "                    \n" +
            "24                      |                                                        " +
            "                                                                               " +
            "                                                                               " +
            "                  \n" +
            "25                      |                                                        " +
            "                                                                               " +
            "                                                                               " +
            "                  \n" +
            "26                      |                                                       " +
            "                                                                                " +
            "                                                                                " +
            "                 \n" +
            "27                      |                                                        " +
            "                                                                                 " +
            "                                                                                  " +
            "             \n" +
            "28                      |                                                         " +
            "                                                                                  " +
            "                                                                                  " +
            "           \n" +
            "29                      |                                                          " +
            "                                                                                   " +
            "                                                                                   " +
            "        \n" +
            "30                      |                                                          " +
            "                                                                                   " +
            "                                                                                   " +
            "        \n" +
            "31                      |                                                          " +
            "                                                                                   " +
            "                                                                                   " +
            "        \n" +
            "32                      |                                         " +
            "                  " +
            "                                                                     " +
            "               " +
            "                                                                     " +
            "               " +
            "     \n", consoleView.printMusic());
  }

  @Test
  public void TestPrintMusic() {
    music1.add(note1);
    music1.add(note2);
    music1.add(note4);
    music1.add(note8);
    music1.add(note10);
    assertEquals("    C3  C#3   D3  D#3   E3   F3  F#3   G3  G#3   A3 \n" +
                    " 0  X    X                                          \n" +
                    " 1  |    |                                          \n" +
                    " 2       |                                       X  \n" +
                    " 3       |                                       |  \n" +
                    " 4       |                                       |  \n" +
                    " 5       |                                       |  \n" +
                    " 6       |                                       |  \n" +
                    " 7                                               |  \n" +
                    " 8                                                  \n" +
                    " 9                                                  \n" +
                    "10                      X         X                 \n" +
                    "11                      |         |                 \n" +
                    "12                      |                           \n" +
                    "13                      |                           \n" +
                    "14                      |                           \n" +
                    "15                      |                           \n" +
                    "16                      |                           \n" +
                    "17                      |                           \n" +
                    "18                      |                           \n" +
                    "19                      |                           \n" +
                    "20                      |                           \n" +
                    "21                      |                           \n" +
                    "22                      |                           \n" +
                    "23                      |                           \n" +
                    "24                      |                           \n" +
                    "25                      |                           \n" +
                    "26                      |                           \n" +
                    "27                      |                           \n" +
                    "28                      |                           \n" +
                    "29                      |                           \n" +
                    "30                      |                           \n" +
                    "31                      |                           \n" +
                    "32                      |                           \n",
            consoleView.printMusic());
  }

  @Test
  public void TestPrintMusic_TwoDigitRow() {
    this.music1.add(note1);
    this.music1.add(note2);
    this.music1.add(note3);
    this.music1.add(note4);
    this.music1.add(note5);
    this.music1.add(note6);
    this.music1.add(note7);
    this.music1.add(note8);
    this.music1.add(note9);
    this.music1.add(note12);
    this.music1.add(note10);
    this.music1.add(note11);
    this.music1.add(note17);
    this.music1.add(note18);

    assertEquals("    C3  C#3   D3  D#3   E3   F3  F#3   G3  G#3   A3  A#3   B3   C4  C#4" +
            "   D4  D#4   E4   F4  F#4   G4  G#4   A4  A#4   B4   C5  C#5   D5  D#5   E5 " +
            "  F5  F#5   G5  " +
            "G#5   A5  A#5   B5   C6  C#6   D6  D#6   E6   F6  F#6   G6  G#6   A6  A#6   B6" +
            "   C7  C#7  " +
            " D7 \n" +
            " 0  X    X                             X                                      " +
            "             " +
            "                                                                                " +
            "          " +
            "                                                                            \n" +
            " 1  |    |                             |                                       " +
            "            " +
            "                                                                                " +
            "            " +
            "                                                                       X  \n" +
            " 2       |                                       X                              " +
            "            " +
            "                                                                               " +
            "              " +
            "                                                                     |  \n" +
            " 3       |                                       |                               " +
            "            " +
            "                     X                                                          " +
            "      X      " +
            "                                                                    |  \n" +
            " 4       |                                       |                           " +
            "            " +
            "                         |                                                     " +
            "           | " +
            "                                                                            \n" +
            " 5       |                                       |                             " +
            "           " +
            "                        |                                                        " +
            "        |  " +
            "                                                                           \n" +
            " 6       |                                       |                             " +
            "           " +
            "                        |                                                        " +
            "        |" +
            "                   X                                                         \n" +
            " 7                                               |                               " +
            "          " +
            "                       |                                                         " +
            "       |  " +
            "                 |                                                         \n" +
            " 8                      X                                                         " +
            "        " +
            "                        |                                                        " +
            "         " +
            "                   |                                                         \n" +
            " 9                      |                                                       " +
            "          " +
            "                                                                                 " +
            "          " +
            "                  |                                                         \n" +
            "10                      X         X                                               " +
            "                                                                                   " +
            "                                  |                                             " +
            "            \n" +
            "11                      |         |                                                 "
            +
            "                                                                                    "
            +
            "                                                                                    "
            +
            "     \n" +
            "12                      |                                                           "
            +
            "                                                                                    "
            +
            "                                                                                 " +
            "        \n" +
            "13                      |                                                         " +
            "                                                                                " +
            "                                                                                " +
            "               \n" +
            "14                      |                                                       " +
            "     " +

            "                                                                                 " +
            "                                                                                 " +
            "          \n" +
            "15                      |                                                   " +
            "                                                                             " +
            "                                                                               " +
            "                         \n" +
            "16                      |                                                    " +
            "                                                                             " +
            "                                                                              " +
            "                         \n" +
            "17                      |                                                        " +
            "                                                                                " +
            "                                                                                " +
            "                \n" +
            "18                      |         X                                             " +
            "                                                                                " +
            "                                                                                " +
            "                 \n" +
            "19                      |         |                                              " +
            "                                                                                 " +
            "                                                                                 " +
            "              \n" +
            "20                      |         |                                               " +
            "                                                                                   " +
            "                                                                                   " +
            "         \n" +
            "21                      |         |                                                 "
            +
            "                                                                                    "
            +
            "                                                                                    "
            +
            "     \n" +
            "22                      |         |                                               " +
            "                                                                                   " +
            "                                                                                  " +
            "          \n" +
            "23                      |         |                                               " +
            "                                                                                   " +
            "                                                                                   " +
            "         \n" +
            "24                      |                                                          " +
            "                                                                                   " +
            "                                                                                   " +
            "        \n" +
            "25                      |                                                          " +
            "                                                                                   " +
            "                                                                                   " +
            "        \n" +
            "26                      |                                                         " +
            "                                                                                  " +
            "                                                                                  " +
            "           \n" +
            "27                      |                                                          " +
            "                                                                                    "
            +
            "                                                                                    "
            +
            "      \n" +
            "28                      |                                                          " +
            "                                                                                   " +
            "                                                                                   " +
            "        \n" +
            "29                      |                                                         " +
            "                                                                                   " +
            "                                                                                   " +
            "         \n" +
            "30                      |                                                           "
            +
            "                                                                                   " +
            "                                                                                   " +
            "       \n" +
            "31                      |                                                     " +
            "                                                                               " +
            "                                                                                " +
            "                    \n" +
            "32                      |                                                       " +
            "                                                                                " +
            "                                                                                " +
            "                 \n" +
            "33                                                                               " +
            "                                                                                 " +
            "                                                                                   " +
            "            \n" +
            "34                                                                              " +
            "                                                                                " +
            "                                                                                " +
            "                 \n" +
            "35                                                                               " +
            "                                                                                  " +
            "                                                                                 " +
            "             \n" +
            "36                                                                                " +
            "                                                                                 " +
            "                                                                                 " +
            "             \n" +
            "37                                                                                 " +
            "                                                                                  " +
            "                                                                                  " +
            "          \n" +
            "38                                                                               " +
            "                                                                                " +
            "                                                                                " +
            "                \n" +
            "39                                                                                " +
            "                                                                                 " +
            "                                                                                   " +
            "           \n" +
            "40                                                                               " +
            "    " +
            "                                                                                 " +
            "                                                                                " +
            "           \n" +
            "41                                                                                 " +
            "                                                                                   " +
            "                                                                                  " +
            "         \n" +
            "42                                                                                  "
            +
            "                                                                                  " +
            "                                                                                  " +
            "         \n" +
            "43                                                                                 " +
            "                                                                                 " +
            "                                                                                  " +
            "   " +
            "        \n" +
            "44                                                                               " +
            "                                                                                   " +
            "                                                                                   " +
            "          \n" +
            "45                                                                                 " +
            "                                                                                    "
            +
            "                                                                                    "
            +
            "      \n" +
            "46                                                                                  "
            +
            "                                                                                  " +
            "                                                                                   " +
            "        \n" +
            "47                                                                              " +
            "                                                                                  " +
            "                                                                                  " +
            "             \n" +
            "48                                                                                " +
            "                                                                                  " +
            "                                                                                  " +
            "           \n" +
            "49                                                                                " +
            "                                                                                   " +
            "                                                                                   " +
            "         \n" +
            "50                           X                                                    " +
            "                                                                                  " +
            "                                                                                   " +
            "          \n" +
            "51                           |                                                     " +
            "                                                                                  " +
            "                                                                                  " +
            "          \n" +
            "52                           |                                                    " +
            "                                                                                  " +
            "                                                                                   " +
            "          \n" +
            "53                           |                                                    " +
            "                                                                                 " +
            "                                                                                 " +
            "             \n" +
            "54                           |                                                    " +
            "                                                                                  " +
            "                                                                                  " +
            "           \n" +
            "55                                                                                " +
            "                                                                                  " +
            "                                                                                  " +
            "           \n" +
            "56                                                                                " +
            "                                                                                   " +
            "                                                                                   " +
            "         \n" +
            "57                                                                               " +
            "                                                                                 " +
            "                                                                                 " +
            "              \n" +
            "58                                                                               " +
            "                                                                                 " +
            "                                                                                 " +
            "              \n" +
            "59                                                                                " +
            "                                                                                  " +
            "                                                                                  " +
            "           \n" +
            "60                                                                                " +
            "                      X                                                           " +
            "                                                                                  " +
            "           \n" +
            "61                                                                               " +
            "                       |                                                         " +
            "                                                                                 " +
            "              \n" +
            "62                                                                                " +
            "                      |                                                            " +
            "                                                                                   " +
            "         \n" +
            "63                                                                                 " +
            "                     |                                                             " +
            "                                                                                   " +
            "        \n" +
            "64                                                                                 " +
            "                     |                                                              "
            +
            "                                                                                   " +
            "       \n" +
            "65                                                                                  "
            +
            "                    |                                                               "
            +
            "                                                                                    "
            +
            "     \n" +
            "66                                                                                 " +
            "                     |                                                             " +
            "                                                                                   " +
            "        \n", consoleView.printMusic());
  }


  @Test
  public void TestMaryLittleLamb() {
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
    ConsoleView consoleViewMary = new ConsoleView(model);
    assertEquals("    E4   F4  F#4   G4  G#4   A4  A#4   B4   C5  C#5   D5  D#5   E5   F5  F#5" +
            "   G5 \n" +
            " 0                 X                                            X                 \n"
            +
            " 1                 |                                            |                 \n"
            +
            " 2                 |                                  X         |                 \n"
            +
            " 3                 |                                  |                           \n"
            +
            " 4                 |                        X         |                           \n"
            +
            " 5                 |                        |                                     \n"
            +
            " 6                 |                        |         X                           \n"
            +
            " 7                 |                                  |                           \n"
            +
            " 8                 X                                  |         X                 \n"
            +
            " 9                 |                                            |                 \n"
            +
            "10                 |                                            X                 \n"
            +
            "11                 |                                            |                 \n"
            +
            "12                 |                                            X                 \n"
            +
            "13                 |                                            |                 \n"
            +
            "14                 |                                            |                 \n"
            +
            "15                 |                                            |                 \n"
            +
            "16                 X                                  X                           \n"
            +
            "17                 |                                  |                           \n"
            +
            "18                 |                                  X                           \n"
            +
            "19                 |                                  |                           \n"
            +
            "20                 |                                  X                           \n"
            +
            "21                 |                                  |                           \n"
            +
            "22                 |                                  |                           \n"
            +
            "23                 |                                  |                           \n"
            +
            "24                 X                                  |         X                 \n"
            +
            "25                 |                                            |                 \n"
            +
            "26                 |                                            |              X  \n"
            +
            "27                                                                             |  \n"
            +
            "28                                                                             X  \n"
            +
            "29                                                                             |  \n"
            +
            "30                                                                             |  \n"
            +
            "31                                                                             |  \n"
            +
            "32                 X                                            X              |  \n"
            +
            "33                 |                                            |                 \n"
            +
            "34                 |                                  X         |                 \n"
            +
            "35                 |                                  |                           \n"
            +
            "36                 |                        X         |                           \n"
            +
            "37                 |                        |                                     \n"
            +
            "38                 |                        |         X                           \n"
            +
            "39                 |                                  |                           \n"
            +
            "40                 X                                  |         X                 \n"
            +
            "41                 |                                            |                 \n"
            +
            "42                 |                                            X                 \n"
            +
            "43                 |                                            |                 \n"
            +
            "44                 |                                            X                 \n"
            +
            "45                 |                                            |                 \n"
            +
            "46                 |                                            X                 \n"
            +
            "47                 |                                            |                 \n"
            +
            "48                 X                                  X         |                 \n"
            +
            "49                 |                                  |                           \n"
            +
            "50                 |                                  X                           \n"
            +
            "51                 |                                  |                           \n"
            +
            "52                 |                                  |         X                 \n"
            +
            "53                 |                                            |                 \n"
            +
            "54                 |                                  X         |                 \n"
            +
            "55                 |                                  |                           \n"
            +
            "56  X              |                        X         |                           \n"
            +
            "57  |                                       |                                     \n"
            +
            "58  |                                       |                                     \n"
            +
            "59  |                                       |                                     \n"
            +
            "60  |                                       |                                     \n"
            +
            "61  |                                       |                                     \n"
            +
            "62  |                                       |                                     \n"
            +
            "63  |                                       |                                     \n"
            +
            "64  |                                       |                                     \n",
            consoleViewMary.printMusic());
  }
}
