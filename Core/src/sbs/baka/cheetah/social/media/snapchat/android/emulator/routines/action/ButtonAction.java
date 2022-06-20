package sbs.baka.cheetah.social.media.snapchat.android.emulator.routines.action;

import java.io.*;

public class ButtonAction implements Action, Serializable {

    private ButtonType button;

    public ButtonAction(int button) {
        this.button = ButtonType.getButtonById(button);
        if(this.button == null) throw new NullPointerException("Failed to retrieve button with id " + button);
    }

    public ButtonType getButton() { return button; }

    @Override
    public void perform() {
//        adb shell input keyevent [button]
    }

    public enum ButtonType {

        HOME(3),
        BACK(4),
        CALL(5),
        END_CALL(6),
        TOGGLE_POWER(26),
        CAMERA(27),
        OPEN_BROWSER(64),
        ENTER(66),
        DELETE(67),
        CONTACTS(207),
        BRIGHTNESS_DOWN(220),
        BRIGHTNESS_UP(221),
        CUT(277),
        COPY(278),
        PASTE(279);

        public int key;

        ButtonType(int key) { this.key = key; }

        public static ButtonType getButtonById(int i) {
            for (ButtonType value : values()) {
                if(value.key == i) return value;
            }
            return null;
        }

    }

}
