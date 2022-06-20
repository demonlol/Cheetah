package sbs.baka.cheetah.gui.menubar;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.plaf.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JMenu {

    public Menu() {

    }

    public interface RunnableMenuFrame { Menu run(Menu f); }

    public static class Builder {
        private Menu t;

        public Builder() { this.t = new Menu(); }

        public Builder withAccelerator(KeyStroke b) {
            t.setAccelerator(b);
            return this;
        }
        public Builder isVisible(boolean b) {
            t.setVisible(b);
            return this;
        }
        public Builder withUI(ButtonUI b) {
            t.setUI(b);
            return this;
        }
        public Builder withUI(MenuItemUI b) {
            t.setUI(b);
            return this;
        }
        public Builder withUI(ComponentUI b) {
            t.setUI(b);
            return this;
        }
        public Builder with(boolean b) {
            t.setEnabled(b);
            return this;
        }
        public Builder withBorder(Border b) {
            t.setBorder(b);
            return this;
        }
        public Builder withMnemonic(char a) {
            t.setMnemonic(a);
            return this;
        }
        public Builder withText(String b) {
            t.setText(b);
            return this;
        }
        public Builder with(JMenuItem menuItem) {
            t.add(menuItem);
            return this;
        }
        public Builder with(JCheckBoxMenuItem menuItem) {
            t.add(menuItem);
            return this;
        }
        public Builder with(JRadioButtonMenuItem menuItem) {
            t.add(menuItem);
            return this;
        }
        public Builder with(JRadioButtonMenuItem...menuItem) {
            for (JRadioButtonMenuItem jRadioButtonMenuItem : menuItem) {
                t.add(jRadioButtonMenuItem);
            }
            return this;
        }
        public Builder withRunnableAndInstance(RunnableMenuFrame runnable) {
            t = runnable.run(t);
            return this;
        }
        public Builder with(Component c) {
            t.add(c);
            return this;
        }
        public Builder with(Component c, int index) {
            t.add(c, index);
            return this;
        }
        public Builder with(String s) {
            t.add(s);
            return this;
        }
        public Builder with(Action a) {
            t.add(a);
            return this;
        }
        public Builder withSeparator() {
           t.addSeparator();
            return this;
        }
        public Builder withMenuListener(MenuListener l) {
           t.addMenuListener(l);
            return this;
        }
        public Builder withMenuDragMouseListener(MenuDragMouseListener l) {
           t.addMenuDragMouseListener(l);
            return this;
        }
        public Builder withMenuKeyListener(MenuKeyListener l) {
           t.addMenuKeyListener(l);
            return this;
        }
        public Builder withActionListener(ActionListener l) {
           t.addActionListener(l);
            return this;
        }

        public Menu build() {

            return t;
        }
    }
    
}
