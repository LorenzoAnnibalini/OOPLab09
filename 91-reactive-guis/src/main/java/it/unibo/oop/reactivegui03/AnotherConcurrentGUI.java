package it.unibo.oop.reactivegui03;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 * Third experiment with reactive gui.
 */
public final class AnotherConcurrentGUI extends JFrame {
    private final JFrame frame; 
    private final JPanel p1;
    private final JButton buttonUp;
    private final JButton buttonDown;
    private final JButton buttonStop;
    private final JTextArea boxContatore;
    private Agent agent1;
    private StopTimer agentStop;
    private Boolean stop = false;
    private Boolean modInc = true;
    private int contatore = 0;

    public AnotherConcurrentGUI() {
        this.frame = new JFrame();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.agent1 = new Agent();
        this.agentStop = new StopTimer();
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
                AnotherConcurrentGUI.this.stop = true;
            } });

        this.buttonDown.addActionListener(new ActionListener(){
             @Override
            public void actionPerformed(final ActionEvent e) {
                AnotherConcurrentGUI.this.modInc = false;
            } });

        this.buttonUp.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(final ActionEvent e) {
                AnotherConcurrentGUI.this.modInc = true;
            } });


        //Rendo visibile frame
        final Dimension schermo = Toolkit.getDefaultToolkit().getScreenSize();
        final int width = (int) schermo.getWidth();
        final int height = (int) schermo.getHeight();
        final  int proporzione = 5;
        this.frame.setSize(width / proporzione, height / proporzione);
        this.frame.setVisible(true);

        //ATTIVO Thread
        new Thread(agent1).start();
        new Thread(agentStop).start();
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
                    SwingUtilities.invokeAndWait(() -> AnotherConcurrentGUI.this.boxContatore.setText(Integer.toString(contatore)));
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class StopTimer implements Runnable {
        public void run() {
            try {
                Thread.sleep(10000);
                buttonStop.doClick();;
            } catch (Exception e) {

            }
        }
    }
}
