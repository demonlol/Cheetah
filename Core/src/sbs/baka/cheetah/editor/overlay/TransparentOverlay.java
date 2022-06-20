package sbs.baka.cheetah.editor.overlay;

//Either a JDesktopPane with undecorated JInternalFrames
//Or our own custom component w/ mouse handling +1111111

//Save overlay component settings and positions

import com.formdev.flatlaf.*;
import com.github.kwhat.jnativehook.*;

import javax.imageio.*;
import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class TransparentOverlay {

    public static void main(String[] args) {
        TransparentOverlay overlay = new TransparentOverlay();
    }

    final Color TRANSPARENT = new Color(0, 0, 0, 0);

    private JDialog frame;
    private BackgroundDesktopPane dp;

    private boolean editingMode = false;

    public TransparentOverlay() {
        FlatDarkLaf.setup();

        frame = new JDialog();
        frame.setModal(true);
        frame.setUndecorated(true);

        frame.getContentPane().setBackground(TRANSPARENT);
        frame.setBackground(TRANSPARENT);


        frame.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        dp = new BackgroundDesktopPane();

        TransparentInternalFrame jif = new TransparentInternalFrame();
        ((BasicInternalFrameUI) jif.getUI()).setNorthPane(null);
        jif.setSize(400, 400);
        jif.setBackground(Color.RED);
        dp.add(jif);

        frame.add(dp);

        frame.setSize(getScreenDimension());
        frame.setTitle("T");
        frame.addWindowStateListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        jif.show();
    }

    public Dimension getScreenDimension() {
        Rectangle bounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        return new Dimension(bounds.width, bounds.height);
    }

    public void enterEditing() {
        this.editingMode = true;
    }
    public void exitEditing() {
        this.editingMode = false;
    }

    public class TransparentInternalFrame extends JInternalFrame {
        private volatile int screenX = 0;
        private volatile int screenY = 0;
        private volatile int myX = 0;
        private volatile int myY = 0;

        public TransparentInternalFrame() {
            super();
            setOpaque(false);
            init();
        }
        public TransparentInternalFrame(String title) {
            super(title);
            setOpaque(false);
            init();
        }
        public TransparentInternalFrame(String title, boolean resizable) {
            super(title, resizable);
            setOpaque(false);
            init();
        }
        public TransparentInternalFrame(String title, boolean resizable, boolean closable) {
            super(title, resizable, closable);
            setOpaque(false);
            init();
        }
        public TransparentInternalFrame(String title, boolean resizable, boolean closable, boolean maximizable) {
            super(title, resizable, closable, maximizable);
            setOpaque(false);
            init();
        }
        public TransparentInternalFrame(String title, boolean resizable, boolean closable, boolean maximizable, boolean iconifiable) {
            super(title, resizable, closable, maximizable, iconifiable);
            setOpaque(false);
            init();
        }

        protected void init() {
            addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) { }
                @Override
                public void mousePressed(MouseEvent e) {
                    screenX = e.getXOnScreen();
                    screenY = e.getYOnScreen();

                    myX = getX();
                    myY = getY();
                }
                @Override
                public void mouseReleased(MouseEvent e) { }
                @Override
                public void mouseEntered(MouseEvent e) { }
                @Override
                public void mouseExited(MouseEvent e) { }
            });
            addMouseMotionListener(new MouseMotionListener() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    int deltaX = e.getXOnScreen() - screenX;
                    int deltaY = e.getYOnScreen() - screenY;

                    setLocation(myX + deltaX, myY + deltaY);
                }
                @Override
                public void mouseMoved(MouseEvent e) { }
            });

            MouseAdapter proxy = new MouseAdapter() {
                protected void dispatchEventToParent(MouseEvent e) {
                    Container parent = getParent();
                    if (parent != null) {
                        e = SwingUtilities.convertMouseEvent(e.getComponent(), e, parent);
                        parent.dispatchEvent(e);
                    }
                }
                @Override
                public void mouseMoved(MouseEvent e) {
                    dispatchEventToParent(e);
                }
                @Override
                public void mouseDragged(MouseEvent e) {
                    dispatchEventToParent(e);
                }
                @Override
                public void mouseWheelMoved(MouseWheelEvent e) {
                    dispatchEventToParent(e);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    dispatchEventToParent(e);
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    dispatchEventToParent(e);
                }
                @Override
                public void mouseReleased(MouseEvent e) {
                    dispatchEventToParent(e);
                }
                @Override
                public void mousePressed(MouseEvent e) {
                    dispatchEventToParent(e);
                }
                @Override
                public void mouseClicked(MouseEvent e) {
                    dispatchEventToParent(e);
                }
            };
            addMouseListener(proxy);
            addMouseMotionListener(proxy);
            addMouseWheelListener(proxy);
        }

        @Override
        public void paint(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();
//            g2d.setComposite(AlphaComposite.SrcOver.derive(0.25f));
            setBackground(TRANSPARENT);
            super.paint(g2d);
            g2d.dispose();
        }

    }

    public class BackgroundDesktopPane extends JDesktopPane {

        private BufferedImage background;
        private List<Point> points;

        public BackgroundDesktopPane() {
            setOpaque(false);
//            setBackground(TRANSPARENT);

//            points = new ArrayList<>(25);
////            background = ImageIO.read(new File("C:\\Users\\shane\\Dropbox\\Ponies\\800px-Rainbow_Dash_flying_past_1_S2E16.png"));
//            addMouseListener(new MouseAdapter() {
//                @Override
//                public void mouseClicked(MouseEvent e) {
//                    points.add(e.getPoint());
//                    repaint();
//                }
//
//            });
        }

        //        @Override
//        public Dimension getPreferredSize() {
//            return background == null ? super.getPreferredSize() : new Dimension(background.getWidth(), background.getHeight());
//        }
//
//        @Override
//        protected void paintComponent(Graphics g) {
//            super.paintComponent(g);
//            Graphics2D g2d = (Graphics2D) g.create();
//            if (background != null) {
//                int x = (getWidth() - background.getWidth()) / 2;
//                int y = (getHeight() - background.getHeight()) / 2;
//                g2d.drawImage(background, x, y, this);
//            }
//
//            g2d.setColor(Color.RED);
//            for (Point p : points) {
//                g2d.fillOval(p.x - 5, p.y - 5, 10, 10);
//            }
//            g2d.dispose();
//        }

    }

}
