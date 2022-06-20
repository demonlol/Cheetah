package sbs.baka.cheetah.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.*;
import java.awt.event.*;
import java.util.List;
import java.util.*;
import java.util.function.*;

public class DesktopFrame extends JFrame {

	public DesktopPane desktopPane;

	public DesktopFrame() {
		desktopPane = new DesktopPane();

		init();
	}

	private void init() {
//		addWindowStateListener(e -> setExtendedState(JFrame.MAXIMIZED_BOTH));

//		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setLocation(0, 0);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DesktopFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setTitle("CHEETAH");
	}

	//Overrides
	
	public void setVisible(boolean b) {
		desktopPane.setVisible(b);
		super.setVisible(b);
	}

	//Our funcs
	public void setFullscreen(boolean b) {
		GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
		setUndecorated(true);
		device.setFullScreenWindow(b ? this : null);

		update(getGraphics());
	}
	public void setWindowedFullscreen(boolean b) {
		setExtendedState(b ? JFrame.MAXIMIZED_BOTH : Frame.NORMAL);
		setUndecorated(b);
		setVisible(b);

		update(getGraphics());
	}
	public void addFrame(InternalFrame frame) {
		desktopPane.add(frame);
	}
	public void addFrame(InternalFrame frame, boolean show) {
		desktopPane.add(frame);
		if(show) frame.show();
	}

	public static class Builder {

		public interface RunnableDesktopFrame { DesktopFrame run(DesktopFrame f); }

		private DesktopFrame f;

		public Builder() {
			f = new DesktopFrame();
		}

		public Builder withNewThreadRunnable(Runnable runnable) {
			new Thread(runnable).start();
			return this;
		}
		public Builder withRunnable(Runnable runnable) {
			runnable.run();
			return this;
		}
		public Builder withRunnableAndInstance(RunnableDesktopFrame runnable) {
			f = runnable.run(f);
			return this;
		}
		public Builder withEDTRunnable(Runnable runnable) {
			SwingUtilities.invokeLater(runnable);
			return this;
		}

		public Builder isResizable(boolean resizable) {
			f.setResizable(resizable);
			return this;
		}

		public Builder withExtendedState(int state) {
			f.setExtendedState(state);
			return this;
		}
		public Builder withBackground(Color state) {
			f.setBackground(state);
			return this;
		}

		public Builder withTitle(String title) {
			f.setTitle(title);
			return this;
		}

		public Builder withDefaultCloseOperation(int operation) {
			f.setDefaultCloseOperation(operation);
			return this;
		}
		
		public Builder withTransferHandler(TransferHandler newHandler) {
			f.setTransferHandler(newHandler);
			return this;
		}

		public Builder withJMenuBar(JMenuBar menubar) {
			f.setJMenuBar(menubar);
			return this;
		}
		
		public Builder withLayout(LayoutManager manager) {
			f.setLayout(manager);
			return this;
		}
		
		protected Builder withRootPane(JRootPane root) {
			f.setRootPane(root);
			return this;
		}

		public Builder withIconImage(Image image) {
			f.setIconImage(image);
			return this;
		}
		
		public Builder withContentPane(Container contentPane) {
			f.setContentPane(contentPane);
			return this;
		}
		
		public Builder withLayeredPane(JLayeredPane layeredPane) {
			f.setLayeredPane(layeredPane);
			return this;
		}
		
		public Builder withGlassPane(Component glassPane) {
			f.setGlassPane(glassPane);
			return this;
		}
		
		public synchronized Builder withIconImages(List<? extends Image> icons) {
			f.setIconImages(icons);
			return this;
		}
		
		public Builder withMinimumSize(Dimension minimumSize) {
			f.setMinimumSize(minimumSize);
			return this;
		}
		
		public Builder withSize(Dimension d) {
			f.setSize(d);
			return this;
		}
		
		public Builder withSize(int width, int height) {
			f.setSize(width, height);
			return this;
		}
		
		public Builder withLocation(int x, int y) {
			f.setLocation(x, y);
			return this;
		}
		
		public Builder withLocation(Point p) {
			f.setLocation(p);
			return this;
		}
		
		public Builder withCursor(Cursor cursor) {
			f.setCursor(cursor);
			return this;
		}
		
		public Builder withFocusableWindowState(boolean focusableWindowState) {
			f.setFocusableWindowState(focusableWindowState);
			return this;
		}
		
		public Builder withAutoRequestFocus(boolean autoRequestFocus) {
			f.setAutoRequestFocus(autoRequestFocus);
			return this;
		}
		
		public Builder withType(Type type) {
			f.setType(type);
			return this;
		}
		
		public Builder withLocationRelativeTo(Component c) {
			f.setLocationRelativeTo(c);
			return this;
		}
		
