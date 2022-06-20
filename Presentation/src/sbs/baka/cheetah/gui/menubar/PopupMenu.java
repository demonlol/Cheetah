package sbs.baka.cheetah.gui.menubar;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.PopupMenuUI;
import java.awt.*;
import java.awt.dnd.DropTarget;

public class PopupMenu extends JPopupMenu {

    public static class Builder {

        private PopupMenu w;

        public Builder() {
            w = new PopupMenu();
        }

        public Builder withBorderPainted(boolean b) {
            w.setBorderPainted(b);
            return this;
        }

        public Builder with(JMenuItem b) {
            w.add(b);
            return this;
        }
        public Builder withSelectionModel(SingleSelectionModel b) {
            w.setSelectionModel(b);
            return this;
        }
        public Builder withPopupUI(PopupMenuUI b) {
            w.setUI(b);
            return this;
        }
        public Builder withComponentUI(ComponentUI b) {
            w.setUI(b);
            return this;
        }
        public Builder isVisible(boolean b) {
            w.setVisible(b);
            return this;
        }
        public Builder withMenuKeyListener(MenuKeyListener b) {
            w.addMenuKeyListener(b);
            return this;
        }
        public Builder withPopupMenuListener(PopupMenuListener b) {
            w.addPopupMenuListener(b);
            return this;
        }
        public Builder withBorder(Border b) {
            w.setBorder(b);
            return this;
        }
        public Builder withBackground(Color b) {
            w.setBackground(b);
            return this;
        }
        public Builder withForeground(Color b) {
            w.setForeground(b);
            return this;
        }
        public Builder withFont(Font b) {
            w.setFont(b);
            return this;
        }
        public Builder withSize(Dimension b) {
            w.setPopupSize(b);
            return this;
        }
        public Builder withDropTarget(DropTarget b) {
            w.setDropTarget(b);
            return this;
        }
        public Builder withInvoker(Component b) {
            w.setInvoker(b);
            return this;
        }
        public Builder withLabel(String b) {
            w.setLabel(b);
            return this;
        }
        public Builder withLocation(Point b) {
            w.setLocation(b.x, b.y);
            return this;
        }

        public JPopupMenu build(Component invoker, int x, int y) {
            w.show(invoker, x, y);
            return w;
        }

        public JPopupMenu build() {
            return w;
        }

    }

}
