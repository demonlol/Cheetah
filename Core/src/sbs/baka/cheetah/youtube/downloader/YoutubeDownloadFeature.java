package sbs.baka.cheetah.youtube.downloader;

import com.github.axet.vget.*;
import com.github.axet.vget.info.*;
import sbs.baka.cheetah.feature.*;
import sbs.baka.cheetah.gui.*;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.concurrent.atomic.*;

public class YoutubeDownloadFeature extends WindowFeature {

    VGet v;

    @Override
    public InternalFrame.Builder getInternalFrame() {
        return new InternalFrame.Builder()
                .withSize(300, 100)
                .withRunnableAndInstance((f -> {
                    f.setLayout(new FlowLayout());

                    JProgressBar progressBar = new JProgressBar();
                    progressBar.setPreferredSize(new Dimension(280, 25));

                    JTextField field = new JTextField();
                    field.setPreferredSize(new Dimension(280, 20));
                    field.addActionListener(a -> {
                            new Thread(() -> {
                                try {
                                    v = new VGet(new URL(field.getText()), new File("C:\\Users\\Tyler\\Desktop"));

                                    new Thread(() -> {
                                        while(v.getVideo().getState() != VideoInfo.States.DONE) {
                                            System.out.println(v.getVideo().getState());
                                            try { Thread.sleep(1000); } catch (InterruptedException e) {}
                                        }
                                    }).start();

                                    v.extract();
                                    v.download(null, new AtomicBoolean(false), () -> {
                                        JOptionPane.showMessageDialog(f, "Download complete");
                                    });
                                } catch (Exception e) {
                                    JOptionPane.showMessageDialog(f, "Failed to downlaod");
                                }
                            }).start();

                    });
                    f.add(field);
                    f.add(progressBar);
                }))
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
