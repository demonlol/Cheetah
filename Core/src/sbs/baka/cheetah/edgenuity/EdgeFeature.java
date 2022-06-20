package sbs.baka.cheetah.edgenuity;

import org.slf4j.*;
import sbs.baka.cheetah.edgenuity.api.*;
import sbs.baka.cheetah.edgenuity.api.err.*;
import sbs.baka.cheetah.feature.*;
import sbs.baka.cheetah.gui.*;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class EdgeFeature extends WindowFeature {

    private Logger log = LoggerFactory.getLogger(EdgeFeature.class);

    private File edgeApiDir = new File(System.getProperty("user.home") + "\\Desktop\\Edge\\");
    private File profilesDir = new File(edgeApiDir.getAbsolutePath() + "\\Users");

    private EdgeAPI edgeAPI;

    public EdgeFeature() {
        super();

        createDirectory(edgeApiDir);
        createDirectory(profilesDir);
    }

    private boolean createDirectory(File dir) {
        if(dir.isDirectory()) {
            if(!dir.exists() && dir.mkdirs()) {
                log.debug("Created directory: " + dir.getAbsolutePath());
                return true;
            }
        }
        return false;
    }

    @Override
    public InternalFrame.Builder getInternalFrame() {
        Rectangle windowBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        return new InternalFrame.Builder()
                .withTitle(getName())
                .is(true, true, true, true)
                .withLocation(988, 500)
                .withInternalFrameListener(new InternalFrameAdapter() {
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
                })
                .withSize(windowBounds.width / 4, windowBounds.height / 3)
                .withRunnableAndInstance((frame) -> {
                    JPanel controlPanel = new JPanel();
                    controlPanel.setBorder(BorderFactory.createTitledBorder("Edgenuity Control Panel"));

                    //----------------------- LOGIN PANEL -----------------------
                    JPanel loginPanel = new JPanel();
//                    loginPanel.setPreferredSize(new Dimension(165, 100));
                    loginPanel.setBorder(BorderFactory.createTitledBorder("Login"));

                    DefaultComboBoxModel<String> listModel = new DefaultComboBoxModel<>();
                    JComboBox<String> profileBox = new JComboBox<>();
                    profileBox.setModel(listModel);


                    JTextField username = new JTextField("ashfolke");
                    JTextField password = new JTextField("usernameusername");

                    username.setPreferredSize(new Dimension(150, 20));
                    password.setPreferredSize(new Dimension(150, 20));

                    JButton login = new JButton("Login");
                    login.setPreferredSize(new Dimension(75, 25));
                    login.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            log.debug("Logging into Edgenuity with credentials{user=" + username.getText() + ",pass=" + password.getText() + "}");

                            try {
                                edgeAPI = new EdgeAPI(username.getText(), password.getText());

                                log.debug("Successfully logged into Edgenuity through custom API.");
                            } catch (EdgeAuthenticationException ex) {
                                JOptionPane.showInternalMessageDialog(frame, "Failed to authenticate credentials");
                            }
                        }
                    });
                    loginPanel.add(profileBox);
                    loginPanel.add(username);
                    loginPanel.add(password);
                    loginPanel.add(login);

                    controlPanel.add(loginPanel);

                    frame.setContentPane(controlPanel);
                });
    }

    @Override
    public String getName() {
        return "Edgenuity";
    }

    @Override
    public String getDesc() {
        return "...";
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
