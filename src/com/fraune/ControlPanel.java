package com.fraune;

import java.awt.FlowLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ControlPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public ControlPanel(ImagePanel ip, EditorPanel ep) {
		setLayout(new FlowLayout());

		JLabel labelLocation = new JLabel("Image location");
		JTextField textBoxLocation = new JTextField("~/");
		JButton buttonPickImage = new JButton("Pick image");
		JButton buttonLoadImage = new JButton("Load image");
		JButton buttonSaveImage = new JButton("Save image");
		JFileChooser jfc = new JFileChooser();

		buttonPickImage.addActionListener(click -> {
			System.out.println("clicked");
			jfc.showOpenDialog(this);
		});

		jfc.addActionListener(filePicked -> {
			System.out.println("picked file");
			String path = jfc.getSelectedFile().getAbsolutePath();
			textBoxLocation.setText(path);
		});

		buttonLoadImage.addActionListener(click -> {
			String location = textBoxLocation.getText();
			if (location.endsWith(".bmp") == false) {
				JOptionPane.showMessageDialog(this, "Only BMP file types are offically supported.", "Warning",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			File imageFile = new File(location);
			ip.setImageFile(imageFile);
			ep.setImageFile(imageFile);
		});

		add(labelLocation);
		add(textBoxLocation);
		add(buttonPickImage);
		add(buttonLoadImage);
		add(buttonSaveImage);
	}
}
