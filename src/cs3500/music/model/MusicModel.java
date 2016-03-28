package cs3500.music.model;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created by Yifan Xing
 * Represents the music editor
 */
public interface MusicModel<T> {

  /**
   * add the node at the given index
   * @param note the given node to be added
   */
  void add(T note);

  /**
   * removes the note at the given indexes
   * @param note the given node to be removed
   */
  void removeNote(T note);

  /**
   * move a note from a position to another, change its pitch
   * @param note represents the note to be moved
   * @param newKey represents the new key that the note is moving to
   * @param indexOfNote represents the new index that the note is moving to
   */
  void moveNote(T note, int newKey, int indexOfNote);

  /**
   * edit the given note
   */
  void editNote(T note, int startBeat, int endBeat);

  /**
   * allows two music piece plays simuteniously
   * @param musicModel another music model
   */
  void playSimultaneously(MusicModel<T> musicModel);

  /**
   * allows two music piece plays one after the other
   * @param musicModel another music model
   */
  void playConsecutively(MusicModel<T> musicModel);

  /**
   * @return all the notes in one arrayList
   */
  ArrayList<Note> getAllNotes();

  /**
   *
   * @return the notes that are playing at the current beat
   */
  ArrayList<Note> getCurrentPlayingNotes(int startBeat);


  /**
   * clear all the notes in the current music piece
   */
  void clearMusicPiece();

  /**
   *
   * @return a clone of the music
   */
  TreeMap<Integer, ArrayList<Note>> getMusic();

  /**
   *
   * @return the tempo of the music in microseconds per beat
   */
  int getTempo();

  /**
   * Sets the value of this.tempo to be tempo, as long as tempo is non-negative.
   *
   * @param tempo the non-negative value of tempo in microseconds per beat
   * @throws IllegalArgumentException if tempo is negative
   */
  void setTempo(int tempo);

  /**
   *
   * @return the integer value of the lowest pitch
   */
  int getLowestPitchValue();

  /**
   *
   * @return the integer value of the highest pitch
   */
  int getHighestPitchValue();

  /**
   * find the end beat of the last note
   *
   * @return the duration of the music
   */
  int getDuration();

  /**
   * fetch this.currentBeatNumber
   *
   * @return the integer value of the current beat number
   */
  int getCurrentBeatNumber();

  /**
   * Sets currentBeatNumber to the given beatNum.
   *
   * @param beatNum the new beat number
   * @throws IllegalArgumentException if beatNum is negative or greater than
   *        this.getDuration()
   */
  void updateCurrentBeatNumber(int beatNum);
}
