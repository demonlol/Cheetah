package sbs.baka.cheetah.gui.menubar;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.MenuItemUI;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuItem extends JMenuItem {

    public MenuItem() {

    }

    public static class Builder {
        private JMenuItem t;

        public Builder() { this.t = new JMenuItem(); }

        public Builder isEnabled(boolean b) {
            t.setEnabled(b);
            return this;
        }
        public Builder withText(String b) {
            t.setText(b);
            return this;
        }
        public Builder withMnemonic(char b) {
            t.setMnemonic(b);
            return this;
        }
        public Builder withMenuUI(MenuItemUI b) {
            t.setUI(b);
            return this;
        }
        public Builder withBorderPainted(boolean b) {
            t.setBorderPainted(b);
            return this;
        }
        public Builder withAccelerator(KeyStroke b) {
            t.setAccelerator(b);
            return this;
        }
        public Builder withButtonUI(ButtonUI b) {
            t.setUI(b);
            return this;
        }
        public Builder withBorder(Border b) {
            t.setBorder(b);
            return this;
        }
        public Builder isArmed(boolean b) {
            t.setArmed(b);
            return this;
        }
        public Builder withModel(ButtonModel b) {
            t.setModel(b);
            return this;
        }
        public Builder withAction(Action b) {
            t.setAction(b);
            return this;
        }
        public Builder withBackground(Color b) {
            t.setBackground(b);
            return this;
        }
        public Builder withComponentPopupMenu(JPopupMenu b) {
            t.setComponentPopupMenu(b);
            return this;
        }
        public Builder isContentAreaFilled(boolean b) {
            t.setContentAreaFilled(b);
            return this;
        }
        public Builder withIcon(Icon b) {
            t.setIcon(b);
            return this;
        }
        public Builder withForeground(Color b) {
            t.setForeground(b);
            return this;
        }
        public Builder withIconTextGap(int b) {
            t.setIconTextGap(b);
            return this;
        }
        public Builder withTooltip(String a) {
            t.setToolTipText(a);
            return this;
        }
        public Builder withActionListener(ActionListener a) {
            t.addActionListener(a);
            return this;
        }

        public JMenuItem build() {
            return t;
        }
    }

}
