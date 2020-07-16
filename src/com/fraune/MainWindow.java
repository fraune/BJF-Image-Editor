package com.fraune;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JFrame;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	public MainWindow() {
		setSize(1200, 800);
		setTitle("BJF's Image Editor");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		File imageFile = new File("/Users/brandonf/Programming/github/BJF-Image-Editor/data/dog3.bmp");
		ImagePanel ip = new ImagePanel(imageFile, true);
		EditorPanel ep = new EditorPanel(imageFile);

		ControlPanel controls = new ControlPanel(ip, ep);
		ContentPanel contents = new ContentPanel(ip, ep);

		setLayout(new BorderLayout());
		add(controls, BorderLayout.NORTH);
		add(contents, BorderLayout.CENTER);

		setVisible(true);
	}
}
