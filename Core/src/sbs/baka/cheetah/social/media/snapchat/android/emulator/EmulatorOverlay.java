package sbs.baka.cheetah.social.media.snapchat.android.emulator;

import com.github.kwhat.jnativehook.*;
import com.github.kwhat.jnativehook.keyboard.*;
import com.sun.jna.platform.*;
import sbs.baka.cheetah.gui.menubar.Menu;
import sbs.baka.cheetah.gui.menubar.MenuBar;
import sbs.baka.cheetah.gui.menubar.MenuItem;
import sbs.baka.cheetah.gui.menubar.PopupMenu;
import sbs.baka.cheetah.social.media.snapchat.android.adb.*;

import javax.imageio.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.List;
import java.util.*;
import java.util.concurrent.atomic.*;

public class EmulatorOverlay implements Runnable, NativeKeyListener {

    /*
CALCULATE DPI FOR SNAPCHAT RATIOS

...

    Usage: input [<source>] <command> [<arg>...]

    The sources are:
      keyboard
      mouse
      joystick
      touchnavigation
      touchpad
      trackball
      dpad
      stylus
      gamepad
      touchscreen

    The commands and default sources are:
      text <string> (Default: touchscreen)
      keyevent [--longpress] <key code number or name> ... (Default: keyboard)
      tap <x> <y> (Default: touchscreen)
      swipe <x1> <y1> <x2> <y2> [duration(ms)] (Default: touchscreen)
      press (Default: trackball)
      roll <dx> <dy> (Default: trackball)
     */

    private final Color TRANSPARENT = new Color(0 / 255f, 0 / 255f, 0 / 255f, 0f);
    private final Color BARELY_TRANSLUCENT = new Color(0 / 255f, 0 / 255f, 0 / 255f, .01f);


    private static EmulatorOverlay inst;
    public static EmulatorOverlay getInstance() { return  inst == null ? (inst = new EmulatorOverlay()) : inst; }

    private Thread t, rt;
    private JDialog f;
    private AtomicBoolean uiKeyPressed;

    private AtomicInteger inactiveSleep, frameSleep; //saves resources

    private AtomicBoolean holdToShow;

    private EmulatorOverlay() {
        t = new Thread(this);

        uiKeyPressed = new AtomicBoolean(true); //true for now

        inactiveSleep = new AtomicInteger(125);
        frameSleep = new AtomicInteger(30);

        holdToShow = new AtomicBoolean(false);
    }

    Rectangle lastBounds = null;

    @Override
    public void run() {
        //Create GUI

        //If showing gui basically
        boolean uiKeyDown;
        AtomicInteger colorIndex = new AtomicInteger(0), colorMaxIndex = new AtomicInteger(Rainbow.RAINBOW_COLORS.length - 1);

        Color[] rainbow = Rainbow.RAINBOW_COLORS;

        while(t.isAlive()) {
            uiKeyDown = uiKeyPressed.get();

            if(f != null) f.setVisible(uiKeyDown);

            if(uiKeyDown) {
                Rectangle bounds = getWindowBounds("imthebestgg");

                lastBounds = bounds;

                if(colorIndex.get() > colorMaxIndex.get())
                    colorIndex.set(0);
                Color c = rainbow[colorIndex.get()];
                colorIndex.incrementAndGet();

//                System.out.println(c);

                //Teleport gui over targetted
                SwingUtilities.invokeLater(() -> {
                    if(lastBounds != null && bounds.getLocation() != lastBounds.getLocation()) f.setBounds(bounds);
                    f.setBackground(BARELY_TRANSLUCENT);

                    f.getRootPane().setBorder(BorderFactory.createLineBorder(c, 2, true));

//                    f.getRootPane().setBorder(BorderFactory.createLineBorder(Rainbow.RAINBOW_COLORS[colorIndex.incrementAndGet()], 2, true));
                });
                sleep(frameSleep.get());
            } else {
                sleep(inactiveSleep.get());
            }

        }

        //Destroy GUI
        SwingUtilities.invokeLater(f::dispose);
    }


