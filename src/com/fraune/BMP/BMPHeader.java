package com.fraune.BMP;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class BMPHeader {

//	private static final int headerByteCount = 14;
	private final int signatureByteCount = 2;
	private final int fileSizeByteCount = 4;
	private final int reservedByteCount = 4;
	private final int dataOffsetByteCount = 4;

//	private final byte[] headerBytes = new byte[headerByteCount];
	private final byte[] signatureBytes = new byte[signatureByteCount]; // "BM"
	private final byte[] fileSizeBytes = new byte[fileSizeByteCount];
	private final byte[] reservedBytes = new byte[reservedByteCount]; // unused
	private final byte[] dataOffsetBytes = new byte[dataOffsetByteCount];

	private String signature;
	private int fileSize;
	private int dataOffset;

	public BMPHeader(InputStream inputStream) throws IOException {
		inputStream.read(signatureBytes);
		inputStream.read(fileSizeBytes);
		inputStream.read(reservedBytes);
		inputStream.read(dataOffsetBytes);

		signature = new String(signatureBytes);
		fileSize = BMPFile.bytesToInt(fileSizeBytes);
		dataOffset = BMPFile.bytesToInt(dataOffsetBytes);
	}

	public String getSignature() {
		return signature;
	}

	public int getFileSize() {
		return fileSize;
	}

	public int getDataOffset() {
		return dataOffset;
	}

}