		public Builder withLocationByPlatform(boolean locationByPlatform) {
			f.setLocationByPlatform(locationByPlatform);
			return this;
		}
		
		public Builder withBounds(int x, int y, int width, int height) {
			f.setBounds(x, y, width, height);
			return this;
		}
		
		public Builder withBounds(Rectangle r) {
			f.setBounds(r);
			return this;
		}
		
		public Builder withComponentZOrder(Component comp, int index) {
			f.setComponentZOrder(comp, index);
			return this;
		}
		
		public Builder add(Component comp, Object constraints) {
			f.add(comp, constraints);
			return this;
		}

		public Builder add(Function<DesktopFrame, Component> func) {
			Component comp = func.apply(f);
			f.add(comp);
			return this;
		}
		public Builder add(Function<DesktopFrame, Component> func, String borderLayout) {
			Component comp = func.apply(f);
			f.add(comp, borderLayout);
			return this;
		}
		
		public Builder withFont(Font fo) {
			f.setFont(fo);
			return this;
		}
		
		public synchronized Builder withDropTarget(DropTarget dt) {
			f.setDropTarget(dt);
			return this;
		}
		
		public Builder withEnabled(boolean b) {
			f.setEnabled(b);
			return this;
		}
		
		public Builder enableInputMethods(boolean enable) {
			f.enableInputMethods(enable);
			return this;
		}

		public Builder show(boolean b) {
			f.show(b);
			return this;
		}
		
		public Builder withForeground(Color c) {
			f.setForeground(c);
			return this;
		}

		public Builder withLocale(Locale l) {
			f.setLocale(l);
			return this;
		}

		public Builder resize(int width, int height) {
			f.resize(width, height);
			return this;
		}

		public Builder withPreferredSize(Dimension preferredSize) {
			f.setPreferredSize(preferredSize);
			return this;
		}

		public Builder withMaximumSize(Dimension maximumSize) {
			f.setMaximumSize(maximumSize);
			return this;
		}

		public Builder withIgnoreRepaint(boolean ignoreRepaint) {
			f.setIgnoreRepaint(ignoreRepaint);
			return this;
		}

		public synchronized Builder addComponentListener(ComponentListener l) {
			f.addComponentListener(l);
			return this;
		}
		public synchronized Builder addFocusListener(FocusListener l) {
			f.addFocusListener(l);
			return this;
		}
		public Builder addHierarchyListener(HierarchyListener l) {
			f.addHierarchyListener(l);
			return this;
		}
		public Builder addHierarchyBoundsListener(HierarchyBoundsListener l) {
			f.addHierarchyBoundsListener(l);
			return this;
		}
		public synchronized Builder addKeyListener(KeyListener l) {
			f.addKeyListener(l);
			return this;
		}
		public synchronized Builder addMouseListener(MouseListener l) {
			f.addMouseListener(l);
			return this;
		}
		public synchronized Builder addMouseMotionListener(MouseMotionListener l) {
			f.addMouseMotionListener(l);
			return this;
		}
		public synchronized Builder addMouseWheelListener(MouseWheelListener l) {
			f.addMouseWheelListener(l);
			return this;
		}
		public synchronized Builder addInputMethodListener(InputMethodListener l) {
			f.addInputMethodListener(l);
			return this;
		}
		public Builder withFocusable(boolean focusable) {
			f.setFocusable(focusable);
			return this;
		}
		public Builder withFocusTraversalKeysEnabled(boolean focusTraversalKeysEnabled) {
			f.setFocusTraversalKeysEnabled(focusTraversalKeysEnabled);
			return this;
		}
		public Builder withComponentOrientation(ComponentOrientation o) {
			f.setComponentOrientation(o);
			return this;
		}
		public Builder withMixingCutoutShape(Shape shape) {
			f.setMixingCutoutShape(shape);
			return this;
		}
		public DesktopFrame build() {
			return f;
		}
		public DesktopFrame build(boolean show) {
			f.add(f.desktopPane, BorderLayout.CENTER);
			if(show) {
				f.desktopPane.setVisible(true);
				f.setVisible(true);
			}
			return f;
		}
		public DesktopFrame build(boolean show, Runnable andThen) {
			f.add(f.desktopPane, BorderLayout.CENTER);

			if(show) {
				f.desktopPane.setVisible(true);
				f.setVisible(true);
			}

			if(andThen != null) build(show, andThen, true);

			return f;
		}
		public DesktopFrame build(boolean show, Runnable andThen, boolean edt) {
			f.add(f.desktopPane, BorderLayout.CENTER);

			if(show) {
				f.desktopPane.setVisible(true);
				f.setVisible(true);
			}

			if(andThen != null) {
				if(edt)
					SwingUtilities.invokeLater(andThen);
				else
					new Thread(andThen).start();
			}
			return f;
		}
		
	}


	
}
