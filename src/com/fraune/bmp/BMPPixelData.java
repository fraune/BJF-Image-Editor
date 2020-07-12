package com.fraune.bmp;

import java.io.IOException;
import java.io.InputStream;

public class BMPPixelData implements BMPFileSection {

	private byte[] pixelData;

	public BMPPixelData(InputStream inputStream, int imageSize) throws IOException {
		pixelData = new byte[imageSize];
		inputStream.read(pixelData);
	}

	@Override
	public byte[] getAll() {
		return pixelData;
	}
}
