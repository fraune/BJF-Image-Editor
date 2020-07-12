package com.fraune.bmp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class BMPPixelData implements BMPFileSection {

	private int offset;
	private byte[] pixelData;

	public BMPPixelData(int offset, InputStream inputStream, int imageSize) throws IOException {
		this.offset = offset;
		if (imageSize > 0) {
			pixelData = new byte[imageSize];
			inputStream.read(pixelData);
		} else {
			// read to end of stream
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int byteRead;
			while ((byteRead = inputStream.read()) != -1) {
				baos.write(byteRead);
			}
			pixelData = baos.toByteArray();
		}
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
