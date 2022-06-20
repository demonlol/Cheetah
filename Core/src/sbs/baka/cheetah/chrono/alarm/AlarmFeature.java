package sbs.baka.cheetah.chrono.alarm;

import sbs.baka.cheetah.feature.*;
import sbs.baka.cheetah.gui.*;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.time.*;
import java.util.Timer;
import java.util.*;


public class AlarmFeature extends WindowFeature {

    private JPanel panel = null;
    private JComboBox<Integer> hourSet = null;
    private JComboBox<Integer> minSet = null;
    private Timer newTimer;
    private Clip alarmSound;

    @Override
    public InternalFrame.Builder getInternalFrame() {
        return new InternalFrame.Builder()
                .withTitle("Alarm Clock")
                .withSize(300, 400)
                .withInternalFrameListener(new InternalFrameAdapter() {
                    @Override
                    public void internalFrameClosing(InternalFrameEvent e) {
                        if (alarmSound != null)
                            alarmSound.stop();
                    }
                })
                .withRunnableAndInstance((frame) -> {
                    // Handles the time computation
                    Clock clock = new Clock() {
                        @Override
                        public ZoneId getZone() {
                            return null;
                        }

                        @Override
                        public Clock withZone(ZoneId zone) {
                            return null;
                        }

                        @Override
                        public Instant instant() {
                            return null;
                        }
                    };

                    // Hour label
                    JLabel hour = new JLabel("Hour");
                    // Minute Label
                    JLabel min = new JLabel("Minute");

                    // User can select what hour to wake up at
                    minSet = new JComboBox<>();
                    for (int i : new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59})
                        minSet.addItem(i);
                    minSet.setToolTipText("Choose Min");

                    // User can select what min to wake up at
                    hourSet = new JComboBox<>();
                    for (int i : new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23})
                        hourSet.addItem(i);
                    hourSet.setToolTipText("Choose Hour");

                    // Adds comboBoxes to the HBox
                    frame.add(hourSet);
                    frame.add(minSet);

                    JButton stopAlarm = new JButton("Stop Alarm");
                    JButton resetAlarm = new JButton("Reset Alarm");
                    resetAlarm.setVisible(false);

                    // Adds a set Alarm button
                    JButton setAlarm = new JButton("Set Alarm");
                    setAlarm.addActionListener(e -> {
                        // Make sure the user set a time to wake up at
                        if (hourSet.getSelectedItem() != null && minSet.getSelectedItem() != null) {
                            setAlarm.setEnabled(false);
                            resetAlarm.setVisible(true);
                            minSet.setEnabled(false);
                            hourSet.setEnabled(false);

                            // Alarm is accurate within 1 seconds
                            // dictates when the alarm will go of
                            newTimer = new Timer("", false);
                            newTimer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    while (true /*repeats(true) equivalent*/) {
                                        if ((alarmTimeInMin() == clockTimeInMin())) {
                                            resetAlarm.setVisible(false);
                                            newTimer.cancel();
                                            alarmSound = null;
                                        }
                                        stopAlarm.setVisible(true);
                                    }
                                }
                            }, 1000);
                        }
                    });

                    // Allows you to set the alarm to a different time
                    resetAlarm.addActionListener(e -> {
                        setAlarm.setEnabled(true);
                        resetAlarm.setVisible(false);
                        hourSet.setEnabled(true);
                        minSet.setEnabled(true);
                        // No need to use 'if (newTimer.isRunning())' because resetAlarm button
                        // is visible only if setAlarm has been pressed, which sets timer running
                        newTimer.cancel();
                    });
                    // Stops the alarm once it is going off
                    stopAlarm.addActionListener(e -> {
                        // Once the alarm is set it cannot be changed
                        // except by the reset alarm button
                        hourSet.setEnabled(true);
                        minSet.setEnabled(true);
                        setAlarm.setEnabled(true);
                        stopAlarm.setVisible(false);
                        resetAlarm.setVisible(false);
//                        try {
//                            alarmSound.stopAudio();
//                        } catch (IOException e1) {
//                            e1.printStackTrace();
//                        }
                    });
                    stopAlarm.setVisible(false);

                    // panel added to the swingNode
                    panel = new JPanel();
                    panel.setLayout(new BorderLayout());

                    // clock.getTime returns a JComponent(JLabel)
                    panel.add(new JLabel(String.valueOf(clock.millis())));
                    panel.setVisible(true);

                    frame.add(panel);
                })
                ;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDesc() {
        return null;
    }

    // alarmTimeinMin() and clockTimeinMin() are used to convert the alarm time set by user
    // to the minutes passed since 12AM; the same happens in the clockTimeInMin method.
    // When they are equal, the alarm goes off.
    private int alarmTimeInMin() {
        int hours = Integer.parseInt(String.valueOf(hourSet.getSelectedItem()));
        return Integer.parseInt(String.valueOf(minSet.getSelectedItem())) + hours * 60;
    }

    private int clockTimeInMin() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        // if it's after noon
        if (cal.get(Calendar.AM_PM) == Calendar.PM) {
            int clockHour = cal.get(Calendar.HOUR);
            int clockTotalMin = (clockHour + 12) * 60 + cal.get(Calendar.MINUTE);
            // 1440 minutes indicates that it is 12AM. Thus, the current time is
            // made up of only minutes
            if (clockTotalMin >= 1440)
                clockTotalMin = cal.get(Calendar.MINUTE);
            return clockTotalMin;
        } else {
            // if it's before noon
            int clockHour = cal.get(Calendar.HOUR);
            return clockHour * 60 + cal.get(Calendar.MINUTE);
        }
    }

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
}
