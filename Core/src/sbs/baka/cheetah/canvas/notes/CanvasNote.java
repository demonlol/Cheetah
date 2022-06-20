package sbs.baka.cheetah.canvas.notes;

public class CanvasNote {

    /*
    		abstract Note Object:
			Date(long)
			String(name)
			String(filepath)
			abstract <T extends InputStream> data()
			Runnable capture() - ends if JDialog is closed or done taking note
			Subclasses:
				AudibleNote
					@Override AudioInputStream data()
				TextualNote
					String getDataAsText()
				PhotoNote
					Image getDataAsImage()
     */

}
