package com.fraune.bmp;

import java.io.IOException;
import java.io.InputStream;

public class BMPPixelData implements BMPFileSection {

	private byte[] imageData;

	public BMPPixelData(InputStream inputStream, int imageSize) throws IOException {
		imageData = new byte[imageSize];
		inputStream.read(imageData);
	}

	@Override
	public byte[] getAll() {
		return imageData;
	}
}
