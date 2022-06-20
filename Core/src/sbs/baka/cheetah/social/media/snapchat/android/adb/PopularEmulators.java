package sbs.baka.cheetah.social.media.snapchat.android.adb;

public enum PopularEmulators {

    BLUESTACKS("C:\\Program Files\\Bluestacks_nxt"),
    NOX_PLAYER("C:\\Program Files (x86)\\Nox\\bin"),
    MEMU("C:\\Program Files\\Microvirt\\MEmu");

    private String path;

    PopularEmulators(String p) {
        this.path = p;
    }

    public String getPath() {
        return path;
    }

}
