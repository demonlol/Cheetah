package sbs.baka.cheetah.editor.overlay;

import com.formdev.flatlaf.*;
import com.formdev.flatlaf.util.*;
import com.github.kwhat.jnativehook.*;
import com.github.kwhat.jnativehook.keyboard.*;
import com.github.kwhat.jnativehook.mouse.*;
import com.sun.jna.*;
import com.sun.jna.platform.win32.*;
import org.bridj.jawt.*;
import sbs.baka.cheetah.gui.menubar.Menu;
import sbs.baka.cheetah.gui.menubar.MenuBar;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;
import javax.tools.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;

public class TransparentMDI extends JDialog implements WindowListener {

    public List<Color> colorList = new ArrayList<>();
    public List<RainbowColorChange> rainbowListeners = new ArrayList<>();
    @FunctionalInterface
    interface RainbowColorChange {
        void change(Color c);
    }
    private Thread rainbowTimer = new Thread(() -> {
         while(true) {
            for (Color c : colorList) {
                for (RainbowColorChange rainbowListener : rainbowListeners) {
                    rainbowListener.change(c);
                    try {
                        Thread.sleep(15);
                    } catch (InterruptedException ex) {
                    }
                }
                try {
                    Thread.sleep(15);
                } catch (InterruptedException ex) {
                }
            }
             try {
                 Thread.sleep(10);
             } catch (InterruptedException ex) {
             }
        }
    });

    JDesktopPane desktop = new JDesktopPane();

    public static void main(String[] args) {
//        GlobalScreen.addNativeKeyListener(new NativeKeyListener() {
//            @Override
//            public void nativeKeyPressed(NativeKeyEvent nativeEvent) {
//                if(nativeEvent.getKeyCode() == NativeKeyEvent.VC_BACKQUOTE) {
//                    canMove = true;
//                    timesPressed++;
//                }
//            }
//
//            @Override
//            public void nativeKeyReleased(NativeKeyEvent nativeEvent) {
//                if(timesPressed > 1) {
//                    timesPressed = 0;
//                    canMove = false;
//                }
//            }
//        });

        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            TransparentMDI dialog = new TransparentMDI();
            dialog.setVisible(true);
        });
    }

    InternalFrame jif;
    static int mlx;    //Represents the x coordinate of the mouse in the component (component coordinate system)
    static int mly;    //Represents the y coordinate of the mouse in the component (component coordinate system)

    public TransparentMDI() {
        float frequency1 = .3f, frequency2 = .3f, frequency3 = .3f, phase1 = 0, phase2 = 2, phase3 = 4, center = 128, width = 127, len = 50;
        for (var i = 0; i < len; ++i)
            colorList.add(new Color(
                        (float) (Math.sin(frequency1*i + phase1) * width + center) / 255,
                        (float) (Math.sin(frequency2*i + phase2) * width + center) / 255,
                        (float) (Math.sin(frequency3*i + phase3) * width + center) / 255
                )
        );
        System.out.println(colorList.size());


//        FlatDarkLaf.setup();

//        setJMenuBar(new MenuBar.Builder()
//                .withMenu(new Menu.Builder()
//                        .withText("Aaahh")
//                        .build())
//                .withMenu(new Menu.Builder()
//                        .withText("Aaahh")
//                        .build())
//                .withMenu(new Menu.Builder()
//                        .withText("Aaahh")
//                        .build())
//                .withMenu(new Menu.Builder()
//                        .withText("Aaahh")
//                        .build())
//                .withMenu(new Menu.Builder()
//                        .withText("Aaahh")
//                        .build())
//                .build());

        setModal(true);
        setAlwaysOnTop(true);
        setAutoRequestFocus(true);
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        Container cp = getContentPane();
//        cp.setBackground(new Color(0, 0, 0, 0));
        JPanel pnlMain = new JPanel(new BorderLayout());
        pnlMain.setBackground(new Color(0, 0, 0, 0));
        JPanel pnlLeft = new JPanel(new BorderLayout());
        pnlLeft.setOpaque(false);
        pnlMain.add(pnlLeft, BorderLayout.WEST);
// Make the desktop semi-transparent
        Color db = new Color(0,0,0, 0);
        desktop.setBackground(db);
//        desktop.setOpaque(false);
        pnlMain.add(desktop, BorderLayout.CENTER);
        jif = new InternalFrame("Internal Frame", true, true);

//        jif.setBackground(new Color(0, 0, 0, .1f));
        ((BasicInternalFrameUI) jif.getUI()).setNorthPane(null);
        jif.setOpaque(true);
//        jif.setOpaque(false);
//        jif.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 1), 1));

        for (MouseListener mouseListener : jif.getMouseListeners()) {
            jif.removeMouseListener(mouseListener);
        }

        jif.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) { }
            @Override
            public void mousePressed(MouseEvent e) {
                mlx = e.getPoint().x;
                mly = e.getPoint().y;
            }
            @Override
            public void mouseReleased(MouseEvent e) {

            }
            @Override
            public void mouseEntered(MouseEvent e) { }
            @Override
            public void mouseExited(MouseEvent e) { }
        });
        jif.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(SwingUtilities.isMiddleMouseButton(e)) {
                    setBounds(e.getXOnScreen() - jif.getX() - mlx, e.getYOnScreen() - jif.getY() - mly, jif.getWidth(), jif.getHeight());
                    repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });

        //menu bar
        GlobalScreen.addNativeMouseMotionListener(new NativeMouseMotionListener() {
            @Override
            public void nativeMouseMoved(NativeMouseEvent nativeEvent) {
                if(getJMenuBar() == null) return;

                if(new Rectangle(-5, -5, GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width + 5, 45).contains(nativeEvent.getPoint())) {
                    getJMenuBar().setVisible(true);
                } else {
                    getJMenuBar().setVisible(false);
                }
            }
        });

        jif.setBounds(0, 0, 300, 300);

        Container jifcp = jif.getContentPane();
        jifcp.setLayout(new BorderLayout());

        desktop.add(jif);
        cp.setLayout(new BorderLayout());
        cp.add(pnlMain, BorderLayout.CENTER);
        setContentPane(cp);
        addWindowListener(this);
        pack();

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, d.width, d.height);

        int alpha = (int) Math.round(255 * .1);
        rainbowListeners.add(c -> {
            SwingUtilities.invokeLater(() -> {
                System.out.println(c);
                System.out.println(jif.getBackground().getAlpha());
                jif.setBackground(new Color(c.getRed() / 255f, c.getGreen() / 255f, c.getBlue() / 255f, 1f / 255f));
                System.out.println(jif.getBackground().getTransparency());
                jif.setBorder(BorderFactory.createLineBorder(new Color(c.getRed(), c.getGreen(), c.getBlue()), 2));
//                jif.repaint();

            });
        });
//                FlatDarkLaf.installProperty(jif, "opaque", false);
                FlatDarkLaf.setup();

                jif.show();
        rainbowTimer.start();

    }

    class InternalFrame extends JInternalFrame {
        public InternalFrame(String internal_frame, boolean b, boolean b1) {
            super(internal_frame, b, b1);
        }
    }

    public void windowClosing(WindowEvent we) {
        System.exit(0);
    }

    public void windowActivated(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowOpened(WindowEvent e) {}
}