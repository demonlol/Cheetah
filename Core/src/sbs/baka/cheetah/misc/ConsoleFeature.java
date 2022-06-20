package sbs.baka.cheetah.misc;

import sbs.baka.cheetah.feature.*;
import sbs.baka.cheetah.gui.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class ConsoleFeature extends WindowFeature {

    @Override
    public void internalFrameOpened(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameClosed(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameIconified(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameDeiconified(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameActivated(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameDeactivated(InternalFrameEvent e) {

    }

    public class CustomOutputStream extends OutputStream {
        private JTextArea textArea;

        public CustomOutputStream(JTextArea textArea) {
            this.textArea = textArea;
        }

        @Override
        public void write(int b) throws IOException {
            // redirects data to the text area
            textArea.append(String.valueOf((char)b));
            // scrolls the text area to the end of data
            textArea.setCaretPosition(textArea.getDocument().getLength());
        }
    }

     /*
         * The text area which is used for displaying logging information.
     */
    private JTextArea textArea;

    private JButton buttonStart = new JButton("Start");
    private JButton buttonClear = new JButton("Clear");

    private PrintStream standardOut;

    public ConsoleFeature() {
        textArea = new JTextArea(50, 10);
        textArea.setEditable(false);
        PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));

        // keeps reference of standard output stream
        standardOut = System.out;

        // re-assigns standard output stream and error output stream
        System.setOut(printStream);
        System.setErr(printStream);

        // creates the GUI
//        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.anchor = GridBagConstraints.WEST;

//        add(buttonStart, constraints);

        constraints.gridx = 1;
//        add(buttonClear, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;

//        add(new JScrollPane(textArea), constraints);

        // adds event handler for button Start
        buttonStart.addActionListener(evt -> printLog());

        // adds event handler for button Clear
        buttonClear.addActionListener(evt -> {
            // clears the text area
            try {
                textArea.getDocument().remove(0,
                        textArea.getDocument().getLength());
                standardOut.println("Text area cleared");
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            }
        });
    }

    /**
     * Prints log statements for testing in a thread
     */
    private void printLog() {
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("Time now is " + (new Date()));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        thread.start();
    }

    @Override
    public InternalFrame.Builder getInternalFrame() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDesc() {
        return null;
    }
}
