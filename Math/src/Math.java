import com.formdev.flatlaf.*;
import com.sun.tools.javac.*;

import javax.imageio.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.time.*;
import java.util.*;
import java.util.List;

public class Math {

	public static void main (String[] args) { SwingUtilities.invokeLater(() -> {
			FlatDarculaLaf.setup();
			new Math();
		}); }

	//Core impl
	JDialog select = null;
	Robot robot = getRobot();
	Rectangle selectionRect;
	Point origin, destination;
	boolean dragging = false;
	JPanel selection = null;
	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	//Mathway impl
	PopupFactory pf = new PopupFactory();
	Popup popup;
	JScrollPane jsp = null;

	Math() {
		{
			{
				selection = new JPanel();
				selection.setBackground(new Color(199, 15, 15, 0));
				selection.setBorder(BorderFactory.createLineBorder(new Color(222, 255, 0, 255), 2));
			}

			select = new JDialog();

			select.setUndecorated(true);
			select.setLayout(null);
			select.setSize(screen);
			select.setLocation(0, 0);
			select.setBackground(new Color(10, 10, 10, 1));
			select.getRootPane().setBorder(BorderFactory.createLineBorder(Color.RED, 10));

			select.addWindowStateListener(e -> { if (e.getNewState() == WindowEvent.WINDOW_CLOSED) System.exit(- 1); });
			select.addKeyListener(new KeyAdapter() {
				@Override public void keyPressed (KeyEvent e) { if (e.getKeyCode() == KeyEvent.VK_ESCAPE) select.dispose(); }
			});
			select.addMouseMotionListener(new MouseMotionListener() {
				private Point destination;

				@Override
				public void mouseDragged (MouseEvent e) {
					if (origin.distance(e.getPoint()) > 15) dragging = true;
					if (dragging) {
						select.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

						destination = getMouse();

						Point topMost = getUpperLeftCoordinate(origin, destination),
								bottomMost = ((topMost == origin) ? destination : origin);

						selectionRect = new Rectangle(topMost.x, topMost.y, bottomMost.x - topMost.x, bottomMost.y - topMost.y);
						selection.setBounds(selectionRect);

						selection.updateUI();
					}
				}

				@Override
				public void mouseMoved (MouseEvent e) { }
			});
			select.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed (MouseEvent e) {
					if (SwingUtilities.isLeftMouseButton(e)) {
						dragging = true;
						origin = getMouse();
						selection.setLocation(origin.x, origin.y);
						selection.setVisible(true);
						selection.updateUI();
					}
				}

				@Override
				public void mouseReleased (MouseEvent e) {
					select.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					if ((destination = getMouse()) != null) {
						if (origin.distance(destination) <= 10) return; //Prevent clicks interpretted as clicks
						// System.out.format("SELECTION: Origin: %s,%s   |   Destination: %s,%s", origin.x, origin.y, destination.x, destination.y + "\n");
						if (e.isPopupTrigger() && selectionRect.contains(e.getXOnScreen(), e.getYOnScreen())) {
							JPopupMenu popupMenu = new JPopupMenu();

							JMenuItem menuItem = new JMenuItem("Screenshot");
							menuItem.addActionListener((a) -> {
								SwingUtilities.invokeLater(() -> {
									selection.setVisible(false);
									selection.updateUI();

									final ImageIcon icon = getImageIconScreenshot();
									selection.setVisible(true);
									doMathwayImplementation(icon);
								});
							});

							JMenuItem saveItem = new JMenuItem("Save");
							saveItem.addActionListener((a) -> {
								final ImageIcon icon = getImageIconScreenshot();

								JFileChooser chooser = new JFileChooser(
										FileSystemView.getFileSystemView().getHomeDirectory() + "\\Desktop");
								chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
								int choose = chooser.showSaveDialog(select);
								if (choose == JFileChooser.APPROVE_OPTION) {
									File dir = new File(chooser.getSelectedFile().getAbsolutePath() + "\\" + System.currentTimeMillis() + ".png");
									try {
										if (dir.createNewFile()) {
											boolean write = ImageIO.write(toBufferedImage(icon.getImage()), "png", dir);
											if (write) {
												System.out.println("Math > Saved screenshot to " + dir.getAbsolutePath());
											} else {
												System.out.println("Math > Didn't save screenshot to " + dir.getAbsolutePath());
												Toolkit.getDefaultToolkit().beep();
											}
										} else {
											System.out.println("Failed to create " + dir.getAbsolutePath() + " when saving " +
													                   "screenshot.");
										}
									} catch (IOException ex) {
										ex.printStackTrace();
									}
								} else {
									//Approve wasn't selected. Ignore.
								}
							});

							JMenuItem ocrItem = new JMenuItem("OCR");
							ocrItem.setEnabled(false);
							ocrItem.addActionListener((a) -> {
								//Todo
							});

							JMenuItem exitItem = new JMenuItem("Exit");
							exitItem.addActionListener((a) -> {
								select.dispose();
								System.exit(0);
							});

							popupMenu.add(menuItem);
							popupMenu.add(saveItem);
							popupMenu.add(ocrItem);
							popupMenu.add(exitItem);

							popupMenu.show(select, e.getX(), e.getY());
						}
					}

					destination = null;
					dragging = false;
				}
			});

