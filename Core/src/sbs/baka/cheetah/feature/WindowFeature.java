package sbs.baka.cheetah.feature;

import sbs.baka.cheetah.gui.*;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public abstract class WindowFeature implements InternalFrameListener {

    private static WindowFeature[] features;

    private String name, desc;
    private WindowWorker frameWorker;

    public WindowFeature() {
        this.name = getName();
        this.desc = getDesc();
    }

    public WindowWorker getFrameWorker() { return frameWorker; }
    public InternalFrame getFrame() { return frameWorker.internalFrame; }
    public void start() { this.frameWorker.execute(); }
    public void startAndShow() { this.frameWorker.execute(); this.frameWorker.internalFrame.show(); }
    public void load() { this.frameWorker = new WindowWorker(getInternalFrame().build()); this.getFrame().addInternalFrameListener(this); }
    public boolean cancel() { return this.frameWorker.cancel(true); }
    public int getID() { return name.hashCode(); }
    public void show() { this.frameWorker.internalFrame.show(); }

    public abstract InternalFrame.Builder getInternalFrame();
    public abstract String getName();
    public abstract String getDesc();

    public static WindowFeature[] getFeatures() { return features; }
    public static void AddFeatures(WindowFeature...feats) { features = feats; }
    public static void LoadFeatures() { for (WindowFeature feature : features) feature.load(); }
    public static void StartAllFeatures() { for (WindowFeature feature : features) { feature.start(); } }
    public static <T extends WindowFeature, V extends Class<T>> T GetFeatureByClass(V clazz) { for (WindowFeature windowFeature : features) { if(windowFeature.getClass() == clazz) return ((T) windowFeature); } return null; }
    public static <T extends WindowFeature, V extends Class<T>> void StartFeatureByClass(V clazz) { for (WindowFeature windowFeature : features) { if(windowFeature.getClass() == clazz) windowFeature.start(); } }
    public static <T extends WindowFeature, V extends Class<T>> void StopFeatureByClass(V clazz) { for (WindowFeature windowFeature : features) { if(windowFeature.getClass() == clazz) windowFeature.start(); } }

    public static class WindowWorker extends SwingWorker {

        private final InternalFrame internalFrame;
        private long startTime, endTime;

        protected WindowWorker(InternalFrame internalFrame) {
            this.internalFrame = internalFrame;
        }

        @Override
        protected Object doInBackground() {
            this.startTime = System.currentTimeMillis();
            return null;
        }

        @Override
        protected void done() {
            this.endTime = System.currentTimeMillis();
            System.out.println("Windowed feature closed. Alive Time: " + (endTime - startTime) + "ms");
        }

        @Override
        protected void process(List chunks) {

        }

        public long getStartTime() { return startTime; }
        public long getEndTime() { return endTime; }
    }

}
