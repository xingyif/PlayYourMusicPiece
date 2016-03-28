package cs3500.music.view;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

import javax.sound.midi.*;

import cs3500.music.model.MusicModel;
import cs3500.music.model.Note;


/**
 * A skeleton for MIDI playback
 */

public class MidiViewImpl implements MidiView {
  MusicModel<Note> model;
  private final Synthesizer synth;
  private final Receiver receiver;

  public MidiViewImpl(MusicModel<Note> model) throws MidiUnavailableException {
    this.model = model;
    this.synth = MidiSystem.getSynthesizer();
    this.receiver = synth.getReceiver();
    this.synth.open();
  }


	/**
	 * a convenience constructor for the mock midi device
	 * @param model
	 * @throws MidiUnavailableException
   */
	public MidiViewImpl(MusicModel<Note> model, Synthesizer synth) throws MidiUnavailableException {
		this.model = model;
		this.synth = synth;
		this.receiver = synth.getReceiver();
		this.synth.open();
	}



	/**
   * Relevant classes and methods from the javax.sound.midi library:
   * <ul>
   *  <li>{@link MidiSystem#getSynthesizer()}</li>
   *  <li>{@link Synthesizer}
   *    <ul>
   *      <li>{@link Synthesizer#open()}</li>
   *      <li>{@link Synthesizer#getReceiver()}</li>
   *      <li>{@link Synthesizer#getChannels()}</li>
   *    </ul>
   *  </li>
   *  <li>{@link Receiver}
   *    <ul>
   *      <li>{@link Receiver#send(MidiMessage, long)}</li>
   *      <li>{@link Receiver#close()}</li>
   *    </ul>
   *  </li>
   *  <li>{@link MidiMessage}</li>
   *  <li>{@link ShortMessage}</li>
   *  <li>{@link MidiChannel}
   *    <ul>
   *      <li>{@link MidiChannel#getProgram()}</li>
   *      <li>{@link MidiChannel#programChange(int)}</li>
   *    </ul>
   *  </li>
   * </ul>
   * @see <a href="https://en.wikipedia.org/wiki/General_MIDI">
   *   https://en.wikipedia.org/wiki/General_MIDI
   *   </a>
**/

  public void playNote(Note note) throws InvalidMidiDataException {
    MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON,
            note.getInstrument(),
            note.getPitchValue(), note.getVolume());
    MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, note
            .getInstrument(), note
            .getPitchValue(), note.getVolume());
    this.receiver.send(start, this.model.getTempo()*note.getStartBeat());
    this.receiver.send(stop, this.model.getTempo()*note.getEndBeat());
  }

  @Override
  public void initialize() {
    // do nothing
  }

  /**
	* Makes all notes in the model audible, according to each note's fields.
	*/
	@Override
	public void displayView() {
	  TreeMap<Integer, ArrayList<Note>> treeMap = this.model.getMusic();

	  Set<Integer> keySet = treeMap.keySet();
	  ArrayList<Integer> keys = new ArrayList<>();
	  for (Integer i : keySet) {
		keys.add(i);
	  }

	  int keysIndex = 0;
	  int currentKey = keys.get(keysIndex);
	  ArrayList<Note> listOfNote = treeMap.get(currentKey);

	  for (int i = 0; keysIndex < keys.size(); i++) {

		// at the last list
		if (keysIndex >= keys.size() - 1) {
		  // at the last note of the last list
		  if (i >= listOfNote.size() - 1) {
			// plays the note
			try {
			  this.playNote(listOfNote.get(i));
			} catch (InvalidMidiDataException e) {
			  System.out.println("Invalid Midi data; could not play given note");
			} catch (IndexOutOfBoundsException e) {
      }
			keysIndex = keys.size();
		  }

		  else {
			// plays the note
			try {
			  this.playNote(listOfNote.get(i));
			} catch (InvalidMidiDataException e) {
			  System.out.println("Invalid Midi data; could not play given note");
			}
			
		  }
		}

		// before the last list
		else {
		  // when the last note has been played
		  if (i > listOfNote.size() - 1) {
			  // increment keyIndex to get the next arrayList
			  keysIndex++;
			  // update the index of this list
			  i = 0;
			  // update the list to the next arrayList<Note>
			  listOfNote = treeMap.get(keys.get(keysIndex));
			}
		  
		  // plays the note
		  try {
			this.playNote(listOfNote.get(i));
		  } catch (InvalidMidiDataException e) {
			System.out.println("Invalid Midi data; could not play given note");
		  }
		  
		}
	  }
    try {
      Thread.sleep(this.model.getDuration()*this.model.getTempo()/1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    this.receiver.close();
	}
}
