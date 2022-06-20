package sbs.baka.cheetah;

import com.formdev.flatlaf.*;
import sbs.baka.cheetah.canvas.*;
import sbs.baka.cheetah.canvas.wrapper.*;
import sbs.baka.cheetah.feature.*;
import sbs.baka.cheetah.gui.*;
import sbs.baka.cheetah.social.media.snapchat.android.gui.*;
import sbs.baka.cheetah.social.media.snapchat.tools.data.*;
import sbs.baka.cheetah.storage.filesys.*;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.*;

public class Start {

    //apache commons:
    //math3
    //codec
    //email
    //configuration
    //compress

    public static void printGetMethods(Object b) {
//        System.out.println("\n" + b.getClass().getSimpleName());
        for (Method method : b.getClass().getMethods()) {
            if(method.getName().startsWith("get") && !method.getName().equals("getStringId")) {
//                try {
//                    System.out.println("    - " + method.getName() + "   ...   " + method.invoke(b, null));
//                } catch (IllegalAccessException | InvocationTargetException e) { e.printStackTrace(); }
            }
        }
//        System.out.println();
    }

    public static void main(String[] args) {
        FlatDarkLaf.setup();

        InitializeGUI(
                new AndroidAutomaterFeature(),
                new ParseFriendsFeature()
        );
//        InitializeCanvas();
    }

    private static DesktopFrame window;



























    public static void InitializeCanvas() {
        FileSystem ff = FileSystem.getInstance();
        System.out.println(ff.getRoot().getAbsolutePath());

        CanvasAPI canvasAPI = new CanvasAPI();
        long start = System.currentTimeMillis();
        for (CanvasCourse cours : canvasAPI.getCourses()) {
            try {
//                System.out.println(cours.getName());

//                printGetMethods(cours);
//                printGetMethods(cours.getTabs().get(0));
//                printGetMethods(cours.getAssignments().get(0));
//                printGetMethods(cours.getSettings().get(0));
//                printGetMethods(cours.getEnrollments().get(0));
//                printGetMethods(cours.getFeatures().get(0));
//                printGetMethods(cours.getGroups().get(0));
//                printGetMethods(cours.getTeachers().get(0));
//                printGetMethods(cours.getGradingStandards().get(0));
//                printGetMethods(cours.getModules().get(0));
            } catch (IndexOutOfBoundsException ex) {
                continue;
            }
        }


        //31857ms to complete (non cache)
        System.out.println(System.currentTimeMillis() - start + "ms to complete (non cache)");
//        System.out.println(EnvironmentUtil.getJarFilePath());
    }
    public static void InitializeGUI(WindowFeature...features) {
        WindowFeature.AddFeatures(features);

        long s = System.currentTimeMillis();
        WindowFeature.LoadFeatures();
        System.out.println("Loaded features in " + (System.currentTimeMillis() - s) + "ms");

        Rectangle maxScreenBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();

        //  .add((frame) -> new Toolbar.Builder().add(new JButton("Slush")).build(), BorderLayout.NORTH)

        SwingUtilities.invokeLater(() -> window = new DesktopFrame.Builder()
                .withTitle("CHEETAH") //window title
                .withLocationRelativeTo(null) //center
                .withSize(maxScreenBounds.width / 2, maxScreenBounds.height / 2)
                .withLocation(0, 0)
                .isResizable(true)
                .withDefaultCloseOperation(DesktopFrame.EXIT_ON_CLOSE)
                .withLayout(new BorderLayout())
                //Misc code
                .withRunnableAndInstance((frame) -> {
                    for (WindowFeature feature : WindowFeature.getFeatures()) {
                        frame.addFrame(feature.getFrame(), true);
                    }

//                        WindowFeature.GetFeatureByClass(.class).startAndShow();
                    return frame;
                })

                .build(true));
    }

}