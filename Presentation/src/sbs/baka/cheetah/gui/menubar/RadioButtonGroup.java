package sbs.baka.cheetah.gui.menubar;

import javax.swing.*;

public class RadioButtonGroup extends ButtonGroup {
    public static class Builder {

        private RadioButtonGroup w;

        public Builder() {
            w = new RadioButtonGroup();
        }

        public Builder with(AbstractButton b) {
            w.add(b);
            return this;
        }
        public Builder isSelected(ButtonModel model, boolean b) {
            w.setSelected(model, b);
            return this;
        }
        public ButtonGroup build() {
            return w;
        }

    }

}
