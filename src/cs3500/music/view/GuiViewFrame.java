package cs3500.music.view;

import java.awt.*;

import javax.swing.*;

/**
 * A skeleton Frame (i.e., a window) in Swing
 */
public class GuiViewFrame extends javax.swing.JFrame implements MusicView {

  private final JPanel displayPanel; // You may want to refine this to a subtype of JPanel

  /**
   * Creates new GuiView
   */
  public GuiViewFrame(JPanel panel) {
    this.displayPanel = panel;
    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
   // this.setLayout(new FlowLayout());
    this.displayView();
  }

  @Override
  public void initialize(){
    this.setVisible(true);
  }

  /**
   * Displays the view to the user as a graphical window.
   */
  @Override
  public void displayView() {

    JFrame musicDisplay = new JFrame("Music Editor");
    musicDisplay.getPreferredSize();
    this.getContentPane().add(this.displayPanel);
    this.pack();

    // Frame and Labels
    this.initialize();
  }

  @Override
  public Dimension getPreferredSize(){
    return new Dimension(100, 100);
  }

  @Override
  public Graphics getGraphics() {
    return super.getGraphics();
  }
}
