package sbs.baka.cheetah.gui.toolbar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Toolbar extends JToolBar {

    private List<ToolbarListener> toolbarListenerList;

    public Toolbar() {
        toolbarListenerList = new ArrayList<>();
    }

    public void addToolbarListener(ToolbarListener toolbarListener) { this.toolbarListenerList.add(toolbarListener); }

    @Override
    protected void addImpl(Component comp, Object constraints, int index) {
        comp.addMouseListener(new MouseAdapter() {
            @Override public void mousePressed(MouseEvent e) {
                if(comp instanceof JToolBar.Separator) return;
                toolbarListenerList.forEach(l -> l.onClick((JComponent) comp, ToolbarListener.MouseEvent.PRESS));
            }
            @Override public void mouseReleased(MouseEvent e) {
                if(comp instanceof JToolBar.Separator) return;
                toolbarListenerList.forEach(l -> l.onClick((JComponent) comp, ToolbarListener.MouseEvent.RELEASE));
            }
            @Override public void mouseEntered(MouseEvent e) {
                if(comp instanceof JToolBar.Separator) return;
                toolbarListenerList.forEach(l -> l.onHover((JComponent) comp, ToolbarListener.MouseEvent.HOVER));
            }
            @Override public void mouseExited(MouseEvent e) {
                if(comp instanceof JToolBar.Separator) return;
                toolbarListenerList.forEach(l -> l.onHover((JComponent) comp, ToolbarListener.MouseEvent.UNHOVER));
            }
        });
        super.addImpl(comp, constraints, index);
    }

    public static class Builder {

        private Toolbar t;

        public Builder() { t = new Toolbar(); }

        public Builder add(Component component) {
            t.add(component);
            return this;
        }
        public Builder add(Component component, String layout) {
            t.add(component, layout);
            return this;
        }

        public Builder isFloatable(boolean b) {
            t.setFloatable(b);
            return this;
        }
        public Builder isRollable(boolean b) {
            t.setRollover(b);
            return this;
        }
        public Builder withMargin(Insets b) {
            t.setMargin(b);
            return this;
        }
        public Builder withOrientation(int b) {
            t.setOrientation(b);
            return this;
        }
        public Builder withLayout(LayoutManager b) {
            t.setLayout(b);
            return this;
        }
        public Builder withUI(ToolbarUI b) {
            t.setUI(b);
            return this;
        }
        public void addSeparator() {
            t.addSeparator();
        }
        public void addSeparator(Dimension size) {
            t.addSeparator(size);
        }
        public JButton add(Action a) {
            return t.add(a);
        }
        public Builder withToolbarListener(ToolbarListener toolbarListener) {
            t.addToolbarListener(toolbarListener);
            return this;
        }
        public JToolBar build() {
            return t;
        }

    }

}
