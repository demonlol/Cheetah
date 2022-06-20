package sbs.baka.cheetah.social.media.snapchat.tools.data;

import org.apache.commons.io.*;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import sbs.baka.cheetah.feature.*;
import sbs.baka.cheetah.gui.*;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.io.*;
import java.lang.reflect.*;
import java.util.List;
import java.util.*;

public class ParseFriendsFeature extends WindowFeature {

    //Friends=0

    //Friend Requests Sent=1

    //Blocked Users=2

    //Deleted Friends=3

    //Hidden Friend Requests=4

    //*NO* Shortcuts

    private File saveDir = new File(System.getProperty("user.home") + "\\Desktop\\SC");

    private List<SnapchatFriendship> friendshipList;

    @Override
    public InternalFrame.Builder getInternalFrame() {
        friendshipList = new ArrayList<>();

        return new InternalFrame.Builder()
                .is(true, true, true, true)
                .withRunnableAndInstance((f) -> {
                    saveDir.mkdirs();

                    f.setTitle("Snapchat Friend Downloader");
                    f.setLayout(new FlowLayout());
                    f.setSize(200, 150);

                    JFileChooser chooser = new JFileChooser();
                    chooser.setCurrentDirectory(new File(System.getProperty("user.home") + "\\Desktop\\snapdata\\tylercheek\\html"));
                    chooser.addActionListener(a -> {
                        friendshipList.clear();

                        File friendsFile = chooser.getSelectedFile();
                        try {
                            Document doc = Jsoup.parse(FileUtils.readFileToString(friendsFile));
                            Elements tables = doc.getElementsByTag("table");
                            tables.remove(tables.size() - 1);

                            for (int i = 0; i < tables.size() - 1; i++) {
                                Class<? extends SnapchatFriendship> clazz = null;
                                switch(i) {
                                    case 0:
                                        clazz = SnapchatFriendship.Friend.class;
                                        break;
                                    case 1:
                                        clazz = SnapchatFriendship.FriendRequestSent.class;
                                        break;
                                    case 2:
                                        clazz = SnapchatFriendship.BlockedUser.class;
                                        break;
                                    case 3:
                                        clazz = SnapchatFriendship.DeletedUser.class;
                                        break;
                                    case 4:
                                        clazz = SnapchatFriendship.HiddenFriendRequest.class;
                                        break;
                                    default:
                                        clazz = null;
                                        break;
                                }

                                Constructor constructa = clazz.getDeclaredConstructor(String.class, String.class, String.class, String.class, String.class);

                                if(clazz == null) {
                                    Element table = tables.get(i);

                                    Elements tds = table.getElementsByTag("td");
                                    for (int j = 0; j < tds.size(); j += 5) {
                                        String username = tds.get(j).text(),
                                                displayName = tds.get(j + 1).text(),
                                                timestamp = tds.get(j + 2).text(),
                                                lastModifiedTimestamp = tds.get(j + 3).text(),
                                                source = tds.get(j + 4).text();

                                        SnapchatFriendship scf = null;
                                        try {
                                            scf = (SnapchatFriendship) constructa.newInstance(username, displayName, timestamp, lastModifiedTimestamp, source);
                                        } catch (InstantiationException e) {
                                            e.printStackTrace();
                                        } catch (IllegalAccessException e) {
                                            e.printStackTrace();
                                        } catch (InvocationTargetException e) {
                                            e.printStackTrace();
                                        }
                                        if(scf == null) {
                                            JOptionPane.showMessageDialog(f, "Failed to create sc frienship object.");
                                        }
                                        friendshipList.add(scf);
                                    }
                                }
                            }
                            JOptionPane.showConfirmDialog(f, "Saved " + friendshipList.size() + " relationship logs.");
                        } catch (IOException | NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                    });

                    JButton saveFriends = new JButton("Save");
                    saveFriends.addActionListener(a -> {
                        File base;
                        FileWriter fw = null;

                        base = new File(System.getProperty("user.home") + "\\Desktop\\SC\\" + System.currentTimeMillis() + "\\");

                        for (SnapchatFriendship snapchatFriendship : friendshipList) {
                            File baseFile = new File(base.getAbsolutePath() + "\\" + snapchatFriendship.toString().toLowerCase() + ".txt");

                            try {
                                boolean i = (baseFile.getParentFile().mkdirs() && baseFile.createNewFile());
                                fw = new FileWriter(baseFile, true);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            if(fw != null) {
                                String semiSerialized = snapchatFriendship.toString();
                                try {
                                    fw.append(semiSerialized);
                                    fw.append(System.getProperty("line.separator"));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    JOptionPane.showMessageDialog(f, "Error caught while writing friend to file.");
                                }
                            }

                        }

                        int i = JOptionPane.showConfirmDialog(f, "Open folder where items were saved?", "Open Folder", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null);
                        if(i == JOptionPane.YES_OPTION) {
                            try {
                                Desktop.getDesktop().open(base);
                            } catch (IOException e) {
                                JOptionPane.showConfirmDialog(f, "Failed to open folder.");
                                e.printStackTrace();
                            }
                        }
                    });
                    f.add(chooser);
                    f.add(saveFriends);
                })
                ;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDesc() {
        return null;
    }

    @Override
    public void internalFrameOpened(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameClosed(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameIconified(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameDeiconified(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameActivated(InternalFrameEvent e) {

    }

    @Override
    public void internalFrameDeactivated(InternalFrameEvent e) {

    }

    public abstract static class SnapchatFriendship implements Serializable {

        @Serial
        private static final long serialVersionUID = 341741235L;

        private final String username;
        private final String displayName;
        private final String timestamp;
        private final String lastModifiedTimestamp;
        private final String source;

        public SnapchatFriendship(String username, String displayName, String timestamp, String lastModifiedTimestamp, String source) {
            this.username = username;
            this.displayName = displayName;
            this.timestamp = timestamp;
            this.lastModifiedTimestamp = lastModifiedTimestamp;
            this.source = source;
        }

        public String getUsername() { return username; }
        public String getDisplayName() { return displayName; }
        public String getTimestamp() { return timestamp; }
        public String getLastModifiedTimestamp() { return lastModifiedTimestamp; }
        public String getSource() { return source; }

        public static class Friend extends SnapchatFriendship { public Friend(String username, String displayName, String timestamp, String lastModifiedTimestamp, String source) { super(username, displayName, timestamp, lastModifiedTimestamp, source); } }
        public static class FriendRequestSent extends SnapchatFriendship { public FriendRequestSent(String username, String displayName, String timestamp, String lastModifiedTimestamp, String source) { super(username, displayName, timestamp, lastModifiedTimestamp, source); } }
        public static class BlockedUser extends SnapchatFriendship { public BlockedUser(String username, String displayName, String timestamp, String lastModifiedTimestamp, String source) { super(username, displayName, timestamp, lastModifiedTimestamp, source); } }
        public static class DeletedUser extends SnapchatFriendship { public DeletedUser(String username, String displayName, String timestamp, String lastModifiedTimestamp, String source) { super(username, displayName, timestamp, lastModifiedTimestamp, source); } }
        public static class HiddenFriendRequest extends SnapchatFriendship {  public HiddenFriendRequest(String username, String displayName, String timestamp, String lastModifiedTimestamp, String source) { super(username, displayName, timestamp, lastModifiedTimestamp, source); } }

        @Override
        public String toString() {
            return getClass().getName() + "{" +
                    "username='" + username + '\'' +
                    ", displayName='" + displayName + '\'' +
                    ", timestamp='" + timestamp + '\'' +
                    ", lastModifiedTimestamp='" + lastModifiedTimestamp + '\'' +
                    ", source='" + source + '\'' +
                    '}';
        }
    }
}
