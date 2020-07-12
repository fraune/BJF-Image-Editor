package com.fraune.bmp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class BMPFile {

	private BMPHeader header;
	private BMPInfoHeader infoHeader;
	private BMPColorTable colorTable;
	private BMPPixelData pixelData;

	public BMPFile(File bmpFile) {
		try (InputStream inputStream = new FileInputStream(bmpFile)) {
			header = new BMPHeader(inputStream);
			infoHeader = new BMPInfoHeader(inputStream);
			int colorTableBytes = infoHeader.getBitsPerPixel() >= 8 ? 0 : 4 * infoHeader.getNumberColors();
			colorTable = new BMPColorTable(inputStream, colorTableBytes);
			pixelData = new BMPPixelData(inputStream, infoHeader.getImageSize());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public BMPHeader getHeader() {
		return header;
	}

	public BMPInfoHeader getInfoHeader() {
		return infoHeader;
	}

	public BMPColorTable getColorTable() {
		return colorTable;
	}

	public BMPPixelData getPixelData() {
		return pixelData;
	}

	protected static int bytesToInt(byte[] bytes) {
		if (bytes.length < 4) {
			bytes = BMPFile.extendByteArray(bytes, (4 - bytes.length));
		}
		ByteBuffer byteBuffer = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN);
		return byteBuffer.getInt();
	}

	protected static byte[] extendByteArray(byte[] inputBytes, int bytesToAdd) {
		byte[] result = new byte[inputBytes.length + bytesToAdd];
		System.arraycopy(inputBytes, 0, result, 0, inputBytes.length);
		return result;
	}
}
