package com.fraune;

import java.awt.GridLayout;
import java.io.File;

import javax.swing.JPanel;

public class ContentPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public ContentPanel(ImagePanel ip, EditorPanel ep) {
		setLayout(new GridLayout(1, 2));
		add(ip);
		add(ep);
	}
}
