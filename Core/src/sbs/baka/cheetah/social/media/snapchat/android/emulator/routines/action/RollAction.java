package sbs.baka.cheetah.social.media.snapchat.android.emulator.routines.action;

import java.io.*;

public class RollAction implements Action, Serializable {

    private int dx, dy;

    public RollAction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() { return dx; }
    public int getDy() { return dy; }

    @Override
    public void perform() {
        //adb shell input roll [dx, dxy]
        //positive for down, negative for up. numbers don't seem to matter past like 100
    }
}
