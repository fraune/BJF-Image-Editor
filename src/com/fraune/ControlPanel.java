package com.fraune;

import java.awt.BorderLayout;
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
		setLayout(new BorderLayout());

		// WEST
		JLabel labelLocation = new JLabel("Image location");

		// CENTER
		JTextField textFieldLocation = new JTextField("/Users/brandonf/Programming/github/BJF-Image-Editor/data/test1.bmp");

		// EAST
		JButton buttonPickImage = new JButton("Pick image");
		JButton buttonLoadImage = new JButton("Load image");
		JButton buttonSaveImage = new JButton("Save image");

		JPanel panelImageButtons = new JPanel();
		panelImageButtons.add(buttonPickImage);
		panelImageButtons.add(buttonLoadImage);
		panelImageButtons.add(buttonSaveImage);

		JFileChooser imageFileChooser = new JFileChooser();
		imageFileChooser.setCurrentDirectory(new File("data"));

		buttonPickImage.addActionListener(click -> {
			imageFileChooser.showOpenDialog(this);
		});

		imageFileChooser.addActionListener(filePicked -> {
			File selectedFile = imageFileChooser.getSelectedFile();
			if (selectedFile != null) {
				String path = selectedFile.getAbsolutePath();
				textFieldLocation.setText(path);
			}
		});

		buttonLoadImage.addActionListener(click -> {
			JOptionPane.showMessageDialog(this, "Only BMP file types are offically supported.", "Warning", JOptionPane.WARNING_MESSAGE);

			String location = textFieldLocation.getText();
			File imageFile = new File(location);
			ip.setImageFile(imageFile);
			ep.setFile(imageFile);
		});

		add(labelLocation, BorderLayout.WEST);
		add(textFieldLocation, BorderLayout.CENTER);
		add(panelImageButtons, BorderLayout.EAST);
	}
}
