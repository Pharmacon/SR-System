package by.ostis.common.sctpclient.model;

import java.nio.ByteBuffer;

import by.ostis.common.sctpclient.utils.constants.ScElementType;

public class ScElemTypeParam implements ScParameter {
	private ScElementType type;

	public ScElemTypeParam(final ScElementType type) {
		this.type = type;
	}

	@Override
	public byte[] getBytes() {
		ByteBuffer buffer = ByteBuffer
				.allocate(ScElementType.SC_ELEMENT_BYTE_SIZE);
		buffer.putShort(type.getValue());
		return buffer.array();
	}

	@Override
	public int getSize() {
		return ScElementType.SC_ELEMENT_BYTE_SIZE;
	}

}
