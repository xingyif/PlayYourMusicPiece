package cs3500.music.model;

import java.util.ArrayList;
import java.util.TreeMap;

import cs3500.music.util.CompositionBuilder;

/**
 * Created by yifanxing on 3/1/16.
 */
public final class MusicModelImpl implements MusicModel<Note> {

  TreeMap<Integer, ArrayList<Note>> music;
  private int highestPitchValue;
  private int lowestPitchValue;
  private int currentBeatNumber;
  // represents the speed of the music in microseconds per beat
  private int tempo;


  /**
   * constructs a music piece
   */
  MusicModelImpl() {
    if (lowestPitchValue < 0) {
      throw new IllegalArgumentException("Invalid music: lowestPitchValue cannot be negative");
    }
    if (currentBeatNumber < 0) {
      throw new IllegalArgumentException("Invalid music: currentBeatNumber " +
              "cannot be negative");
    }
    if (tempo < 0) {
      throw new IllegalArgumentException("Invalid music: tempo cannot be negative");
    }
    this.music = new TreeMap<>();
    this.currentBeatNumber = 0;
    this.highestPitchValue = 0;
    this.lowestPitchValue = 1000;
    // initializes the tempo to
    // 1 million microseconds per beat, 1 beat per second
    this.tempo = 1000000;
  }

  /**
   * represents a builder class for MusicModelImpl class
   */
  public static final class Builder implements CompositionBuilder<MusicModel<Note>> {

    private MusicModelImpl model;

    public Builder() {
      this.model = new MusicModelImpl();
    }

    @Override
    public MusicModel<Note> build() {
      return this.model;
    }

    @Override
    public CompositionBuilder<MusicModel<Note>> setTempo(int tempo) {
      this.model.setTempo(tempo);
      return this;
    }

    @Override
    public CompositionBuilder<MusicModel<Note>> addNote(int start, int end,
                                                        int instrument, int pitch, int volume) {
      Note newNote = new Note(start, end, pitch/12,
              Pitch.findPitch(pitch % 12), instrument, volume);
      this.model.add(newNote);
      return this;
    }

  }


  /**
   * sets the tempo of this music to the given tempo
   * @param tempo the given tempo
   */
  public void setTempo(int tempo) {
    if (tempo < 0) {
      throw new IllegalArgumentException("tempo can't be negative");
    }
    this.tempo = tempo;
  }

  @Override
  public int getTempo() {
    return this.tempo;
  }

  /**
   *
   * @return a clone of the music
   */
  public TreeMap<Integer, ArrayList<Note>> getMusic() {
    TreeMap<Integer, ArrayList<Note>> musicClone =
            (TreeMap<Integer, ArrayList<Note>>)this.music.clone();
    return musicClone;
  }

  @Override
  public void add(Note note) {
    // if this map already has an arrayList at the given key
    if (this.music.containsKey(note.getStartBeat())) {
      // if the given note already has the same note in the music, replace it
      if (this.music.get(note.getStartBeat()).contains(note)) {
        int indexOfNote = this.music.get(note.getStartBeat()).indexOf(note);
        this.music.get(note.getStartBeat()).set(indexOfNote, note);
      } else {
        // add the note to the arrayList
        this.music.get(note.getStartBeat()).add(note);
      }

      // update the pitch value for the music
      if (note.getPitchValue() > this.getHighestPitchValue()) {
        this.highestPitchValue = note.getPitchValue();
      }
      //  this.updatePitchValues();

      if (note.getPitchValue() < this.getLowestPitchValue()) {
        this.lowestPitchValue = note.getPitchValue();
      }
    }
    // if the arrayList does not exist in map, construct one and put it in the map
    else {
      ArrayList<Note> listOfNote = new ArrayList<>();
      listOfNote.add(note);
      this.music.put(note.getStartBeat(), listOfNote);

      // update the pitch value for the music
      if (note.getPitchValue() > this.getHighestPitchValue()) {
        this.highestPitchValue = note.getPitchValue();
      }
      if (note.getPitchValue() < this.getLowestPitchValue()) {
        this.lowestPitchValue = note.getPitchValue();
      }
    }


  }


