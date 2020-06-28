package com.fraune;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JFrame;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	public MainWindow() {
		setSize(600, 400);
		setTitle("BJF's Image Editor");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

//		BMPFile file = new BMPFile(new File("data/test3.bmp"));
		File imageFile = new File("data/test1.bmp");
		ImagePanel ip = new ImagePanel(imageFile);
		EditorPanel ep = new EditorPanel(imageFile);

		ControlPanel controls = new ControlPanel(ip, ep);
		ContentPanel contents = new ContentPanel(ip, ep);

		setLayout(new BorderLayout());
		add(controls, BorderLayout.NORTH);
		add(contents, BorderLayout.CENTER);

		setVisible(true);
	}
}
