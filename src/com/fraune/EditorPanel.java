package com.fraune;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.fraune.hexeditor.HexEditor;

public class EditorPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private HexEditor hexEditor;

	public EditorPanel(File imageFile) {
		setLayout(new BorderLayout());

		// initialize components
		JPanel panelEditorStyle = new JPanel();
		ButtonGroup buttonGroupEditorStyle = new ButtonGroup();
		JRadioButton radioButtonRaw = new JRadioButton("Raw");
		JRadioButton radioButtonSectioned = new JRadioButton("Sectioned");
		JRadioButton radioButtonIntelligent = new JRadioButton("Intelligent");

		radioButtonRaw.setSelected(true);

		buttonGroupEditorStyle.add(radioButtonRaw);
		buttonGroupEditorStyle.add(radioButtonSectioned);
		buttonGroupEditorStyle.add(radioButtonIntelligent);

		panelEditorStyle.add(radioButtonRaw);
		panelEditorStyle.add(radioButtonSectioned);
		panelEditorStyle.add(radioButtonIntelligent);

		hexEditor = new HexEditor();
		setImageFile(imageFile);

		// add components to panel
		add(panelEditorStyle, BorderLayout.NORTH);
		add(hexEditor, BorderLayout.CENTER);
	}

	protected void setImageFile(File imageFile) {
		try (InputStream inputStream = new FileInputStream(imageFile)) {
			hexEditor.setHexContent(inputStream);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
