package com.fraune;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.fraune.bmp.BMPFile;
import com.fraune.bmp.BMPHeader;
import com.fraune.bmp.BMPInfoHeader;
import com.fraune.hexeditor.HexEditor;

public class EditorPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JRadioButton selectedButton;
	private File file;

	public EditorPanel(File imageFile) {
		this.file = imageFile;
		setLayout(new BorderLayout());

		// initialize components
		JPanel panelEditorStyle = new JPanel();
		ButtonGroup buttonGroupEditorStyle = new ButtonGroup();
		JRadioButton radioButtonRaw = new JRadioButton("Raw");
		JRadioButton radioButtonSectioned = new JRadioButton("Sectioned");
		JRadioButton radioButtonIntelligent = new JRadioButton("Intelligent");

		radioButtonRaw.setSelected(true);
		selectedButton = radioButtonRaw;

		radioButtonRaw.addActionListener(click -> {
			selectedButton = radioButtonRaw;
			setupEditorView();
		});
		radioButtonSectioned.addActionListener(click -> {
			selectedButton = radioButtonSectioned;
			setupEditorView();
		});
		radioButtonIntelligent.addActionListener(click -> {
			selectedButton = radioButtonIntelligent;
			setupEditorView();
		});

		buttonGroupEditorStyle.add(radioButtonRaw);
		buttonGroupEditorStyle.add(radioButtonSectioned);
		buttonGroupEditorStyle.add(radioButtonIntelligent);

		panelEditorStyle.add(radioButtonRaw);
		panelEditorStyle.add(radioButtonSectioned);
		panelEditorStyle.add(radioButtonIntelligent);

		setupEditorView();

		// add components to panel
		add(panelEditorStyle, BorderLayout.NORTH);
	}

	private void setupEditorView() {
		switch (selectedButton.getText()) {
		case "Raw":
			HexEditor hexEditor = new HexEditor();
			hexEditor.setContent(file);
			add(hexEditor, BorderLayout.CENTER);
			break;
		case "Sectioned":
			System.out.println("Sectioned");
			// Assume file is a valid BMP
			JPanel editorView = new JPanel();
			editorView.setLayout(new GridLayout(3, 1));

			HexEditor editorHeader = new HexEditor();
			HexEditor editorInfoHeader = new HexEditor();
//			HexEditor editorColorTable = new HexEditor();
			HexEditor editorPixelData = new HexEditor();

			BMPFile bmp = new BMPFile(file);

			InputStream headerStream = new ByteArrayInputStream(bmp.getHeader().getAll());
			InputStream infoHeaderStream = new ByteArrayInputStream(bmp.getHeader().getAll());
//			InputStream colorTableStream = new ByteArrayInputStream(bmp.getHeader().getAll());
			InputStream pixelDataStream = new ByteArrayInputStream(bmp.getHeader().getAll());

			try {
				editorHeader.setContent(headerStream, 0, BMPHeader.headerByteCount);
				editorInfoHeader.setContent(infoHeaderStream, 0, BMPInfoHeader.infoHeaderByteCount);
				editorPixelData.setContent(pixelDataStream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			editorView.add(editorHeader);
			editorView.add(editorInfoHeader);
			editorView.add(editorPixelData);
//			editorView.add(new JLabel("test"));
//			editorView.add(new JLabel("test"));
//			editorView.add(new JLabel("test"));

			BorderLayout layout = (BorderLayout) getLayout();
			remove(layout.getLayoutComponent(BorderLayout.CENTER));
			add(editorView, BorderLayout.CENTER);
			super.repaint();
			break;
		case "Intelligent":
			break;
		default:
			break;
		}
	}

	public void setFile(File file) {
		this.file = file;
		setupEditorView();
	}
}
