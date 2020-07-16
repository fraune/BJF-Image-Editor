package com.fraune;

import java.awt.BorderLayout;
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
	private boolean showZoomValue;
	private JLabel message, zoomValue;
	private JPanel canvas;
	private String error;

	public ImagePanel(File imageFile, boolean showZoomValue) {
		setLayout(new BorderLayout());

		canvas = new ImageCanvas();
		add(canvas, BorderLayout.CENTER);

		setShowZoomValue(showZoomValue);
		setImageFile(imageFile);
	}

	public void setImageFile(File imageFile) {
		try {
			image = ImageIO.read(imageFile);
			if (image == null) {
				throw new IllegalArgumentException();
			}
			loadSuccess = true;
		} catch (IllegalArgumentException e) {
			error = new String("Error reading image.");
			loadSuccess = false;
		} catch (IOException e) {
			error = new String("Error opening file.");
			loadSuccess = false;
		} finally {
			if (loadSuccess) {
				if (message != null) {
					message.setVisible(false);
				}
				super.repaint();
			} else {
				if (message != null) {
					message.setText(error);
					message.setVisible(true);
				} else {
					message = new JLabel(error);
					message.setHorizontalAlignment(JLabel.CENTER);
					add(message, BorderLayout.CENTER);
				}
			}

		}
	}

	public void setShowZoomValue(boolean showZoomValue) {
		this.showZoomValue = showZoomValue;
		if (showZoomValue) {
			if (zoomValue != null) {
				zoomValue.setVisible(true);
			} else {
				zoomValue = new JLabel("100%");
				zoomValue.setHorizontalAlignment(JLabel.CENTER);
				add(zoomValue, BorderLayout.NORTH);
			}
		} else {
			if (zoomValue != null) {
				zoomValue.setVisible(false);
			}
		}
	}

	private class ImageCanvas extends JPanel {

		private static final long serialVersionUID = 1L;

		@Override
		protected void paintComponent(Graphics g) {
			if (loadSuccess) {
				int imageWidth = image.getWidth();
				int imageHeight = image.getHeight();

				int canvasWidth, canvasHeight;
				if (showZoomValue) {
					canvasWidth = canvas.getWidth();
					canvasHeight = canvas.getHeight();
				} else {
					canvasWidth = getWidth();
					canvasHeight = getHeight();
				}

				float zoomW = (float) canvasWidth / imageWidth;
				float zoomH = (float) canvasHeight / imageHeight;
				float zoom = zoomW > zoomH ? zoomH : zoomW;

				int verticalOffset = (int) Math.abs(((zoom * imageHeight) - canvasHeight) / 2);
				int horizontalOffset = (int) Math.abs(((zoom * imageWidth) - canvasWidth) / 2);
				imageWidth = (int) (zoom * imageWidth);
				imageHeight = (int) (zoom * imageHeight);

				if (showZoomValue) {
					float zoomPercent = 100 * zoom;
					zoomValue.setText(String.format("Zoom: %f%%", zoomPercent));
				}

				g.drawImage(image, horizontalOffset, verticalOffset, imageWidth, imageHeight, null);
			}
		}
	}
}
