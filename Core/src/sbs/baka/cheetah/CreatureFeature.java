package sbs.baka.cheetah;

import sbs.baka.cheetah.feature.*;
import sbs.baka.cheetah.gui.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.awt.*;
import java.util.concurrent.*;

public class CreatureFeature extends WindowFeature {

    //https://auth.edgenuity.com/Login/Login/Student
    //ashfolke:usernameusername

    private JTree tree;
    private JLabel selectedLabel;

    @Override
    public InternalFrame.Builder getInternalFrame() {
        return new InternalFrame.Builder()
                .withTitle(getName())
                .is(true, true, true, true)
                .withSize(400 * 2, 250 * 2)
                .withRunnableAndInstance((frame) -> {
                    //create the root node
                    DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");

                    //create the child nodes
                    DefaultMutableTreeNode userNode = new DefaultMutableTreeNode("ashfolke:usernameusername");

                    DefaultMutableTreeNode historyNode = new DefaultMutableTreeNode("HISTORY [34%] [B-]");
                    DefaultMutableTreeNode englishNode = new DefaultMutableTreeNode("ENGLISH [74%] [A]");
                    DefaultMutableTreeNode mathNode = new DefaultMutableTreeNode("MATH    [44%] [C-]");
                    DefaultMutableTreeNode artNode = new DefaultMutableTreeNode("ART     [5%]  [D+]");

                    for (int i = 0; i < ThreadLocalRandom.current().nextInt(5, 15); i++) {
                        DefaultMutableTreeNode assingmentObj = new DefaultMutableTreeNode("Assignment #" + i);

                        for (int j = 0; j < ThreadLocalRandom.current().nextInt(5, 25); j++) {
                            assingmentObj.add(new DefaultMutableTreeNode("Problem #" + j));
                        }

                        if(ThreadLocalRandom.current().nextBoolean()) {
                            historyNode.add(assingmentObj);
                        } else if(ThreadLocalRandom.current().nextBoolean()) {
                            englishNode.add(assingmentObj);
                        } else if(ThreadLocalRandom.current().nextBoolean()) {
                            mathNode.add(assingmentObj);
                        } else if(ThreadLocalRandom.current().nextBoolean()) {
                            artNode.add(assingmentObj);
                        }
                    }

                    userNode.add(historyNode);
                    userNode.add(englishNode);
                    userNode.add(mathNode);
                    userNode.add(artNode);

                    //add the child nodes to the root node
                    root.add(userNode);

                    //create the tree by passing in the root node
                    tree = new JTree(root);
//                    ImageIcon imageIcon = new ImageIcon(CreatureFeature.class.getResource("/leaf.jpg"));
                    DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
//                    renderer.setLeafIcon(imageIcon);

                    tree.setCellRenderer(renderer);
                    tree.setShowsRootHandles(true);
                    tree.setRootVisible(false);

                    tree.getSelectionModel().addTreeSelectionListener(e -> {
//                        Supplier<TreePath> getNewLeadSelectionPath = e::getNewLeadSelectionPath;
//                        getNewLeadSelectionPath();
                    });

                    frame.add(new JScrollPane(tree));

                    selectedLabel = new JLabel();
                    frame.add(selectedLabel, BorderLayout.SOUTH);
                    tree.getSelectionModel().addTreeSelectionListener(e -> {
                        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                        if(selectedNode == null) return;
                        selectedLabel.setText(selectedNode.getUserObject().toString());
                    });
                })

                ;
    }

    @Override
    public String getName() {
        return "[MAURICE] | Edgenuity Solutions";
    }

    @Override
    public String getDesc() {
        return "";
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
}
