package it.unibo.oop.reactivegui02;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private Agent agent;
    private Boolean stop = false;
    private Boolean modInc = true;
    private int contatore = 0;

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

        this.buttonStop.addActionListener(new ActionListener(){
             @Override
            public void actionPerformed(final ActionEvent e) {
                ConcurrentGUI.this.stop = true;
            } });

        this.buttonDown.addActionListener(new ActionListener(){
             @Override
            public void actionPerformed(final ActionEvent e) {
                ConcurrentGUI.this.modInc = false;
            } });

        this.buttonUp.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(final ActionEvent e) {
                ConcurrentGUI.this.modInc = true;
            } });


        //Rendo visibile frame
        this.frame.pack();
        this.frame.setVisible(true);

        //ATTIVO Thread
        this.agent = new Agent();
        this.agent.run();
    }

    private class Agent implements Runnable {
        @Override
        public void run() {
            while(!stop) {
                try {   
                        if(modInc) {
                            if((contatore+1) > Integer.MAX_VALUE ){
                            contatore = Integer.MIN_VALUE;
                            } else {
                            contatore++;
                            }
                        } else {
                            if((contatore-1) < Integer.MIN_VALUE ){
                                contatore = Integer.MAX_VALUE;
                                } else {
                                contatore--;
                                }
                        }
                    SwingUtilities.invokeAndWait(() -> ConcurrentGUI.this.boxContatore.setText(Integer.toString(contatore)));
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
    }
}
