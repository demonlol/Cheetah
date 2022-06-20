package sbs.baka.cheetah.notification;

import java.util.*;

public class Notifier {

    private static Notifier notifier;
    public static Notifier getNotifier() { return notifier == null ? notifier = new Notifier() : notifier; }

    private Notifier() {}

    private List<Notification> notifications;

    //JFrame
    //Math
    //Blah blah blah

    public void display(Notification notification) {

    }

}
