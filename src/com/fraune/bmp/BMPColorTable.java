package com.fraune.bmp;

import java.io.IOException;
import java.io.InputStream;

public class BMPColorTable implements BMPFileSection {

	private int offset;
	private byte[] colorTable;

	public BMPColorTable(int offset, InputStream inputStream, int size) throws IOException {
		this.offset = offset;
		colorTable = new byte[size];
		inputStream.read(colorTable);
	}

	@Override
	public int getOffset() {
		return offset;
	}

	@Override
	public byte[] getAll() {
		return colorTable;
	}

}
