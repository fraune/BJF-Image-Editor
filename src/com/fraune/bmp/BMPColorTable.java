package com.fraune.bmp;

import java.io.IOException;
import java.io.InputStream;

public class BMPColorTable implements BMPFileSection {

	public BMPColorTable(InputStream inputStream) throws IOException {
		throw new UnsupportedOperationException("I didn't test low bit-depth color data");
	}

	@Override
	public byte[] getAll() {
		throw new UnsupportedOperationException("I didn't test low bit-depth color data");
	}

}
