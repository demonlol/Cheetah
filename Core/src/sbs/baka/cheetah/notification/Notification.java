package sbs.baka.cheetah.notification;

import java.awt.*;

public class Notification {

    /*
[NOTIFICATIONS]
    - type of notification
        email,
        announcement,
        discussion board (comments, replies),
        assignment,
        due date reminder
            date,
            created,
                - just now
                - unread: An assignment created {date} or {d:h:m:s} ago ... blah
                - read: You forgot/haven't submitted ... due (future:in 30 minutes! past: 30 minutes ago!)

      ___________________________________________________________________________________________________________________________________________

    - TYPE of notification (ERROR, DUE (IN _____ (GREEN), IN ____ (BAD), LATE (AWFUL), YOUR GRADE HAS CHANGED IN ____, A NEW [ASSIGNMENT/QUIZ/TEST/RESOURCE] has changed in {class_name}
    - APP NOTIFIATIONS (DEFAULT enabled) (cannot toggle)
         severity - sound
         severitycreative types of sounds
                - favorite spotify songs
                - funny noises
                - motivational noises
         severity:
                    - get more creative with it...
                    - make things intense
        - only forcefully open:
            [ ] Announcements
            [ ] Assignments
            [ ] Quizzes
            [ ] Tests
            [ ] Emails (email viewer)
            [ ]
    - desktop notifications
    - canvas (check)
    - iphone (somehow)

[DETECT DATES, TIMES, LOCATIONS...?, NAMES, MY EMAIL, SOMEONE ELSE'S EMAIL, A CLASS NAME, A CLASSMATE'S NAME, A TEACHER'S NAME]

Can specify which screen to use for notification.
Can specify which corner (center is also possible) to use for notification
If no location is specified, it will show on whatever screen the mouse is on (if a desktop notification)
Duration timeouts, with progress indicator on notification
Light or Dark themes
Can close via close button or clicking on notification body
Can show/hide the close button
Can register a callback for when a user clicks on the notification body
Animates to a collated position if multiple notifications are in the same position
Bypasses the swing EDT, and now renders at a beautiful 30 frames-per-second.
Can have notifications in an application or on the desktop.
     */

    private String header, body;
    private Color headerForeground, bodyForeground, background;
    private Font headerFont, bodyFont;
    private Callback mouseCallback;
    private Position position;
    private Window parent;

    private Dimension size;
    private Point location;

    private long duration;
    private boolean persistence;
    private boolean timeoutIndicator;
    private boolean closeButton;

    public static class Builder {

        private Notification n;

        public Builder withHeader(String header) {
            n.header = header;
            return this;
        }
        public Builder withBody(String body) {
            n.body = body;
            return this;
        }

        public Builder withHeaderForeground(Color headerForeground) {
            n.headerForeground = headerForeground;
            return this;
        }
        public Builder withBodyForeground(Color bodyForeground) {
            n.bodyForeground = bodyForeground;
            return this;
        }
        public Builder withBackground(Color background) {
            n.background = background;
            return this;
        }

        public Builder withHeaderFont(Font f) {
            n.headerFont = f;
            return this;
        }
        public Builder withBodyFont(Font f) {
            n.bodyFont = f;
            return this;
        }

        public Builder withCallback(Callback c) {
            n.mouseCallback = c;
            return this;
        }

        public Builder withPosition(Position position) {
            n.position = position;
            return this;
        }

        public Builder withParent(Window parent) {
            n.parent = parent;
            return this;
        }

        public Builder withDuration(long duration) {
            n.duration = duration;
            return this;
        }
        public Builder isPersistent(boolean persistence) {
            n.persistence = persistence;
            return this;
        }
        public Builder hasTimeoutIndicator(boolean indicator) {
            n.timeoutIndicator = indicator;
            return this;
        }
        public Builder canClose(boolean close) {
            n.closeButton = close;
            return this;
        }

        public Notification build() {
            return n;
        }
    }

    public enum Position {
        TOP_LEFT, TOP_CENTER, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT, BOTTOM_CENTER, CENTER, MOUSE;

//        private int x, y;
//        private boolean add;
//
//
    }
    public interface Callback {
        void onLeftClick();
        void onRightClick();
        void onMiddleClick();
    }

    public Dimension getSize() {
        return size;
    }

    public void setSize(Dimension size) {
        this.size = size;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public String getHeader() {
        return header;
    }

    public String getBody() {
        return body;
    }

    public Color getHeaderForeground() {
        return headerForeground;
    }

    public Color getBodyForeground() {
        return bodyForeground;
    }

    public Color getBackground() {
        return background;
    }

    public Font getHeaderFont() {
        return headerFont;
    }

    public Font getBodyFont() {
        return bodyFont;
    }

    public Callback getMouseCallback() {
        return mouseCallback;
    }

    public Position getPosition() {
        return position;
    }

    public Window getParent() {
        return parent;
    }

    public long getDuration() {
        return duration;
    }

    public boolean isPersistence() {
        return persistence;
    }

    public boolean isTimeoutIndicator() {
        return timeoutIndicator;
    }

    public boolean isCloseButton() {
        return closeButton;
    }
}
