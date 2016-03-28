package cs3500.music.view;

import javax.sound.midi.MidiUnavailableException;

import cs3500.music.model.MusicModel;
import cs3500.music.model.Note;

/**
 * Created by yifanxing on 3/16/16.
 */
public interface MusicView {

  /**
   * a factory class that renders the view for the user by taking a string as an input
   */
  public class ViewFactory {

    MusicModel<Note> model;

    public ViewFactory(MusicModel<Note> model) {
      this.model = model;
    }

    /**
     * renders the corresponding view according to the input of the user
     * @param viewType represents the type of the view class the user wants to create
     * @return the corresponding view class
     * @throws MidiUnavailableException
     */
    public MusicView create(String viewType) throws MidiUnavailableException {

      if (viewType == "console") {
        return new ConsoleView(this.model);
      }
      else if (viewType == "visual") {
        return new GuiViewFrame(new ConcreteGuiViewPanel(this.model));
      }
      else if (viewType == "midi") {
        return new MidiViewImpl(this.model);
      }
      else {
        throw new IllegalArgumentException(
                "Try Again, the input has to be one of console, visual, midi");
      }
    }
  }

  /**
   * Allows the user to begin seeing the view.
   */
  void initialize();

  /**
   * Displays the view to the user.
   * In GuiViewFrame,
   */
  void displayView();
}
