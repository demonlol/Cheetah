package sbs.baka.cheetah.social.media.snapchat.android.emulator;

import java.awt.*;

public class Rainbow {

    public static Color[] RAINBOW_COLORS;

    static {
        int center = 128, width = 127, len = 250;
        float frequency1 = .3f, frequency2 = .3f, frequency3 = .3f;

        RAINBOW_COLORS = new Color[len + 1];

        for (int i = 0; i < len; ++i) {
            var red = Math.sin(frequency1 * i + 0f) * width + center;
            var grn = Math.sin(frequency2 * i + 2f) * width + center;
            var blu = Math.sin(frequency3 * i + 4f) * width + center;

            RAINBOW_COLORS[i] = new Color((int) red, (int) grn, (int) blu);
        }
    }

}