  @Override
  public void removeNote(Note note) {

    if (this.music.containsValue(this.music.get(note.getStartBeat()))) {
      int noteKey = note.getStartBeat();
      ArrayList<Note> newArray = this.music.get(note.getStartBeat());
      newArray.remove(note);
      this.music.put(noteKey, newArray);

    } else {
      throw new IllegalArgumentException("Note does not exist!");
    }
  }

  @Override
  public int getHighestPitchValue() {
    return this.highestPitchValue;
  }

  @Override
  public int getLowestPitchValue() {
    return this.lowestPitchValue;
  }

  @Override
  public int getDuration() {
    // if the music is empty
    if (this.music.size() == 0) {
      return 0;
    }
    else {
      int highestDuration = 0;
      ArrayList<Note> allNotes = this.getAllNotes();
      // find the note that has the longest length in the last arrayList
      for (Note note : allNotes) {
        if (note.getEndBeat() > highestDuration) {
          highestDuration = note.getEndBeat();
        }
      }
      return highestDuration;
    }
  }

  @Override
  public int getCurrentBeatNumber() {
    return this.currentBeatNumber;
  }

  @Override
  public void updateCurrentBeatNumber(int beatNum) {
    if(beatNum < 0) {
      throw new IllegalArgumentException("beatNum can't be negative");
    }
    if (beatNum > this.getDuration()) {
      throw new IllegalArgumentException("beatNum can't be larger than " +
              "duration of music piece");
    }

    this.currentBeatNumber = beatNum;
  }

  @Override
  public void moveNote(Note note, int newKey, int newPitch) {
    int noteDuration = note.getEndBeat() - note.getStartBeat();
    // if the note exist in the map
    if (this.music.containsValue(this.music.get(note.getStartBeat()))
            && this.music.get(note.getStartBeat()).contains(note)) {
      // if there is an arrayList constructed in the newKey already
      if (this.music.containsValue(this.music.get(newKey))) {
        this.removeNote(note);

        ArrayList<Note> list = this.music.get(newKey);
        list.set(newPitch, note);
        //replace the arrayList by the new arrayList without the given oldNote
        this.music.put(newKey, list);
        // modify the note's startBeat, endBeat,, duration doesn't change
        note.editNoteLength(newKey, newKey + noteDuration);
      } else {
        // remove the old note
        this.removeNote(note);

        ArrayList<Note> listOfNote = new ArrayList<>();
        listOfNote.add(note);
        this.music.put(newKey, listOfNote);
        // modify the note's startBeat, endBeat
        note.editNoteLength(newKey, newKey + noteDuration);
      }
    } else {
      throw new IllegalArgumentException("Moving a non-exist note");
    }
  }

  @Override
  public void editNote(Note note, int startBeat, int endBeat) {
    note.editNoteLength(startBeat, endBeat);
  }

  @Override
  public void playSimultaneously(MusicModel<Note> musicModel) {

    // makes a copy of the music piece from the given interface
    ArrayList<Note> listOfNoteFromModel = musicModel.getAllNotes();

    for (Note n : listOfNoteFromModel) {
      this.add(n);
    }
  }

  @Override
  public void playConsecutively(MusicModel<Note> musicModel) {

    ArrayList<Note> listOfNoteFromModel = musicModel.getAllNotes();

    for (Note n : listOfNoteFromModel) {
      n.editNoteLength(n.getStartBeat() + this.getDuration(),
              n.getEndBeat() + this.getDuration());
      this.add(n);
    }

  }

  @Override
  public ArrayList<Note> getAllNotes() {
    ArrayList<Note> listOfNote = new ArrayList<>();
    for (int i : this.music.keySet()) {
      for (Note n : this.music.get(i)) {
        listOfNote.add(n);
      }
    }
    return listOfNote;
  }

  @Override
  public ArrayList<Note> getCurrentPlayingNotes(int startBeat) {
    // catches the exception thrown is the given startBeat does not exist in the music
    ArrayList<Note> al = this.music.get(startBeat);
    if (al == null) {
      return new ArrayList<>();
    }
    else {
      return al;
    }
  }

  @Override
  public void clearMusicPiece() {
    this.music = new TreeMap<>();
  }
}






