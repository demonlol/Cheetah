package sbs.baka.cheetah.social.media.snapchat.android.emulator.routines.action;

public class KeyAction implements Action {

    private byte[] keys;

    public KeyAction(byte...keys) {
        this.keys = keys;
    }

    @Override
    public void perform() {
//        adb shell input keyevent 64

    }
}
