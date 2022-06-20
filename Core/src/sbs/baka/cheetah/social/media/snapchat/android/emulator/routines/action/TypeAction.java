package sbs.baka.cheetah.social.media.snapchat.android.emulator.routines.action;

public class TypeAction implements Action {

    private String text;

    public TypeAction(String text) {
        this.text = text;
    }

    @Override
    public void perform() {
//        adb shell input text hlello
    }
}