			select.add(selection);

			select.setVisible(true);
		}
	}

	void doMathwayImplementation(ImageIcon icon) {
		JDialog frame = new JDialog();

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setSize(icon.getIconWidth(), icon.getIconHeight());

		JLabel label = new JLabel(icon);
		label.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
		panel.add(label);

		JPanel mathwayPanel = new JPanel();
		{
			mathwayPanel.setBorder(BorderFactory.createTitledBorder("Mathway"));
			mathwayPanel.setLayout(new BorderLayout());

			DefaultListModel<String> defaultListModel = new DefaultListModel<>();
			defaultListModel.addAll(Arrays.stream(MathwayEval.getListData()).toList());
			JTextField searchField = new JTextField();
			JList<String> mathawayEvals = new JList<>(defaultListModel);
			mathawayEvals.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);

			searchField.setPreferredSize(new Dimension(175, 20));
			// mathawayEvals.setPreferredSize(new Dimension(275, 75));

			searchField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped (KeyEvent e) {
					String text = searchField.getText();
					if(text.length() == 0) {
						mathawayEvals.setListData(MathwayEval.getListData());
					} else {
						List<EvalSearchResult> evalSearchResults = EvalSearchResult.findSearchResults(text);
						String[] searchArray = new String[evalSearchResults.size()];
						for (int i = 0 ;i < evalSearchResults.size() ;i++) searchArray[i] = evalSearchResults.get(i).getEval().toString();
						mathawayEvals.setListData(searchArray);
					}
				}
			});
			mathawayEvals.addListSelectionListener((a) -> {
				String name = mathawayEvals.getSelectedValue();
				MathwayEval eval = MathwayEval.getEval(name);
				if(eval != null) {
					if(popup != null) popup.hide();

					JPanel pan = new JPanel();
					JButton btn = new JButton("Solve");
					btn.addActionListener((act) -> {
						if(mathawayEvals.isSelectionEmpty()) return;

					});

					pan.add(new JLabel(eval.getPrettyName()));
					pan.add(new JLabel("(DataID=" + eval.getDataId() + ")"));
					pan.add(btn);

					Point loc = jsp.getLocationOnScreen();
					popup = pf.getPopup(panel, pan, loc.x, loc.y - 75);

					popup.show();

					pan.addFocusListener(new FocusAdapter() { @Override public void focusLost (FocusEvent e) { popup.hide(); } });
				}
			});
			mathwayPanel.add(searchField, BorderLayout.NORTH);
			jsp = new JScrollPane(mathawayEvals, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			jsp.setPreferredSize(new Dimension(275, 75));
			mathwayPanel.add(jsp,BorderLayout.CENTER);
		}

		JOptionPane optionPane = new JOptionPane(
				panel,
				JOptionPane.PLAIN_MESSAGE,
				JOptionPane.DEFAULT_OPTION,
				null,
				new Object[] {
						mathwayPanel
				},
				null
		);
		frame = optionPane.createDialog(Instant.ofEpochMilli(System.currentTimeMillis()).toString());
		frame.setVisible(true);
	}

	BufferedImage toBufferedImage(Image img) {
		if (img instanceof BufferedImage) return (BufferedImage) img;
		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();

		return bimage;
	}
	ImageIcon getImageIconScreenshot() {
		Image nativeResImage = null;

		MultiResolutionImage screenCapture = robot.createMultiResolutionScreenCapture(selectionRect);
		List<Image> resolutionVariants = screenCapture.getResolutionVariants();
		if (resolutionVariants.size() > 1) {
			nativeResImage = resolutionVariants.get(1);
		} else {
			nativeResImage = resolutionVariants.get(0);
		}
		return new ImageIcon(nativeResImage);
	}
	Point getUpperLeftCoordinate(Point... points) {
		Point mostUpperLeft = null;
		for (Point point : points) {
			if (mostUpperLeft == null) {
				mostUpperLeft = point;
			} else {
				double diffX = mostUpperLeft.getX() - point.getX();
				double diffY = point.getY() - mostUpperLeft.getY();
				if (diffX + diffY > 0) {
					mostUpperLeft = point;
				}
			}
		}
		return mostUpperLeft;
	}
	Robot getRobot() {
		try {
			return new Robot();
		} catch (AWTException e) {
			return null;
		}
	}
	Point getMouse() { return MouseInfo.getPointerInfo().getLocation(); }
}
