package com.fraune.bmp;

import java.io.IOException;
import java.io.InputStream;

public class BMPColorTable implements BMPFileSection {

	private byte[] colorTable;

	public BMPColorTable(InputStream inputStream, int size) throws IOException {
		colorTable = new byte[size];
		inputStream.read(colorTable);
	}

	@Override
	public byte[] getAll() {
		return colorTable;
	}

}
