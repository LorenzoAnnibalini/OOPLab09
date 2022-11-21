package it.unibo.oop.reactivegui02;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 * Second example of reactive GUI.
 */
public final class ConcurrentGUI extends JFrame {

    private final JFrame frame; 
    private final JPanel p1;
    private final JButton buttonUp;
    private final JButton buttonDown;
    private final JButton buttonStop;
    private final JTextArea boxContatore;
    private final Agent agent;

    public ConcurrentGUI() {
        this.frame = new JFrame();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.agent = new Agent();
        this.p1 = new JPanel();
        this.buttonStop = new JButton("Stop");
        this.buttonUp = new JButton("Up");
        this.buttonDown = new JButton("Down");
        this.boxContatore = new JTextArea();
        this.boxContatore.setEditable(false);
        this.frame.setContentPane(p1);
        this.p1.add(boxContatore);
        this.p1.add(buttonUp);
        this.p1.add(buttonDown);
        this.p1.add(buttonStop);

        
        //Rendo visibile frame
        this.frame.pack();
        this.frame.setVisible(true);
    }

    private class Agent implements Runnable {
        @Override
        public void run() {
            // TODO Auto-generated method stub
        }
        
    }
}
