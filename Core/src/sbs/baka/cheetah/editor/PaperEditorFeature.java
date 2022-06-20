package sbs.baka.cheetah.editor;

import sbs.baka.cheetah.feature.*;
import sbs.baka.cheetah.gui.*;

import javax.swing.event.*;
import java.awt.*;

public class PaperEditorFeature extends WindowFeature {


// TODO [SIMPLIFIED]
    /*
    Multiple pages/papers open at the same time
        Cache drafts before they're saved and the program closes, and reopen them when the editor is reopened

    */


// JMENUBAR ->
    /*
        File
			New
				Document
					Object:
						Name
						File
							Path
							Last Opened
							Last Edited
				Template
					placeholders {class} {teacher} {date} {pgnum} {font} {margins} {line spacing}
					MLA Document
					APA Document
			Open
			Recently Opened
		View
		    Mobile Overlay
			Statistics
                Elapsed Time: [since created, since opened]
                WPM
                    Reset
			Word Counter
                Detect/Set Word Count
		Tools
		    Autosave
			Cite
				Create Citations
			Spell Checker
			Ant/Synonyms
				Hotkey
				Context Menu
			Sentence Starters
			Writing Phrases
			Examples
				Research paper, argumentative paper, etc...
			Assignment Overlay
				Viewer
				Simplifier
				Checklist
				Rubric
			Autosave
        Canvas
            Link To Assignment...
		Help
            sbs.baka.cheetah.Start paper from beginning to end
            Header
            Thesis
            Intro
            Body Paragraphs
            Works Cited
     */

    /*
    - create rough drafts that auto save after 5-10 seconds of typing
    - create and link folders/(drafts/files) to the following:(   [ ] | Assignment [ ]  |  Quiz [ ]  |  Test [ ]  |  Non-Gradables  |  [X] Any (DEFAULT)   )
    - create personal folder for each [ ] Any [ ] Assignment [ ] Quiz [ ] Test [ ] Non-Gradables

    ___________________________________________________________________________________________________________________________________________

    todo >> [ Helpful features for writing in MLA and APA ]
        (add source(s), add header, add body paragraph)
        tips and tricks for writing
            like how to start sentences (or phrases for starting sentences),
        examples of (argumentative, definitions for English writer terminology)
        synonym and antonym checker
        spell checker
        instructions viewer and simplifier
            - perhaps a step-by-step checkbox?
    - save as/convert to: google doc, word, txt, pdf, png, jpg, etc

    ___________________________________________________________________________________________________________________________________________

    todo >> [ESSAY ESSE   /   PAPER PAL]
    - Helps with starting a paper from beginning to end
    - Select a type of paper if necessary, and it will walk you through the steps like:
      header, thesis, intro, body pgs, starting/ending/intros/explaining phrases,
      main points, credible quotes, mla and apa works cited, preset formats (TNR, 12pt font, double spaced, 1" margins)
    - In this and/or the editor (which this might be in):
        (1) paste w/ and w/o formatting
        (2) placeholder variables in preset like:
           *****      Placeholders are applied after exporting/saving      *****
            (3) {teacher}, {class_full_name}, {class_name}, ( {date} need a default format, but also customization ), {title}

    		- Has overlay similar to LunarClient's
     */

    @Override
    public InternalFrame.Builder getInternalFrame() {
        Dimension dims = Toolkit.getDefaultToolkit().getScreenSize();

        return new InternalFrame.Builder()
                .withTitle("Document Editor")
                .is(false, true, false, true)
                .withSize(dims.width, dims.height - 25)
                .withRunnableAndInstance((frame) -> {

                })
                ;
    }

    @Override
    public String getName() {
        return "Editor";
    }

    @Override
    public String getDesc() {
        return "Custom Paper Editor";
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
