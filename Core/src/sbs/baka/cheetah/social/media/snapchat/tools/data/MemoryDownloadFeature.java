package sbs.baka.cheetah.social.media.snapchat.tools.data;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import sbs.baka.cheetah.feature.*;
import sbs.baka.cheetah.gui.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.image.*;
import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.text.*;
import java.util.List;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

public class MemoryDownloadFeature extends WindowFeature {

    protected File outputDir = new File(System.getProperty("user.home") + "\\Desktop\\SC");

    private DefaultTableModel dtm = new DefaultTableModel();
    private JTable jt;

    @Override
    public InternalFrame.Builder getInternalFrame() {
        if(!outputDir.exists()) outputDir.mkdirs();

        return new InternalFrame.Builder()
                .withRunnableAndInstance((f) -> {
                    f.setSize(485, 425);
                    f.setTitle("Snapchat Memory Downloader ;)");
                    f.setLayout(new FlowLayout());

                    File file = new File("C:\\Users\\Tyler\\Desktop\\snapdata\\ashleyfolkes\\html\\memories_history.html");

                    List<Memory> memories = getMemoriesFromHtml(file);

                    f.add(new JLabel("Loaded " + memories.size() + " memories."));

                    Object[][] data = new Object[memories.size()][6];
                    for (int i = 0; i < memories.size(); i++) {
                        Memory memory = memories.get(i);
                        data[i][0] = memory.getMediaType() != null ? memory.getMediaType().getExtensionName() : "N\\A";
                        data[i][1] = memory.progressBar.getValue();
                        data[i][2] = memory.getSize();
                        data[i][3] = memory.getDownloadTime();
                        data[i][4] = memory.getFormattedDate();
                        data[i][5] = memory.getId();
                    }
                    jt = new JTable(data, new String[]{"Type", "Progress", "Size", "Time", "Date", "ID"});

//                    dtm = (DefaultTableModel) jt.getModel().x;

                    JButton dl = new JButton("Begin Download"), of = new JButton("Open Folder");
                    dl.setPreferredSize(new Dimension(120, 25));
                    of.setPreferredSize(new Dimension(120, 25));

                    dl.addActionListener(a -> {
                        for (Memory memory : memories.stream().sorted(Comparator.comparing(Memory::getDate).reversed()).toList()) {
                            memory.download(); //10 swing workers at a time, rest are added to queue
                        }
                    });
                    of.addActionListener(a -> {
                        try { Desktop.getDesktop().open(outputDir); } catch (IOException e) { e.printStackTrace(); }
                    });

                    JScrollPane jsp = new JScrollPane(jt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    jsp.setPreferredSize(new Dimension(450,200));

                    f.add(jsp);

                    f.add(dl);
                    f.add(of);

                    //Select memories html file
                    //Read all links from it
                    //Send the correct requests to them multithreadedly
                    //  Make sure the file date names / creation dates are correct and in order from beginning to end...
                    //Separate pictures and videos

                    //SECOND REQUEST
                    /*
                        Request URL: https://sc-prod-memories-dmd-us-east-2.s3.us-east-2.amazonaws.com/dmd-us-east-2/dac78e9b-c0a1-48ed-abb3-d2fb25a55f01/3f60e852-745a-4028-aa7a-aa17e09138e3.mp4?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAIB2CEGB7ZKGRPFZA%2F20220524%2Fus-east-2%2Fs3%2Faws4_request&X-Amz-Date=20220524T105853Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=0b9400166b15cc1d7e7aaf290afddf44e336b299bd8c78a4f255d775559951da
                        Request Method: GET

                        Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,     * / *     ;q=0.8,application/signed-exchange;v=b3;q=0.9
                        Accept-Encoding: gzip, deflate, br
                        Accept-Language: en-US,en;q=0.9
                        Cache-Control: no-cache
                        Connection: keep-alive
                        Host: sc-prod-memories-dmd-us-east-2.s3.us-east-2.amazonaws.com
                        Upgrade-Insecure-Requests: 1
                        User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36

                        .mp4
                        .jpg
                     */

                })
                ;
    }

    private List<Memory> getMemoriesFromHtml(File htmlFile) {
        List<Memory> list = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");

        try {
            Document parse = Jsoup.parse(Files.readString(Paths.get(htmlFile.toURI())));

            Elements tds = parse.getElementsByTag("td");
            for (int i = 0; i < tds.size(); i+=3) {
                Element dateElement = tds.get(i), linkElement = tds.get(i + 2);
                String dateString = dateElement.text(), linkString = linkElement.child(0).attr("href");

                linkString = linkString.replace("javascript:downloadMemories('", "");
                linkString = linkString.replace("');", "");

                Date date = null;
                try {
                    date = sdf.parse(dateString);
                } catch (ParseException e) {
                    System.err.println("Failed to parse date " + dateString + "");
                }

                System.out.println(date);
                System.out.println(linkString);

                list.add(new Memory(date, linkString, (memory, progress) -> memory.progressBar.setValue(progress)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private class Memory {

        interface MemoryProgressEvent {
            void progressUpdated(Memory memory, int progress);
        }

        private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        private final DecimalFormat df = new DecimalFormat("0.00");

        private SwingWorker<Long, Double> worker;

        protected enum MediaType {
            IMAGE(".jpg"),
            VIDEO(".mp4");

            private String ext;

            MediaType(String ext) { this.ext = ext; }
            public String getFileExtension() { return ext; }
            public String getExtensionName() { return ext.substring(1).toUpperCase(); }
            public static MediaType getTypeByLink(String link) {
                if(link.contains(".mp4")) return VIDEO;
                else if(link.contains(".jpg")) return IMAGE;
                else return null;
            }
        }

        public String id = getRandomId();
        public JProgressBar progressBar;

        private Date date;
        private MediaType mediaType;
        private String link;

        private long start, end;

        public long size;
        public AtomicBoolean downloaded;
        public MemoryProgressEvent progress;

        public Memory(Date date, String link, MemoryProgressEvent progress) {
            this.progressBar = new JProgressBar();
            this.progress = new MemoryProgressEvent() {
                @Override
                public void progressUpdated(Memory memory, int progress) {

                }
            };

            this.date = date;
            this.mediaType = MediaType.getTypeByLink(link);
            if(mediaType == null) {
                System.out.println("Skip for now.");
            }
            this.link = link;
            this.downloaded = new AtomicBoolean(false);
            this.worker = new SwingWorker<>() {

                private long downloadMediaFile(String link, File dir) {
                    System.out.println(link);
                    mediaType = MediaType.getTypeByLink(link);

                    InputStream input = null;
                    OutputStream output = null;
                    HttpURLConnection connection = null;
                    try {
                        File outputdirpath = new File(new String(dir.getAbsolutePath() + "\\" + mediaType.getExtensionName()));
                        File outputFile = new File(outputdirpath.getAbsolutePath() + "\\" + String.valueOf(getPrettyFormattedDate() + mediaType.getFileExtension()).replace(' ', '_'));
                        outputdirpath.mkdirs();
                        outputFile.createNewFile();

                        connection = (HttpURLConnection) new URL(link).openConnection();
                        connection.connect();

                        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) JOptionPane.showMessageDialog(getFrame(), "Server returned HTTP " + connection.getResponseCode() + " " + connection.getResponseMessage());

                        long fileLength = connection.getContentLength();

                        // download the file
                        input = connection.getInputStream();
                        output = new FileOutputStream(outputFile);

                        long totalBytesWritten = 0;
                        byte[] data = new byte[4096];
                        int count;
                        while ((count = input.read(data)) != -1) {
                            output.write(data, 0, count); //Write to file
                            totalBytesWritten += count; //Add to total bytes downloaded
                            // if(fileLength != -1) publish((((double) totalBytesWritten) / ((double) fileLength))); //Update progress
                        }
                        setFileCreationDate(outputFile.getAbsolutePath(), date);
                        return totalBytesWritten;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return -1;
                    } finally {
                        try {
                            if (output != null) output.close();
                            if (input != null) input.close();
                        } catch (IOException ignored) {}

                        if (connection != null) connection.disconnect();
                    }
                }
                private String getDownloadLink(String link) {
                    try {
                        Connection.Response response = Jsoup.connect(link)
                                .method(Connection.Method.POST)
                                .headers(Map.of(
                                        "content-type",
                                        "application/x-www-form-urlencoded",
                                        "user-agent",
                                        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36"
                                        ))
                                .execute();
                        if(response.statusCode() == 200) {
                            return response.body();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected Long doInBackground() {
                    return (size = downloadMediaFile(getDownloadLink(link), outputDir));
                }
                @Override
                protected void done() {
                    downloaded.set(true);
                    end = System.currentTimeMillis();

                    for (int i = 0; i < dtm.getRowCount(); i++) {
                        if(dtm.getValueAt(i, 5).toString().equals(getId())) {
                            dtm.setValueAt(getSize(), i, 2);
                            dtm.setValueAt(getDownloadTime(), i, 3);
                            dtm.setValueAt(getFormattedDate(), i, 4);
                        }
                    }
                }
                @Override
                protected void process(List<Double> chunks) {
//                    for (Double chunk : chunks) {
//                        worker.setValue((int) Math.round(chunk));
//                    }
                    super.process(chunks);
                }

            };
            this.worker.addPropertyChangeListener(e -> {
                if(e.getPropertyName().equals("progress")) {
                    progress.progressUpdated(this, (Integer) e.getNewValue());
                }
            });
        }

        public String getId() { return id; }
        private String getRandomId() {
            char[] chars = "1234567890qwertyuiopasdfghjklzxcvbnm".toCharArray();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 12; i++) {
                sb.append(chars[ThreadLocalRandom.current().nextInt(0, chars.length - 1)]);
            }
            return sb.toString();
        }
        public String getPrettyFormattedDate() { return new SimpleDateFormat("MMMMM dd yyyy hh-mm-ss a SSS").format(date); }
        public Date getDate() { return date; }
        public long getDateInMs() { return date.getTime(); }
        public String getFormattedDate() { return sdf.format(date);}
        public String getFormattedDate(SimpleDateFormat sdf) { return sdf.format(date); }
        public MediaType getMediaType() { return mediaType; }
        public String getLink() { return link; }
        public boolean isDownloaded() { return downloaded.get(); }
        public void setDownloaded() { this.downloaded.set(true); }
        public long getSize() {
            if(!isDownloaded()) return -1;
            return size;
        }
        public boolean isDownloading() { return this.worker.getState() != SwingWorker.StateValue.DONE; }
        public long getDownloadTime() {
            if(isDownloading()) {
                return System.currentTimeMillis() - start;
            } else {
                return start - end;
            }
        }
        public void download() {
            this.start = System.currentTimeMillis();
            worker.execute();
        }
    }

    protected void setFileCreationDate(String filePath, Date creationDate) throws IOException {
        BasicFileAttributeView attributes = Files.getFileAttributeView(Paths.get(filePath), BasicFileAttributeView.class);
        FileTime time = FileTime.fromMillis(creationDate.getTime());
        attributes.setTimes(time, time, time);
    }

    @Override
    public String getName() { return null; }
    @Override
    public String getDesc() { return null; }
    @Override
    public void internalFrameOpened(InternalFrameEvent e) {}
    @Override
    public void internalFrameClosing(InternalFrameEvent e) {}
    @Override
    public void internalFrameClosed(InternalFrameEvent e) {}
    @Override
    public void internalFrameIconified(InternalFrameEvent e) {}
    @Override
    public void internalFrameDeiconified(InternalFrameEvent e) {}
    @Override
    public void internalFrameActivated(InternalFrameEvent e) {}
    @Override
    public void internalFrameDeactivated(InternalFrameEvent e) {}

    public class DropPane extends JPanel {

        FilesDroppedEvent callback;
        interface FilesDroppedEvent {
            void onDrop(List files);
        }

        private DropTarget dropTarget;
        private DropTargetHandler dropTargetHandler;
        private Point dragPoint;

        private boolean dragOver = false;
        private BufferedImage target;

        private JLabel message;

        public DropPane(FilesDroppedEvent callback) {
            this.callback = callback;

//            try {
//                target = ImageIO.read(new File("target.png"));
//            } catch (IOException ex/) {
//                ex.printStackTrace();
//            }

            setLayout(new GridBagLayout());
            message = new JLabel();
            message.setFont(message.getFont().deriveFont(Font.BOLD, 24));
            add(message);

        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(100, 300);
        }

        protected DropTarget getMyDropTarget() {
            if (dropTarget == null) {
                dropTarget = new DropTarget(this, DnDConstants.ACTION_COPY, null);
            }
            return dropTarget;
        }

        protected DropTargetHandler getDropTargetHandler() {
            if (dropTargetHandler == null) {
                dropTargetHandler = new DropTargetHandler();
            }
            return dropTargetHandler;
        }

        @Override
        public void addNotify() {
            super.addNotify();
            try {
                getMyDropTarget().addDropTargetListener(getDropTargetHandler());
            } catch (TooManyListenersException ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void removeNotify() {
            super.removeNotify();
            getMyDropTarget().removeDropTargetListener(getDropTargetHandler());
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (dragOver) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setColor(new Color(0, 255, 0, 64));
                g2d.fill(new Rectangle(getWidth(), getHeight()));

                FontMetrics fm = g2d.getFontMetrics();
                String str = "Drop memory html here...";
                int strW = fm.stringWidth(str);
                g2d.drawString(str, (getWidth() / 2) - (strW / 2), (getHeight() / 2) - (fm.getHeight() / 2));

                g2d.dispose();
            }
        }

        protected void importFiles(final List files) {
            callback.onDrop(files);
        }

        protected class DropTargetHandler implements DropTargetListener {

            protected void processDrag(DropTargetDragEvent dtde) {
                if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                    dtde.acceptDrag(DnDConstants.ACTION_COPY);
                } else {
                    dtde.rejectDrag();
                }
            }

            @Override
            public void dragEnter(DropTargetDragEvent dtde) {
                processDrag(dtde);
                SwingUtilities.invokeLater(new DragUpdate(true, dtde.getLocation()));
                repaint();
            }

            @Override
            public void dragOver(DropTargetDragEvent dtde) {
                processDrag(dtde);
                SwingUtilities.invokeLater(new DragUpdate(true, dtde.getLocation()));
                repaint();
            }

            @Override
            public void dropActionChanged(DropTargetDragEvent dtde) {
            }

            @Override
            public void dragExit(DropTargetEvent dte) {
                SwingUtilities.invokeLater(new DragUpdate(false, null));
                repaint();
            }

            @Override
            public void drop(DropTargetDropEvent dtde) {

                SwingUtilities.invokeLater(new DragUpdate(false, null));

                Transferable transferable = dtde.getTransferable();
                if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                    dtde.acceptDrop(dtde.getDropAction());
                    try {

                        List transferData = (List) transferable.getTransferData(DataFlavor.javaFileListFlavor);
                        if (transferData != null && transferData.size() > 0) {
                            importFiles(transferData);
                            dtde.dropComplete(true);
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    dtde.rejectDrop();
                }
            }
        }

        public class DragUpdate implements Runnable {

            private boolean dragOver;
            private Point dragPoint;

            public DragUpdate(boolean dragOver, Point dragPoint) {
                this.dragOver = dragOver;
                this.dragPoint = dragPoint;
            }

            @Override
            public void run() {
                DropPane.this.dragOver = dragOver;
                DropPane.this.dragPoint = dragPoint;
                DropPane.this.repaint();
            }
        }

    }

}
