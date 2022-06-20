package sbs.baka.cheetah.gui;

import javax.swing.*;
import java.awt.*;

public class DesktopPane extends JDesktopPane {

	public DesktopPane() {

	}

	public void addFrame(InternalFrame frame) {
		add(frame);
	}
	public void addFrame(InternalFrame frame, boolean show) {
		add(frame);
		if(show) frame.show();
	}

	@Override
	public Component add(Component comp) {
		if(!(comp instanceof InternalFrame)) {
			System.out.println(comp);
//			throw new RuntimeException("Cannot add components other than sbs.baka.cheata.gui.InternalFrame objects to sbs.baka.cheata.gui.DesktopPane.");
		}
		return super.add(comp);
	}

}
