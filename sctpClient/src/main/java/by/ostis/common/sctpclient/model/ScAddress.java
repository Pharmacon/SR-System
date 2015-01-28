package by.ostis.common.sctpclient.model;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import by.ostis.common.sctpclient.utils.constants.ScParameterSize;

public class ScAddress implements ScParameter{
	
	
	private short segment;
	private short offset;

	public ScAddress(short segment, short offset) {
		this.segment = segment;
		this.offset = offset;
	}
	
	public ScAddress(int segment, int offset) {
		this((short)segment, (short)offset);

	}

	public short getSegment() {
		return segment;
	}

	public void setSegment(short segment) {
		this.segment = segment;
	}

	public short getOffset() {
		return offset;
	}

	public void setOffset(short offset) {
		this.offset = offset;
	}

	@Override
	public byte[] getBytes() {
		ByteBuffer tempBuffer = ByteBuffer.allocate(ScParameterSize.SC_ADDRESS.getSize());
		tempBuffer.order(ByteOrder.LITTLE_ENDIAN);
		tempBuffer.putShort(segment);
		tempBuffer.putShort(offset);
		return tempBuffer.array();
	}

	@Override
	public int getByteSize() {
		return ScParameterSize.SC_ADDRESS.getSize();
	}
}
