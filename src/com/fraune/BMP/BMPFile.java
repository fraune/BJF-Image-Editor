package com.fraune.BMP;

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
	private BMPFileSection pixelData;

	public BMPFile(File bmpFile) {
		try (InputStream inputStream = new FileInputStream(bmpFile)) {
			header = new BMPHeader(inputStream);
			infoHeader = new BMPInfoHeader(inputStream);
			if (infoHeader.getBitsPerPixel() < 8) {
				colorTable = new BMPColorTable(inputStream);
			}
			pixelData = new BMPPixelData(inputStream, infoHeader.getImageSize());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
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
