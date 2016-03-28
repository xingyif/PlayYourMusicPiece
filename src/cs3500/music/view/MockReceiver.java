package cs3500.music.view;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

/**
 * Created by yifanxing on 3/22/16.
 */
public class MockReceiver implements Receiver {

  StringBuilder output;

  MockReceiver(StringBuilder output) {
    this.output = output;
  }

  @Override
  public void send(MidiMessage message, long timeStamp) {
    ShortMessage shortMessage = (ShortMessage) message;
    int instrument = shortMessage.getChannel();
    int pitchValue = shortMessage.getData1();
    int volume = shortMessage.getData2();

    if (shortMessage.getCommand() == ShortMessage.NOTE_ON) {
      this.output.append("note " + "ON" + " " + instrument + " " +
              pitchValue + " " + volume + " " + timeStamp + "\n");
    }
    else if (shortMessage.getCommand() == ShortMessage.NOTE_OFF){
      this.output.append("note " + "OFF" + " " + instrument + " " +
              pitchValue + " " + volume + " " + timeStamp + "\n");
    }
  }

  @Override
  public void close() {
    // do nothing
  }
}
