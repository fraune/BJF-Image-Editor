package com.fraune;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private BufferedImage image;

	public ImagePanel(File imageFile) {
		setImageFile(imageFile);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters
	}

	public void setImageFile(File imageFile) {
		try {
			image = ImageIO.read(imageFile);
			super.repaint();
		} catch (IOException ex) {
			// handle exception...
			System.out.println(ex.getMessage());
		}
	}
}
