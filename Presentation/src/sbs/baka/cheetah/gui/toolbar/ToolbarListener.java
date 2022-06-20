package sbs.baka.cheetah.gui.toolbar;

import javax.swing.*;

public interface ToolbarListener {

    void onHover(JComponent toolbarComponent, MouseEvent e);
    void onClick(JComponent toolbarComponent, MouseEvent e);

    enum MouseEvent {
        HOVER,
        UNHOVER,
        PRESS,
        RELEASE
    }

}
