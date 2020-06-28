package com.fraune;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class EditorPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextArea editor;

	public EditorPanel(File imageFile) {
		setLayout(new BorderLayout());
		editor = new JTextArea();
		JScrollPane jsp = new JScrollPane(editor);
		System.out.println(this.getMaximumSize());
		editor.setWrapStyleWord(true);
		add(new JLabel("asdf"), BorderLayout.NORTH);
		add(jsp, BorderLayout.CENTER);
		this.setImageFile(imageFile);
	}

	public void setImageFile(File imageFile) {
		editor.setText("");
		try (InputStream inputStream = new FileInputStream(imageFile)) {
			int byteRead;
			while ((byteRead = inputStream.read()) != -1) {
				editor.append(Integer.toHexString(byteRead) + " ");
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
