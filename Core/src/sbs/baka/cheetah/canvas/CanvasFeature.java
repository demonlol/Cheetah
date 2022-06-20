package sbs.baka.cheetah.canvas;

import sbs.baka.cheetah.feature.*;
import sbs.baka.cheetah.gui.*;

import javax.swing.event.*;
import java.awt.*;

public class CanvasFeature extends WindowFeature {

    /*
    [ASSIGNMENT VIEWER] - like through the editor you could "send" it over to an assignment or w/e thats accepting files.
        - an assignment viewer
            - search
            - loads a either (1) based on the context of which class or (2) empty and select a class | does this make sense lol?

    [CLASS GRAPHS]>[✓] accuracy, [✓] grade, [✓] attendance, [✓] pre-programmed tags (can be disabled in options)


     */

    @Override
    public InternalFrame.Builder getInternalFrame() {
        return new InternalFrame.Builder()
                .withTitle(getName())
                .is(false, true, false, true)
                .withBounds(900, 500,new Dimension(800, 425))
                .withRunnableAndInstance((frame) -> {

                })
                ;
    }

    @Override
    public String getName() {
        return "Classes";
    }

    @Override
    public String getDesc() {
        return "Manage your canvas classes.";
    }

    @Override
    public void internalFrameOpened(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameClosed(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameIconified(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameDeiconified(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameActivated(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameDeactivated(InternalFrameEvent e) {

    }
}
