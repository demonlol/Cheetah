package sbs.baka.cheetah.social.media.snapchat.android.emulator.routines.action;

import java.awt.*;

public class SwipeAction implements Action {

    private int x1, y1, x2, y2;

    public SwipeAction(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    public SwipeAction(Point p1, Point p2) {
        this.x1 = p1.x;
        this.y1 = p1.y;
        this.x2 = p2.x;
        this.y2 = p2.y;
    }

    public int getX1() { return x1; }
    public int getY1() { return y1; }
    public int getX2() { return x2; }
    public int getY2() { return y2; }

    @Override
    public void perform() {
//        swipe <x1> <y1> <x2> <y2> [duration(ms)] (Default: touchscreen)
    }
}
