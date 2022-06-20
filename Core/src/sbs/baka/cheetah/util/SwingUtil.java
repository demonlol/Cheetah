package sbs.baka.cheetah.util;

import javax.swing.*;

public class SwingUtil {

    public static void PrintIsEDT(Object obj) {
        System.out.println("Is EDT: " + SwingUtilities.isEventDispatchThread());
    }

}
