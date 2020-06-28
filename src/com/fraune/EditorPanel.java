package com.fraune;

import java.awt.BorderLayout;
import java.awt.Font;
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
	private final int hexBytesWidth = 16;

	public EditorPanel(File imageFile) {
		setLayout(new BorderLayout());
		editor = new JTextArea();
		Font font = new Font("Courier New", Font.PLAIN, 16);
		editor.setFont(font);
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
			for (int i = 1; (byteRead = inputStream.read()) != -1; i++) {
				editor.append(String.format("%02X", byteRead));
				if (i % hexBytesWidth == 0) {
					editor.append(System.lineSeparator());
				} else {
					editor.append(" ");
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
