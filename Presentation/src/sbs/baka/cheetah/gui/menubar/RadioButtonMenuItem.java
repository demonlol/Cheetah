package sbs.baka.cheetah.gui.menubar;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class RadioButtonMenuItem extends JRadioButtonMenuItem {
    public static class Builder {

        private RadioButtonMenuItem w;
        private RadioButtonGroup group;

        public Builder() {
            w = new RadioButtonMenuItem();
        }
        public Builder(RadioButtonGroup group) { this(); this.group = group; }

        public Builder withText(String str) {
            w.setText(str);
            return this;
        }
        public Builder withTooltip(String str) {
            w.setToolTipText(str);
            return this;
        }
        public Builder withActionListener(ActionListener a) {
            this.w.addActionListener(a);
            return this;
        }
        public Builder withAccelerator(KeyStroke str) {
            w.setAccelerator(str);
            return this;
        }
        public Builder withSelectedIcon(Icon state) {
            w.setSelectedIcon(state);
            return this;
        }

        public Builder withIcon(Icon state) {
            w.setIcon(state);
            return this;
        }
        public Builder withIconTextGap(int state) {
            w.setIconTextGap(state);
            return this;
        }
        public Builder withDisabledIcon(Icon state) {
            w.setDisabledIcon(state);
            return this;
        }
        public Builder withPressedIcon(Icon state) {
            w.setPressedIcon(state);
            return this;
        }
        public Builder withRolloverSelectedIcon(Icon state) {
            w.setRolloverSelectedIcon(state);
            return this;
        }
        public Builder withDisabledSelectedIcon(Icon state) {
            w.setDisabledSelectedIcon(state);
            return this;
        }
        public Builder withAction(Action state) {
            w.setAction(state);
            return this;
        }
        public Builder withRolloverIcon(ActionListener state) {
            w.addActionListener(state);
            return this;
        }
        public Builder isEnabled(boolean state) {
            w.setEnabled(state);
            return this;
        }
        public Builder withBorder(Border b) {
            w.setBorder(b);
            return this;
        }
        public Builder withForeground(Color b) {
            w.setForeground(b);
            return this;
        }
        public Builder withBackground(Color b) {
            w.setBackground(b);
            return this;
        }
        
        
        
        public JRadioButtonMenuItem build() {
            return w;
        }

    }

}
