package by.ostis.common.sctpclient.model;

import java.nio.ByteBuffer;

import by.ostis.common.sctpclient.utils.constants.ScArcType;

public class ScArcTypeParam implements ScParameter {
    private ScArcType type;

    public ScArcTypeParam(final ScArcType type) {
	this.type = type;
    }

    @Override
    public byte[] getBytes() {
	ByteBuffer buffer = ByteBuffer.allocate(ScArcType.SC_ARC_TYPE_BYTE_SIZE);
	buffer.putShort(type.getValue());
	return buffer.array();
    }

    @Override
    public int getByteSize() {
	return ScArcType.SC_ARC_TYPE_BYTE_SIZE;
    }

}
