package com.fraune.BMP;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class BMPInfoHeader {

//	private static final int infoHeaderByteCount = 40;
	private final int sizeByteCount = 4;
	private final int widthByteCount = 4;
	private final int heightByteCount = 4;
	private final int planesByteCount = 2;
	private final int bitsPerPixelByteCount = 2;
	private final int compressionByteCount = 4;
	private final int imageSizeByteCount = 4;
	private final int xPixelsPerMByteCount = 4;
	private final int yPixelsPerMByteCount = 4;
	private final int colorsUsedByteCount = 4;
	private final int importantColorsByteCount = 4;

//	private final byte[] headerBytes = new byte[headerByteCount];
	private final byte[] sizeBytes = new byte[sizeByteCount];
	private final byte[] widthBytes = new byte[widthByteCount];
	private final byte[] heightBytes = new byte[heightByteCount];
	private final byte[] planesBytes = new byte[planesByteCount];
	private final byte[] bitsPerPixelBytes = new byte[bitsPerPixelByteCount];
	private final byte[] compressionBytes = new byte[compressionByteCount];
	private final byte[] imageSizeBytes = new byte[imageSizeByteCount];
	private final byte[] xPixelsPerMBytes = new byte[xPixelsPerMByteCount];
	private final byte[] yPixelsPerMBytes = new byte[yPixelsPerMByteCount];
	private final byte[] colorsUsedBytes = new byte[colorsUsedByteCount];
	private final byte[] importantColorsBytes = new byte[importantColorsByteCount];

	private int size;
	private int width;
	private int height;
	private int planes;
	private int bitsPerPixel;
	private int compression;
	private int imageSize;
	private int xPixelsPerM;
	private int yPixelsPerM;
	private int colorsUsed;
	private int importantColors;

	public BMPInfoHeader(InputStream inputStream) throws IOException {
		inputStream.read(sizeBytes);
		inputStream.read(widthBytes);
		inputStream.read(heightBytes);
		inputStream.read(planesBytes);
		inputStream.read(bitsPerPixelBytes);
		inputStream.read(compressionBytes);
		inputStream.read(imageSizeBytes);
		inputStream.read(xPixelsPerMBytes);
		inputStream.read(yPixelsPerMBytes);
		inputStream.read(colorsUsedBytes);
		inputStream.read(importantColorsBytes);

		size = BMPFile.bytesToInt(sizeBytes);
		width = BMPFile.bytesToInt(widthBytes);
		height = BMPFile.bytesToInt(heightBytes);
		planes = BMPFile.bytesToInt(planesBytes);
		bitsPerPixel = BMPFile.bytesToInt(bitsPerPixelBytes);
		compression = BMPFile.bytesToInt(compressionBytes);
		imageSize = BMPFile.bytesToInt(imageSizeBytes);
		xPixelsPerM = BMPFile.bytesToInt(xPixelsPerMBytes);
		yPixelsPerM = BMPFile.bytesToInt(yPixelsPerMBytes);
		colorsUsed = BMPFile.bytesToInt(colorsUsedBytes);
		importantColors = BMPFile.bytesToInt(importantColorsBytes);
	}

	public int getSize() {
		return size;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getPlanes() {
		return planes;
	}

	public int getBitsPerPixel() {
		return bitsPerPixel;
	}

	public int getCompression() {
		return compression;
	}

	public int getImageSize() {
		return imageSize;
	}

	public int getxPixelsPerM() {
		return xPixelsPerM;
	}

	public int getyPixelsPerM() {
		return yPixelsPerM;
	}

	public int getColorsUsed() {
		return colorsUsed;
	}

	public int getImportantColors() {
		return importantColors;
	}

}
