package com.fraune.bmp;

import java.io.IOException;
import java.io.InputStream;

public class BMPHeader implements BMPFileSection {

	private final int headerByteCount = 14;

	private final int signatureByteCount = 2;
	private final int fileSizeByteCount = 4;
	private final int reservedByteCount = 4;
	private final int dataOffsetByteCount = 4;

	private final int signatureOffset = 0;
	private final int fileSizeOffset = signatureOffset + signatureByteCount;
	private final int reservedOffset = fileSizeOffset + fileSizeByteCount;
	private final int dataOffsetOffset = reservedOffset + reservedByteCount;

	private final byte[] headerBytes = new byte[headerByteCount];

	private final byte[] signatureBytes = new byte[signatureByteCount]; // "BM"
	private final byte[] fileSizeBytes = new byte[fileSizeByteCount];
	private final byte[] reservedBytes = new byte[reservedByteCount]; // unused
	private final byte[] dataOffsetBytes = new byte[dataOffsetByteCount];

	private String signature;
	private int fileSize;
	private int dataOffset;

	public BMPHeader(InputStream inputStream) throws IOException {
		inputStream.read(headerBytes);

		System.arraycopy(headerBytes, signatureOffset, signatureBytes, 0, signatureByteCount);
		System.arraycopy(headerBytes, fileSizeOffset, fileSizeBytes, 0, fileSizeByteCount);
		System.arraycopy(headerBytes, reservedOffset, reservedBytes, 0, reservedByteCount);
		System.arraycopy(headerBytes, dataOffsetOffset, dataOffsetBytes, 0, dataOffsetByteCount);

		signature = new String(signatureBytes);
		fileSize = BMPFile.bytesToInt(fileSizeBytes);
		dataOffset = BMPFile.bytesToInt(dataOffsetBytes);
	}

	@Override
	public byte[] getAll() {
		return headerBytes;
	}

	public String getSignature() {
		return signature;
	}

	public int getFileSize() {
		return fileSize;
	}

	public byte[] getReserved() {
		return reservedBytes;
	}

	public int getDataOffset() {
		return dataOffset;
	}

}
