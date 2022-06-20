package sbs.baka.cheetah.util;

public class RequireUtils {

    public static void requireNonEmpty(String valueToCheck, String message) {
        if (valueToCheck == null) {
            throw new NullPointerException(message);
        }
        if (valueToCheck.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }
}
