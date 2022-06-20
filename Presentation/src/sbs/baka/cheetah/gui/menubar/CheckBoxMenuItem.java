package sbs.baka.cheetah.gui.menubar;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class CheckBoxMenuItem extends JCheckBoxMenuItem {

    public static class Builder {

        private CheckBoxMenuItem w;

        public Builder() {
            w = new CheckBoxMenuItem();
        }

        public Builder withState(boolean state) {
            w.setState(state);
            return this;
        }
        public Builder withText(String txt) {
            this.w.setText(txt);
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
        public Builder withActionListener(ActionListener state) {
            w.addActionListener(state);
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

        public JCheckBoxMenuItem build() {
            return w;
        }

    }


}
