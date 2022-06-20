package sbs.baka.cheetah.gui;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.plaf.*;
import java.awt.*;
import java.awt.dnd.*;
import java.awt.event.*;
import java.io.*;
import java.util.function.*;

public class InternalFrame extends JInternalFrame {

    private InternalFrame() {
        //decoration changes, etc...

        //todo Failed attempt at rainbow
//        addInternalFrameListener(new InternalFrameAdapter() {
//            private javax.swing.Timer timer;
//            private volatile Color color;
//            private double f1 = .3, f2 = .3, f3 = .3, phase1 = 0, phase2 = 2, phase3 = 4, center = 0, width = 0, len = 0;
//
//            @Override
//            public void internalFrameActivated(InternalFrameEvent e) {
//                System.out.println("activated");
//                timer = new Timer(25, new AbstractAction() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        if (center == 0)   center = 128;
//                        if (width == 0)    width = 127;
//                        if (len == 0)      len = 50;
//                        for (var i = 0; i < len; ++i) {
//                            double red = Math.sin(f1 * i + phase1) * width + center, grn = Math.sin(f2 * i + phase2) * width + center, blu = Math.sin(f3 * i + phase3) * width + center;
//                            color = new Color((int) Math.ceil(red), (int) Math.ceil(grn), (int) Math.ceil(blu));
//
//                            ((JInternalFrame) e.getSource()).setBorder(BorderFactory.createLineBorder(color, 2, true));
//                        }
//                    }
//                });
//                timer.setRepeats(true);
//                timer.start();
//            }
//
//            @Override
//            public void internalFrameDeactivated(InternalFrameEvent e) {
//                timer.stop();
//            }
//        });
    }

    public static class Builder {

        public interface RunnableInternalFrame { void run(InternalFrame f) throws IOException; }

        private InternalFrame f;

        public Builder() {
            this.f = new InternalFrame();
        }

        public Builder withPanel(JPanel panel) {
            f.setContentPane(panel);
            return this;
        }
        public Builder withRunnableAndInstance(RunnableInternalFrame runnable) {
//            f = runnable.run(f);
            try {
                runnable.run(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return this;
        }
        public Builder add(Component comp, Object constraints) {
            f.add(comp, constraints);
            return this;
        }
        public Builder add(Component comp, Object constraints, int index) {
            f.add(comp, constraints, index);
            return this;
        }
        public Builder add(Function<InternalFrame, Component> func) {
            Component comp = func.apply(f);
            f.add(comp);
            return this;
        }

        public Builder is(boolean resizable, boolean closable, boolean maximizable, boolean iconifiable) {
            isResizeable(resizable);
            isClosable(closable);
            isMaximizable(maximizable);
            isIconifiable(iconifiable);
            return this;
        }

        public Builder withSize(int width, int height) {
            f.setSize(width, height);
            return this;
        }
        public Builder withSize(Dimension dimension) {
            f.setSize(dimension);
            return this;
        }
        public Builder withBounds(int x, int y, Dimension dimension) {
            f.setBounds(x, y, dimension.width, dimension.height);
            return this;
        }
        public Builder withBounds(Point point, Dimension dimension) {
            f.setBounds(point.x, point.y, dimension.width, dimension.height);
            return this;
        }
        public Builder withKeyListener(KeyAdapter adapter) {
            f.addKeyListener(adapter);
            return this;
        }
        public Builder withMouseListener(MouseAdapter mouseAdapter) {
            f.addMouseListener(mouseAdapter);
            return this;
        }
        public Builder withInternalFrameListener(InternalFrameListener internalFrameListener) {
            f.addInternalFrameListener(internalFrameListener);
            return this;
        }
        public Builder isUndecorated(boolean undecor) {
            f.putClientProperty("JInternalFrame.isPalette", undecor ? Boolean.TRUE : Boolean.FALSE);
            f.getRootPane().setWindowDecorationStyle(undecor ? JRootPane.NONE : JRootPane.FRAME);
            return this;
        }
        public Builder withTitle(String title) {
            f.setTitle(title);
            return this;
        }
        public Builder isClosable(boolean w) {
            f.setClosable(w);
            return this;
        }
        public Builder isResizeable(boolean r) {
            f.setResizable(r);
            return this;
        }
        public Builder isMaximizable(boolean m) {
            f.setMaximizable(m);
            return this;
        }
        public Builder isIconifiable(boolean i) {
            f.setIconifiable(i);
            return this;
        }
        public Builder withBackground(Color b) {
            f.getContentPane().setBackground(b);
            return this;
        }
        public Builder withIcon(Image icon) {
            f.setFrameIcon(new ImageIcon(icon));
            return this;
        }
        public Builder withBorder(Border b) {
            f.setBorder(b);
            return this;
        }
        public Builder withPopupMenu(JPopupMenu popupMenu) {
            f.setComponentPopupMenu(popupMenu);
            return this;
        }
        public Builder withDesktopIcon(JDesktopIcon icon) {
            f.setDesktopIcon(icon);
            return this;
        }
        public Builder withLocation(int x, int y) {
            f.setLocation(x, y);
            return this;
        }
        public Builder withDropTarget(DropTarget dropTarget) {
            f.setDropTarget(dropTarget);
            return this;
        }
        public Builder withInternalFrameUI(InternalFrameUI internalFrameUI) {
            f.setUI(internalFrameUI);
            return this;
        }
        public Builder withCursor(Cursor cursor) {
            f.setCursor(cursor);
            return this;
        }
        public Builder withFont(Font font) {
            f.setFont(font);
            return this;
        }
        public Builder withComponentUI(ComponentUI c) {
            f.setUI(c);
            return this;
        }
        public Builder withNewThreadRunnable(Runnable runnable) {
            new Thread(runnable).start();
            return this;
        }
        public Builder withRunnable(Runnable runnable) {
            runnable.run();
            return this;
        }
        public Builder withEDTRunnable(Runnable runnable) {
            SwingUtilities.invokeLater(runnable);
            return this;
        }

        public InternalFrame build() {
            return f;
        }
        public InternalFrame build(JDesktopPane pane) {
            if(pane != null) pane.add(f);

            return build();
        }

    }

}
