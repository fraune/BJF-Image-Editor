package com.fraune.bmp;

import java.io.IOException;
import java.io.InputStream;

public class BMPPixelData implements BMPFileSection {

	private int offset;
	private byte[] pixelData;

	public BMPPixelData(int offset, InputStream inputStream, int imageSize) throws IOException {
		this.offset = offset;
		pixelData = new byte[imageSize];
		inputStream.read(pixelData);
	}

	@Override
	public int getOffset() {
		return offset;
	}

	@Override
	public byte[] getAll() {
		return pixelData;
	}
}
