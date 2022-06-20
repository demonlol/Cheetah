package sbs.baka.cheetah.editor;

import com.formdev.flatlaf.*;

import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class EditorUI implements ActionListener {

	public static void main (String[] args) {
		new EditorUI();
	}

	private JFrame f;
//	private JDocument doc;
	private static int returnValue = 0;

	/*
Advanced character formatting
Advanced paragraph formatting
Paging, multi-column layout
Header-footer and support for multi-section documents
Tables, nested tables, tables with irregular layout
RTF, ODF (OpenDocumentFormat) and XML save/load
XHTML save and load
PDF and XSL-FO export (Requires Apache FOP)
Conversion from various formats to others (RTF, XHTML, PDF, XSL-FO)
Spell checker and proof reader integration
Copy-paste (as WMF, EMF, RTF, BMP, TXT)
Vector and bitmap image support (JPG, PNG, GIF, WMF, EMF, PNG and SVG)
Fields, Symbols
Form fields and form processing
Named styles for character, paragraph and table elements
Quick styles
Bullets and numbering
Multi-level undo-redo, find-replace, drag- drop
Mail-merge
Extensible and customizable interfaces
	 */

	//Printer
	//http://java-sl.com/JEditorPanePrinter.html

	/*
Width of editor = 900
Width of scrollpane = max? or
	 */

	/*
File
	Share

	New >
		Document
		From Template >
			MLA
			APA
	Open (Ctrl + O)
	Make A Copy

	Email >
	Download >
	Version history >

	Rename
	Move to trash

	Publish to the web

	Document details
	Language
	Page setup
	Print
Edit
	Undo
	Redo

	Cut
	Copy
	Paste
	Paste without formatting
	Delete
	Select all

	Find and replace
View
	Print layout

	Show ruler
	Show document outline
	Show equation toolbar
	Show section breaks

	Full screen
Insert

Format
	Text >
	Paragraph styles >
	Align & indent >
	Line & paragraph spacing >
	Columns >
	Bullets & numbering

	Headers & footers
	Page numbers
	Page orientation
Tools
	Spelling and grammar
	Word count
	Citations
	Translate document
	Voice typing
	Preferences >
		Automatically capitalzie words
		Use smart quotes
		Automatically >
			Capitalize words
			Correct spelling

Add-Ons
Help
	 */

	//Logger.getLogger(EditorUI.class.getName()).log(Level.SEVERE, null, ex);

	public EditorUI() {
		FlatDarculaLaf.setup();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		f = new JFrame("Text Edit");
		// f.setLayout(new FlowLayout());
		f.setJMenuBar(getMenuBar());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1920, screenSize.height);

//		f.add((doc = new JDocument(f)));

		f.setVisible(true);
	}

	@Override
	public void actionPerformed (ActionEvent e) {
		String ingest = null;
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setDialogTitle("Choose destination.");
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		String ae = e.getActionCommand();
		if (ae.equals("Open")) {
			returnValue = jfc.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File f = new File(jfc.getSelectedFile().getAbsolutePath());
				try{
					FileReader read = new FileReader(f);
					Scanner scan = new Scanner(read);
					while(scan.hasNextLine()){
						String line = scan.nextLine() + "\n";
						ingest = ingest + line;
					}
					// editor.setText(ingest);
				}
				catch ( FileNotFoundException ex) { ex.printStackTrace(); }
			}
			// SAVE
		} else if (ae.equals("Save")) {
			returnValue = jfc.showSaveDialog(null);
			try {
				File f = new File(jfc.getSelectedFile().getAbsolutePath());
				FileWriter out = new FileWriter(f);
				// out.write(editor.getText());
				out.close();
			} catch (FileNotFoundException ex) {
				Component f = null;
				JOptionPane.showMessageDialog(f,"File not found.");
			} catch (IOException ex) {
				Component f = null;
				JOptionPane.showMessageDialog(f,"Error.");
			}
		} else if (ae.equals("New")) {
			// editor.setText("");
		} else if (ae.equals("Quit")) { System.exit(0); }
	}

	protected JMenuBar getMenuBar() {
		JMenuBar menu_main = new JMenuBar();

		JMenu menu_file = new JMenu("File");

		JMenuItem menuitem_new = new JMenuItem("New");
		JMenuItem menuitem_open = new JMenuItem("Open");
		JMenuItem menuitem_save = new JMenuItem("Save");
		JMenuItem menuitem_quit = new JMenuItem("Quit");

		menuitem_new.addActionListener(this);
		menuitem_open.addActionListener(this);
		menuitem_save.addActionListener(this);
		menuitem_quit.addActionListener(this);

		menu_main.add(menu_file);

		menu_file.add(menuitem_new);
		menu_file.add(menuitem_open);
		menu_file.add(menuitem_save);
		menu_file.add(menuitem_quit);

		return menu_main;
	}

	class JRichEditor extends JTextArea {

		public JRichEditor (int rows, int columns) {
			super(rows, columns);
		}

		public JRichEditor (Document doc) {
			super(doc);
		}

	}

}