    private Rectangle getWindowBounds(String title) {
        final Rectangle rect = new Rectangle(0, 0, 0, 0);

        WindowUtils.getAllWindows(true).forEach(desktopWindow -> {
            if (desktopWindow.getTitle().contains(title)) {
                String fp = desktopWindow.getFilePath();
                String processName = fp.substring(fp.lastIndexOf('\\') + 1);
                String root = fp.substring(0, fp.lastIndexOf('\\') - 1);

                rect.setRect(desktopWindow.getLocAndSize());

//                String processName = fp.substring(fp.lastIndexOf('\\') + 1);
//                System.out.println(processName);
            }
        });
        if(rect.x == 0 && rect.y == 0 && rect.width == 0 && rect.height == 0)
            return null;
        else {
            if(true) { //if bluestacks
                rect.y += (15 * 2) /*bs title bar*/;
                rect.height -= 35;

                rect.width -= 30 /*bs controls*/;
            }
            return rect;
        }
    }
    private void sleep(long ms) { try { Thread.sleep(ms); } catch (Exception e) { e.printStackTrace(); } }
    public void start() throws InterruptedException {
        if(f == null) buildWindow();

        if (t == null || t.isInterrupted())
            t = new Thread(this);
        else if (t.isAlive())
            t.join();
        t.start();
    }
    public void stop() throws InterruptedException { t.join(); }
    public boolean isActive() {
        return (f != null && (f.isVisible() || f.isShowing()) || t.isAlive());
    }

    private JTextArea console;
    private JScrollPane jsp = null;
    private List<String> pastCommands;

    private void buildWindow() {
        pastCommands = new ArrayList<>();
        try {
            GlobalScreen.addNativeKeyListener(this);
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }

        ADB adb = new ADB(PopularEmulators.BLUESTACKS);

        SwingUtilities.invokeLater(() -> {
            f = new JDialog();

            f.setLayout(new BorderLayout());
            f.setAlwaysOnTop(true);
            f.setUndecorated(true);
            f.getRootPane().setBorder(BorderFactory.createLineBorder(new Color(12, 194, 92), 2, true));
            f.setBackground(TRANSPARENT);

            JGear settings = new JGear();
            settings.setCursor(new Cursor(Cursor.HAND_CURSOR));
            settings.addActionListener(a -> {
                try {
                    System.out.println(
                            adb.execute(ADB.USER_INSTALLED_PACKAGES.split(" "))
                            );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            settings.setBounds(0, 0, 25, 25);
            f.add(settings);

            JTextField consoleField = new JTextField("adb ", 120);
            consoleField.addKeyListener(new KeyAdapter() {
                private int index = 0;

                @Override
                public void keyTyped(KeyEvent e) {
                    System.out.println(e.getKeyCode() + " | " + KeyEvent.VK_UP + " | " + KeyEvent.VK_DOWN);

                    if(e.getKeyCode() == KeyEvent.VK_UP) {
                        index += 1;
                        if(index > pastCommands.size() - 1) {
                            index = 0;
                            return;
                        }

                        String cmd = pastCommands.get(index);
                        consoleField.setText("adb " + cmd);
                    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        index -= 1;

                        if(index == -1) {
                            consoleField.setText("");
                            return;
                        }
                        String cmd = pastCommands.get(index);
                        consoleField.setText("adb " + cmd);
                    } else if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                        index = 0;
                    }
                }
            });
            consoleField.addActionListener(a -> {
                try {
                    log(adb.execute(consoleField.getText().substring(4).split(" ")));
                    pastCommands.add(consoleField.getText());
                    consoleField.setText("");
                    scrollToBottom();

                } catch (IOException e) {
                    JOptionPane.showMessageDialog(f, "Failed to execute.");
                    e.printStackTrace();
                }
            });
            ((AbstractDocument) consoleField.getDocument()).setDocumentFilter(new DocumentFilter() {
                private String TEXT_NOT_TO_TOUCH = "adb ";

                @Override
                public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                    if (offset < TEXT_NOT_TO_TOUCH.length())
                        return;
                    super.insertString(fb, offset, string, attr);
                }

                @Override
                public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                    if (offset < TEXT_NOT_TO_TOUCH.length()) {
                        length = Math.max(0, length - TEXT_NOT_TO_TOUCH.length());
                        offset = TEXT_NOT_TO_TOUCH.length();
                    }
                    super.replace(fb, offset, length, text, attrs);
                }

                @Override
                public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
                    if (offset < TEXT_NOT_TO_TOUCH.length()) {
                        length = Math.max(0, length + offset - TEXT_NOT_TO_TOUCH.length());
                        offset = TEXT_NOT_TO_TOUCH.length();
                    }
                    if (length > 0)
                        super.remove(fb, offset, length);
                }
            });

            console = new JTextArea(8, 40);
            console.setForeground(Color.WHITE);
            console.setBackground(new Color(75 / 255f, 75 / 255f, 75 / 255f, .5f));

            JPanel p1 = new JPanel(), p2 = new JPanel(), p3 = new JPanel(), p4 = new JPanel();
            p1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));
            p1.setBackground(TRANSPARENT);
            p1.setOpaque(false);
            p1.setLayout(new BorderLayout());

