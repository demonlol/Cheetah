package sbs.baka.cheetah.social.media.snapchat.android.gui;

import sbs.baka.cheetah.feature.*;
import sbs.baka.cheetah.gui.*;
import sbs.baka.cheetah.social.media.snapchat.android.emulator.*;

import javax.imageio.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class AndroidAutomaterFeature extends WindowFeature {

    Image iconStart;
    Image iconStop;

    JLabel lab;

    @Override
    public InternalFrame.Builder getInternalFrame() {
        return new InternalFrame.Builder()
                .withTitle("Asyncagator Android Automate-r")
                .is(false, false, false, false)
                .withSize(800, 600)
                .withInternalFrameListener(this)
                .withRunnableAndInstance((f) -> {
                    BufferedImage tempStart = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("ui_design/android/bs_start.png"));

                    Dimension imgDim = new Dimension(tempStart.getWidth() / 3, tempStart.getHeight() / 3);

                    iconStart = tempStart.getScaledInstance((int) imgDim.getWidth(), (int) imgDim.getHeight(), tempStart.getType());
                    iconStop = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("ui_design/android/bs_stop.png")).getScaledInstance((int) imgDim.getWidth(), (int) imgDim.getHeight(), tempStart.getType());

                    lab = new JLabel(new ImageIcon(iconStart));
                    lab.setPreferredSize(imgDim);
                    lab.setCursor(new Cursor(Cursor.HAND_CURSOR));

                    lab.addComponentListener(new ComponentAdapter() {
                        @Override
                        public void componentShown(ComponentEvent e) {
                            SwingUtilities.invokeLater(() -> leftClicked(null));
                        }
                    });

                    lab.addMouseListener(new MouseAdapter() {
                        private boolean pressed;

                        @Override
                        public void mousePressed(MouseEvent e) {
                            if(SwingUtilities.isLeftMouseButton(e))
                                pressed = true;
                        }
                        @Override
                        public void mouseReleased(MouseEvent e) {
                            if(SwingUtilities.isLeftMouseButton(e) && pressed) {
                                leftClicked(e);
                                pressed = false;
                            }
                        }

                    });

                    f.setLayout(new FlowLayout());

                    f.add(lab);
                })
                ;
    }

    public void leftClicked(MouseEvent e) {
        EmulatorOverlay eo = EmulatorOverlay.getInstance();
        try {
            if(eo.isActive()) {
                eo.stop();
                lab.setIcon(new ImageIcon(iconStart));
                //start on icon
            }
            else {
                eo.start();
                lab.setIcon(new ImageIcon(iconStop));
                //stop on icon
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private EmulatorOverlay getOverlay() {
        return EmulatorOverlay.getInstance();
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDesc() {
        return null;
    }

    @Override
    public void internalFrameOpened(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {
        try {
            getOverlay().stop();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
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
