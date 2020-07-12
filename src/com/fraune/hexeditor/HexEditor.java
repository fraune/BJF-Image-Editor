package com.fraune.hexeditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class HexEditor extends JPanel {

	private static final long serialVersionUID = 1L;
	private final int hexBytesWidth = 16;
	private Font font = new Font("Courier New", Font.PLAIN, 16);
	private JTextArea textAreaEditor, textAreaRowOffset;
	private JScrollPane editorScrollPane;

	public HexEditor() {
		this(null);
	}

	public HexEditor(String title) {
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

		if (title != null) {
			add(new JLabel(title), BorderLayout.NORTH);
		}
		mainView.add(textAreaRowOffset, BorderLayout.WEST);
		mainView.add(textAreaColumnOffset, BorderLayout.NORTH);
		mainView.add(textAreaEditor, BorderLayout.CENTER);

		editorScrollPane = new JScrollPane(mainView);
		editorScrollPane.setViewportView(mainView);
		add(editorScrollPane, BorderLayout.CENTER);
	}

	public void setContent(File file) {
		try (InputStream inputStream = new FileInputStream(file)) {
			setContent(inputStream);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public int setContent(InputStream input) throws IOException {
		return setContent(input, 0, 0);
	}

	public int setContent(InputStream inputStream, int offset, int readSize) throws IOException {
		if (offset < 0) {
			throw new IllegalArgumentException("Cannot accept a negative offset.");
		}

		boolean readToEndOfStream = readSize <= 0;

		int rowOffset = (offset / hexBytesWidth) * hexBytesWidth;
		textAreaRowOffset.setText(String.format("%08X: %n", rowOffset));
		textAreaEditor.setText("");

		int columnOffset = offset % hexBytesWidth;
		for (int i = 0; i < columnOffset; i++) {
			textAreaEditor.append("   ");
		}

		int i, byteRead;
		for (i = 1 + offset; ((byteRead = inputStream.read()) != -1) && (readToEndOfStream || (i <= readSize + offset)); i++) {
			textAreaEditor.append(String.format("%02X ", byteRead));
			if (i % hexBytesWidth == 0) {
				textAreaRowOffset.append(String.format("%08X: %n", i));
				textAreaEditor.append(System.lineSeparator());
			}
		}

		// Scroll back to top
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				editorScrollPane.getVerticalScrollBar().setValue(0);
			}
		});

		return i;
	}

}
