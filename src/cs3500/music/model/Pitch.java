package cs3500.music.model;

import java.util.Objects;

/**
 * Created by yifanxing on 3/1/16.
 */
public enum Pitch {

  C("C"), C_Sharp("C#"),  D("D"), D_Sharp("D#"), E("E"),
  F("F"), F_Sharp("F#"), G("G"), G_Sharp("G#"), A("A"),
  A_Sharp("A#"), B("B");

  private final String pitchName;

  /**
   * constructs a name
   * @param pitchName represents the name of the pitch
   */
  Pitch(String pitchName) {
    this.pitchName = Objects.requireNonNull(pitchName);
  }

  @Override
  public String toString() {
    return this.pitchName;
    }

  public int getPitch() {
    return this.ordinal();
  }

  /**
   * find the corresponding pitching according to the given value
   * @param pitchValue
   * @return
   */
  public static Pitch findPitch(int pitchValue) {
    Pitch pitch;
    switch (pitchValue) {
      case 0 : pitch = Pitch.C;
        break;
      case 1 :  pitch =  Pitch.C_Sharp;
        break;
      case 2 :  pitch =  Pitch.D;
        break;
      case 3 :  pitch =  Pitch.D_Sharp;
        break;
      case 4 :  pitch =  Pitch.E;
        break;
      case 5 :  pitch =  Pitch.F;
        break;
      case 6 :  pitch =  Pitch.F_Sharp;
        break;
      case 7 :  pitch =  Pitch.G;
        break;
      case 8 :  pitch =  Pitch.G_Sharp;
        break;
      case 9 :  pitch =  Pitch.A;
        break;
      case 10 :  pitch =  Pitch.A_Sharp;
        break;
      default:  pitch =  Pitch.B;
        break;
    }
    return pitch;
    }



}
