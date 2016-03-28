package cs3500.music.view;

import org.junit.Test;

import javax.sound.midi.MidiUnavailableException;

import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicModelImpl;
import cs3500.music.model.Note;
import static org.junit.Assert.assertTrue;

/**
 * Created by yifanxing on 3/22/16.
 */
public class TestViewFactory {

  MusicModel<Note> model = new MusicModelImpl.Builder().build();
  MusicView.ViewFactory viewFactory = new MusicView.ViewFactory(model);

  @Test
  public void TestCreate() throws MidiUnavailableException {
    assertTrue(viewFactory.create("console") instanceof ConsoleView);
    assertTrue(viewFactory.create("visual") instanceof GuiViewFrame);
    assertTrue(viewFactory.create("midi") instanceof MidiView);
  }


  @Test(expected = IllegalArgumentException.class)
  public void TestCreate2() throws MidiUnavailableException {
    viewFactory.create("222");
  }
}
