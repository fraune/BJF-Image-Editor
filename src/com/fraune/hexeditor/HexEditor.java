package com.fraune.hexeditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class HexEditor extends JPanel {

	private static final long serialVersionUID = 1L;
	private final int hexBytesWidth = 16;
	private Font font = new Font("Courier New", Font.PLAIN, 16);
	private File file;
	private JTextArea textAreaEditor, textAreaRowOffset;

	public HexEditor(File file) {
		this.file = file;
		setLayout(new BorderLayout());

		JPanel mainView = new JPanel();

		BorderLayout layout = new BorderLayout();
		mainView.setLayout(layout);

		textAreaRowOffset = new JTextArea();
		textAreaRowOffset.setEditable(false);
		textAreaRowOffset.setForeground(Color.BLUE);
		textAreaRowOffset.setFont(font);

		JTextArea textAreaColumnOffset = new JTextArea("  Offset: 00 01 02 03 04 05 06 07 08 09 0A 0B 0C 0D 0E 0F");
		textAreaColumnOffset.setEditable(false);
		textAreaColumnOffset.setForeground(Color.BLUE);
		textAreaColumnOffset.setFont(font);

		textAreaEditor = new JTextArea();
		textAreaEditor.setFont(font);

		mainView.add(textAreaRowOffset, BorderLayout.WEST);
		add(textAreaColumnOffset, BorderLayout.NORTH);
		mainView.add(textAreaEditor, BorderLayout.CENTER);

		JScrollPane editorScrollPane = new JScrollPane(mainView);
		editorScrollPane.setViewportView(mainView);
		add(editorScrollPane, BorderLayout.CENTER);
		setHexContent(this.file);

	}

	public void setHexContent(File file) {
		textAreaRowOffset.setText(String.format("00000000: %n"));
		textAreaEditor.setText("");

		try (InputStream inputStream = new FileInputStream(file)) {
			int byteRead;
			for (int i = 1; (byteRead = inputStream.read()) != -1; i++) {
				textAreaEditor.append(String.format("%02X ", byteRead));
				if (i % hexBytesWidth == 0) {
					textAreaRowOffset.append(String.format("%08X: %n", i));
					textAreaEditor.append(System.lineSeparator());
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
