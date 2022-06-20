package sbs.baka.cheetah.video.webcam;

import com.github.sarxos.webcam.*;
import org.apache.commons.lang3.mutable.*;
import sbs.baka.cheetah.feature.*;
import sbs.baka.cheetah.gui.*;
import sbs.baka.cheetah.gui.menubar.*;
import sbs.baka.cheetah.gui.menubar.Menu;
import sbs.baka.cheetah.gui.menubar.MenuBar;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.time.*;
import java.time.format.*;

public class CameraFeature extends WindowFeature {

    private Webcam webcam;
    private WebcamPanel panel;
    private SwingWorker webcamWorker;

    @Override
    public InternalFrame.Builder getInternalFrame() {
        webcam = Webcam.getDefault();

        panel = new WebcamPanel(webcam, false);
        panel.setAntialiasingEnabled(true);
        panel.setFPSLimit(60);
        panel.setDrawMode(WebcamPanel.DrawMode.FILL);
        panel.setPainter(new CustomPainter());

        return new InternalFrame.Builder()
                .is(true, true, true, true)
                .withBackground(Color.PINK)
                .withTitle(webcam.getName())
                .withInternalFrameListener(new InternalFrameAdapter() {
                    @Override
                    public void internalFrameClosing(InternalFrameEvent e) {
                        webcam.close();
                    }
                    @Override
                    public void internalFrameOpened(InternalFrameEvent e) {
                        start();
                        webcam.open();
                    }
                })
                .add((frame) -> {
                    MutableObject<FlipDirection> flipDirection = new MutableObject<>(FlipDirection.NONE);
                    MutableObject<Boolean> fpsCounter = new MutableObject<>(Boolean.TRUE);

                    RadioButtonGroup group = new RadioButtonGroup(), group2 = new RadioButtonGroup();

                    JRadioButtonMenuItem[] sizes = new JRadioButtonMenuItem[Webcam.getDefault().getViewSizes().length];
                    for (int i = 0; i < Webcam.getDefault().getViewSizes().length; i++) {
                        Dimension viewSize = Webcam.getDefault().getViewSizes()[i];
                        JRadioButtonMenuItem item = new RadioButtonMenuItem.Builder()
                                .withText(viewSize.width + "x" + viewSize.height)
                                .withActionListener((a) -> {
                                    webcam.close();
                                    webcam.setViewSize(viewSize);
                                    webcam.open();
                                })
                                .isEnabled(false)
                                .build();
                        sizes[i] = item;
                        group2.add(item);
                    }

                    //Menubar
                    frame.setJMenuBar(new MenuBar.Builder()
                            .withMenu(new Menu.Builder()
                                    .withText("Options")
                                    .with(new CheckBoxMenuItem.Builder()
                                            .withText("Always Record").withActionListener((a) -> {
                                                System.out.println(this.getName() + " : RecordConstantly > " + ((CheckBoxMenuItem) a.getSource()).isSelected());
                                            })
                                            .build())
                                    .with(new CheckBoxMenuItem.Builder()
                                            .withText("FPS").withActionListener((a) -> {
                                                fpsCounter.setValue(((CheckBoxMenuItem) a.getSource()).isSelected());
                                                System.out.println(this.getName() + " : FPS Counter > " + ((CheckBoxMenuItem) a.getSource()).isSelected());
                                            })
                                            .build())
                                    .withSeparator()
                                    .with(new RadioButtonMenuItem.Builder(group)
                                            .withText("Mirrored").withActionListener((a) -> {
                                                flipDirection.setValue((((RadioButtonMenuItem) a.getSource()).isSelected()) ? FlipDirection.HORIZONTAL : FlipDirection.NONE);
                                            }).build())
                                    .withSeparator()
                                    .with(sizes)
                                    .build())
                            .build());

                    frame.addComponentListener(new ComponentAdapter() {
                        @Override
                        public void componentResized(ComponentEvent e) {
                            InternalFrame frm = ((InternalFrame) e.getSource());
                            int W = 16, H = 9;
                            Rectangle b = frm.getBounds();
                            frame.setBounds(b.x, b.y, b.width, b.width * H / W);
                        }
                    });

                    //Webcam
                    webcamWorker = new SwingWorker() {
                        @Override
                        protected Object doInBackground() {
                            frame.setSize(new Dimension(640, 360));

                            fpsCounter.setValue(panel.isFPSDisplayed());
                            flipDirection.setValue(panel.isMirrored() ? FlipDirection.HORIZONTAL : FlipDirection.NONE);
                            return null;
                        }
                    };
                    return panel;
                });
    }
    @Override public String getName() { return "Edgeunius"; }
    @Override public String getDesc() { return "A genius invention."; }

    @Override
    public void startAndShow() {
        super.startAndShow();
        panel.start();
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

    enum FlipDirection { NONE, HORIZONTAL }
    class CustomPainter implements WebcamPanel.Painter {
        private WebcamPanel.Painter defaultPainter;

        CustomPainter() {
            defaultPainter = panel.getDefaultPainter();
        }

        @Override
        public void paintPanel(WebcamPanel webcamPanel, Graphics2D graphics2D) {
            defaultPainter.paintPanel(webcamPanel, graphics2D);
        }

        @Override
        public void paintImage(WebcamPanel webcamPanel, BufferedImage bufferedImage, Graphics2D graphics2D) {
            graphics2D.setColor(Color.BLUE);

            defaultPainter.paintImage(webcamPanel, bufferedImage, graphics2D);

            graphics2D.drawString(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME), 5, graphics2D.getFontMetrics().getHeight() + 5);

            graphics2D.dispose();
        }
    }

}