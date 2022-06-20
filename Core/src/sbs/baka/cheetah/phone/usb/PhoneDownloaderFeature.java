package sbs.baka.cheetah.phone.usb;

import sbs.baka.cheetah.feature.*;
import sbs.baka.cheetah.gui.*;

import javax.swing.event.*;

public class PhoneDownloaderFeature extends WindowFeature {

    @Override
    public InternalFrame.Builder getInternalFrame() {
        return new InternalFrame.Builder()
                .withTitle(getName())
                .is(true, true, true, true)
                .withSize(200, 200)
                .withRunnableAndInstance((frame) -> {
                    //Will need to use google's JMTP lib.
                })
                ;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDesc() {
        return null;
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
