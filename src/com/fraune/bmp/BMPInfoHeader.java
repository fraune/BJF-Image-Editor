package com.fraune.bmp;

import java.io.IOException;
import java.io.InputStream;

public class BMPInfoHeader implements BMPFileSection {

	public static final int infoHeaderByteCount = 40;

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

	private final int sizeOffset = 0;
	private final int widthOffset = sizeOffset + sizeByteCount;
	private final int heightOffset = widthOffset + widthByteCount;
	private final int planesOffset = heightOffset + heightByteCount;
	private final int bitsPerPixelOffset = planesOffset + planesByteCount;
	private final int compressionOffset = bitsPerPixelOffset + bitsPerPixelByteCount;
	private final int imageSizeOffset = compressionOffset + compressionByteCount;
	private final int xPixelsPerMOffset = imageSizeOffset + imageSizeByteCount;
	private final int yPixelsPerMOffset = xPixelsPerMOffset + xPixelsPerMByteCount;
	private final int colorsUsedOffset = yPixelsPerMOffset + yPixelsPerMByteCount;
	private final int importantColorsOffset = colorsUsedOffset + colorsUsedByteCount;

	private final byte[] infoHeaderBytes = new byte[infoHeaderByteCount];

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
		inputStream.read(infoHeaderBytes);

		System.arraycopy(infoHeaderBytes, sizeOffset, sizeBytes, 0, sizeByteCount);
		System.arraycopy(infoHeaderBytes, widthOffset, widthBytes, 0, widthByteCount);
		System.arraycopy(infoHeaderBytes, heightOffset, heightBytes, 0, heightByteCount);
		System.arraycopy(infoHeaderBytes, planesOffset, planesBytes, 0, planesByteCount);
		System.arraycopy(infoHeaderBytes, bitsPerPixelOffset, bitsPerPixelBytes, 0, bitsPerPixelByteCount);
		System.arraycopy(infoHeaderBytes, compressionOffset, compressionBytes, 0, compressionByteCount);
		System.arraycopy(infoHeaderBytes, imageSizeOffset, imageSizeBytes, 0, imageSizeByteCount);
		System.arraycopy(infoHeaderBytes, xPixelsPerMOffset, xPixelsPerMBytes, 0, xPixelsPerMByteCount);
		System.arraycopy(infoHeaderBytes, yPixelsPerMOffset, yPixelsPerMBytes, 0, yPixelsPerMByteCount);
		System.arraycopy(infoHeaderBytes, colorsUsedOffset, colorsUsedBytes, 0, colorsUsedByteCount);
		System.arraycopy(infoHeaderBytes, importantColorsOffset, importantColorsBytes, 0, importantColorsByteCount);

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

	@Override
	public byte[] getAll() {
		return infoHeaderBytes;
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