            p2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));
            p2.setBackground(TRANSPARENT);
            p2.setOpaque(false);
            p2.setLayout(new BorderLayout());

            p3.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));
            p3.setBackground(TRANSPARENT);
            p3.setOpaque(false);
            p3.setLayout(new BorderLayout());

            p4.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));
            p4.setBackground(TRANSPARENT);
            p4.setOpaque(false);
            p4.setLayout(new BorderLayout());


            jsp = new JScrollPane(console, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            JViewport viewport = jsp.getViewport();
            viewport.setOpaque(false);
            viewport.setBackground(console.getBackground());
            jsp.setBackground(console.getBackground());
            jsp.setOpaque(false);

            JPopupMenu jpm = new JPopupMenu();
            jpm.add(new MenuItem.Builder().withText("Clear console")
                    .withActionListener(a -> {
                        console.setText("");
                    })
                    .build());
            jpm.add(new MenuItem.Builder().withText("Clear console history")
                    .withActionListener(a -> {
                        pastCommands.clear();
                    })
                    .build());
            console.setComponentPopupMenu(jpm);

            console.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(SwingUtilities.isRightMouseButton(e)) {
                        new PopupMenu()
                                .add(new Menu.Builder().withText("Clear console")
                                        .withActionListener(a -> {
                                            console.setText("");
                                        })
                                        .build())
                                .add(new Menu.Builder().withText("Clear console history")
                                        .withActionListener(a -> {
                                            pastCommands.clear();
                                        })
                                        .build()).setVisible(true);


                    }
                }
            });

            JButton closeConsole = new JButton("Close Console");
            closeConsole.addActionListener(a -> {
                jsp.setVisible(!jsp.isVisible());
            });

            p1.add(closeConsole, BorderLayout.NORTH);
            p2.add(jsp, BorderLayout.CENTER);
            p3.add(consoleField, BorderLayout.NORTH);

            p1.add(p2, BorderLayout.CENTER);
            p1.add(p3, BorderLayout.SOUTH);

            f.add(p1, BorderLayout.SOUTH);

            f.setJMenuBar(new MenuBar.Builder()
                    .withMenu(new Menu.Builder()
                            .withText("View")
                            .with(new Menu.Builder()
                                    .withText("Console")
                                    .withActionListener((a) -> {
                                        //Show console @ bottom of screen
                                    })
                                    .build())
                            .build())
                    .withMenu(new Menu.Builder()
                            .withText("Tools")
                            .with(new Menu.Builder()
                                    .withText("Record")
                                    .with(new MenuItem.Builder().withText("Audio").withActionListener((a) -> {

                                            }).build())
                                    .with(new MenuItem.Builder().withText("Video").withActionListener((a) -> {

                                            }).build())
                                    .with(new MenuItem.Builder().withText("Video + Audio").withActionListener((a) -> {

                                            }).build())
                                    .build())
                            .with(new MenuItem.Builder().withText("Screenshot").withActionListener((a) -> {

                            }).build())
                            .withSeparator()
                            .with(new Menu.Builder()
                                    .withText("Spoof Camera")
                                    .build())
                            .build())
                    .withMenu(new Menu.Builder().withText("ADB")
                            .with(new MenuItem.Builder().withText("Android Version: v0.13.43").build())
                            .withSeparator()
                            .with(new Menu.Builder().withText("Devices").withRunnableAndInstance((menu) -> {
                                        //Add each device as menu, then "connect" "disconnect" "reconnect"

                                        //Search for app
                                        //adb shell dumpsys window windows | findstr <any unique string from your pkg Name>

                                        //current activity name
//                                        adb shell dumpsys window | find "mCurrentFocus"
                                        //adb shell dumpsys window windows | find "mCurrentFocus"

                                        //adb shell pm list packages -3
//                                        package:com.snapchat.android

//                                          Start app w/o activity/intent
//                                        adb shell monkey -p com.snapchat.android -c android.intent.category.LAUNCHER 1
                                        return menu;
                                    })
                                    .build())
                            .withSeparator()
                            .with(new MenuItem.Builder().withText("Start server").withActionListener((a) -> {

                            }).build())
                            .with(new MenuItem.Builder().withText("Kill server").withActionListener((a) -> {

                            }).build())
                            .withSeparator()
                            .with(new Menu.Builder()
                                    .withText("Shell")
                                    .with(new MenuItem.Builder().withText("Run command...").withActionListener((a) -> {

                                    }).build())
                                    .with(new MenuItem.Builder().withText("Run emulator console command...").withActionListener((a) -> {
//                                        adb emu <command>            - run emulator console command
                                    }).build())
                                    .withSeparator()
                                    .with(new MenuItem.Builder().withText("List user installed packages...").withActionListener((a) -> {
                                                //adb shell pm list packages -3"|cut -f 2 -d ":
                                    }).build()).with(new MenuItem.Builder().withText("Re/set emulator size/dpi").withActionListener((a) -> {
                                                        /*
                                                            adb shell wm size 2048x1536
                                                            adb shell wm density 288
                                                            // And reset to default
                                                            adb shell wm size reset
                                                            adb shell wm density reset
                                                         */
                                    }).build()).with(new MenuItem.Builder().withText("Run command...")
                                                    .withActionListener((a) -> {

                                                    })
                                            .build()
                                    ).with(new MenuItem.Builder().withText("Run command...")
                                            .build()
                                    )
                                    .build())
                            .with(new Menu.Builder().withText("I/O")
                                    .with(new MenuItem.Builder()
                                            .withText("Install APK...")
                                            .withActionListener((a) -> {
                                                //adb -e install path/to/app.apk
                                            })
                                            .build())
                                    .withSeparator()
                                    .with(new MenuItem.Builder()
                                            .withText("Push/upload file...")
                                            .withActionListener((a) -> {
//                                                adb push [source] [destination]    // Copy files from your computer to your phone.
                                            })
                                            .build())
                                    .with(new MenuItem.Builder()
                                            .withText("Pull/download file...")
                                            .withActionListener((a) -> {
//                                                adb pull [device file location] [local file location] // Copy files from your phone to your computer.
                                            })
                                            .build())
                                    .withSeparator()
                                    .with(new MenuItem.Builder()
                                            .withText("Dump uiautomator")
                                            .withActionListener((a) -> {
                                                //adb shell uiautomator dump

                                                //adb pull /sdcard/window_dump.xml (read from file)
                                                //adb shell cat /sdcard/window_dump.xml (read from shell)
                                            })
                                            .build())
                                    .build())
                            .build())
                    .withMenu(new Menu.Builder()
                            .withText("Routines")
                            .build())
                    .build());

            f.addMouseListener(new MouseAdapter() {
                //JUST RECORD MOUSE MOVEMENTS INSTEAD OF SETTING EACH AND EVERY THING.
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    super.mouseReleased(e);
                }

                @Override
                public void mouseWheelMoved(MouseWheelEvent e) {
                    super.mouseWheelMoved(e);
                }

                @Override
                public void mouseDragged(MouseEvent e) {
                    super.mouseDragged(e);
                }

                @Override
                public void mouseMoved(MouseEvent e) {
                    super.mouseMoved(e);
                }
            });

            f.addWindowStateListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    try {
                        stop();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            });

            f.setVisible(true);
        });
    }

    private void scrollToBottom() {
        JScrollBar vsb;
        (vsb = jsp.getVerticalScrollBar()).setValue(vsb.getMaximum());
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeEvent) {
//        if(!holdToShow.get()) uiKeyPressed.set(!uiKeyPressed.get());
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeEvent) {
//        if(nativeEvent.getKeyCode() == NativeKeyEvent.VC_BACKQUOTE && holdToShow.get()) {
//            uiKeyPressed.set(true);
//        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeEvent) {
//        if(nativeEvent.getKeyCode() == NativeKeyEvent.VC_BACKQUOTE && holdToShow.get()) {
//            uiKeyPressed.set(false);
//        }
    }

    private void log(String s) {
        console.setText(console.getText() + "\n" + s);
    }

    class JGear extends JButton {

        private BufferedImage bi;
        private Image gearIcon;
        int avg = -1;
        int type = -1;
        boolean mouseHover;

        public JGear() {
            super();

            try {
                bi = ImageIO.read(getClass().getClassLoader().getResourceAsStream("ui_design/android/gear.png"));
                type = bi.getType();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    mouseHover = true;
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    mouseHover = false;
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            if(avg == -1) avg = 25;
            if(gearIcon == null) gearIcon = bi.getScaledInstance(avg, avg, type);

            setSize(new Dimension(avg, avg));

//            System.out.println(avg);

            g.setColor(mouseHover ? Color.GRAY.brighter() : Color.GRAY);
            g.fillRect(0, 0, avg, avg);

            g.drawImage(gearIcon, 0, 0, null);

            g.dispose();
        }
    }

}
