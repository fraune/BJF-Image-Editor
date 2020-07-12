package com.fraune;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.ButtonGroup;
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
			System.out.println("Raw editor selected");
			HexEditor hexEditor = new HexEditor();
			hexEditor.setContent(file);

			emptyEditorView();
			add(hexEditor, BorderLayout.CENTER);
			break;
		case "Sectioned":
			System.out.println("Sectioned editor selected");
			// Assume file is a valid BMP
			JPanel editorView = new JPanel();
			GridLayout gridLayout = new GridLayout(4, 1);
			editorView.setLayout(gridLayout);

			HexEditor editorHeader = new HexEditor("Header");
			HexEditor editorInfoHeader = new HexEditor("Info Header");
			HexEditor editorColorTable = new HexEditor("Color Table");
			HexEditor editorPixelData = new HexEditor("Pixel Data");

			BMPFile bmp = new BMPFile(file);

			InputStream headerStream = new ByteArrayInputStream(bmp.getHeader().getAll());
			InputStream infoHeaderStream = new ByteArrayInputStream(bmp.getInfoHeader().getAll());
			InputStream colorTableStream = new ByteArrayInputStream(bmp.getColorTable().getAll());
			InputStream pixelDataStream = new ByteArrayInputStream(bmp.getPixelData().getAll());

			try {
				editorHeader.setContent(headerStream, bmp.getHeader().getOffset(), BMPHeader.headerByteCount);
				editorInfoHeader.setContent(infoHeaderStream, bmp.getInfoHeader().getOffset(), BMPInfoHeader.infoHeaderByteCount);
				editorColorTable.setContent(colorTableStream, bmp.getColorTable().getOffset(), bmp.getColorTable().getAll().length);
				editorPixelData.setContent(pixelDataStream, bmp.getPixelData().getOffset(), bmp.getPixelData().getAll().length);
			} catch (IOException e) {
				e.printStackTrace();
			}

			editorView.add(editorHeader);
			editorView.add(editorInfoHeader);
			editorView.add(editorColorTable);
			editorView.add(editorPixelData);

			emptyEditorView();
			add(editorView, BorderLayout.CENTER);
			break;
		case "Intelligent":
			System.out.println("Intelligent editor selected");
			break;
		default:
			System.out.println("???");
			break;
		}

		revalidate();
	}

	private void emptyEditorView() {
		BorderLayout layout = (BorderLayout) getLayout();
		Component lastComponent = layout.getLayoutComponent(BorderLayout.CENTER);
		if (lastComponent != null) {
			remove(lastComponent);
		}
	}

	public void setFile(File file) {
		this.file = file;
		setupEditorView();
	}
}
