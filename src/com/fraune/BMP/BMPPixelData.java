package com.fraune.BMP;

import java.io.IOException;
import java.io.InputStream;

public class BMPPixelData {

	private byte[] imageData;

	public BMPPixelData(InputStream inputStream, int imageSize) throws IOException {
		imageData = new byte[imageSize];
		inputStream.read(imageData);
	}

	public byte[] getImageData() {
		return imageData;
	}
}
