package sbs.baka.cheetah.social.media.snapchat.android.emulator.routines.action;

public class ClickAction implements Action {

    private int x, y;

    public ClickAction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void perform() {
        //adb shell input tap x y

    }
}
