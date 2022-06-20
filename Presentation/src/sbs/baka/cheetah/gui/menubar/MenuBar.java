package sbs.baka.cheetah.gui.menubar;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.MenuBarUI;
import java.awt.*;

public class MenuBar extends JMenuBar {

    public MenuBar() {

    }

    public static class Builder {

        private JMenuBar b;

        public Builder() { this.b = new JMenuBar(); }

        public Builder withMenu(JMenu t) {
            b.add(t);
            return this;
        }
        public Builder isVisible(boolean w) {
            b.setVisible(w);
            return this;
        }
        public Builder withBorderPainted(boolean t) {
            b.setBorderPainted(t);
            return this;
        }
        public Builder withMargin(Insets t) {
            b.setMargin(t);
            return this;
        }
        public Builder withBorder(Border t) {
            b.setBorder(t);
            return this;
        }
        public Builder withUI(MenuBarUI t) {
            b.setUI(t);
            return this;
        }
        public Builder withSingleSelectionModel(SingleSelectionModel t) {
            b.setSelectionModel(t);
            return this;
        }

        public JMenuBar build() {

            return b;
        }

    }

}
