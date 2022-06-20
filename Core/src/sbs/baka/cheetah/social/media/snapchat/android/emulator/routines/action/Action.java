package sbs.baka.cheetah.social.media.snapchat.android.emulator.routines.action;

/*
    Click [x,y]
    Swipe [x1, y1, x2, x2]
    Send Keystrokes [byte_arr]
    Back Button
    Home Button
    Done Button
    If Element Visible [ui dump]
    End Routine

    Enter
    Delete

    Cut
    Copy
    Paste


      text <string> (Default: touchscreen)
      keyevent [--longpress] <key code number or name> ... (Default: keyboard)
      tap <x> <y> (Default: touchscreen)
      swipe <x1> <y1> <x2> <y2> [duration(ms)] (Default: touchscreen)
      press (Default: trackball)
      roll <dx> <dy> (Default: trackball)

 */

public interface Action {

    void perform();

}
