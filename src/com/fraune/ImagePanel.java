package com.fraune;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	private boolean loadSuccess = false;
	private JLabel message;

	public ImagePanel(File imageFile) {
		setLayout(new BorderLayout());
		message = new JLabel("Error");
		message.setHorizontalAlignment(JLabel.CENTER);
		add(message, BorderLayout.CENTER);

		setImageFile(imageFile);
	}

	@Override
	protected void paintComponent(Graphics g) {
		if (loadSuccess) {
			int imageWidth = image.getWidth();
			int imageHeight = image.getHeight();
			int canvasWidth = this.getWidth();
			int canvasHeight = this.getHeight();

			float zoomW = (float) canvasWidth / imageWidth;
			float zoomH = (float) canvasHeight / imageHeight;
			float zoom = zoomW > zoomH ? zoomH : zoomW;

			int verticalOffset = (int) Math.abs(((zoom * imageHeight) - canvasHeight) / 2);
			int horizontalOffset = (int) Math.abs(((zoom * imageWidth) - canvasWidth) / 2);
			imageWidth = (int) (zoom * imageWidth);
			imageHeight = (int) (zoom * imageHeight);

			g.drawImage(image, horizontalOffset, verticalOffset, imageWidth, imageHeight, Color.PINK, null);
		}
	}

	public void setImageFile(File imageFile) {
		try {
			image = ImageIO.read(imageFile);
			if (image == null) {
				message.setText("Error reading image.");
				message.setVisible(true);
			} else {
				message.setVisible(false);
			}
			loadSuccess = true;
			super.repaint();
		} catch (IllegalArgumentException | IOException e) {
			message.setText("Error opening file.");
			message.setVisible(true);
			loadSuccess = false;
		}
	}
}
