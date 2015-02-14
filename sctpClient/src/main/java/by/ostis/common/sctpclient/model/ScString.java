package by.ostis.common.sctpclient.model;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import by.ostis.common.sctpclient.utils.constants.ScParameterSize;

public class ScString implements ScParameter {

    private String content;

    public String getContent() {
	return content;
    }

    public void setContent(String content) {
	this.content = content;
    }

    public ScString(String content) {
	this.content = content;
    }

    @Override
    public byte[] getBytes() {
	ByteBuffer capacityBuffer = ByteBuffer.allocate(ScParameterSize.SC_CAPACITY.getSize());
	capacityBuffer.order(ByteOrder.LITTLE_ENDIAN);
	int capacity = content.getBytes().length;
	capacityBuffer.putInt(capacity);
	ByteBuffer generalBuffer = ByteBuffer.allocate(ScParameterSize.SC_CAPACITY.getSize() + capacity);
	generalBuffer.put(capacityBuffer.array());
	generalBuffer.put(content.getBytes());
	return generalBuffer.array();
    }

    @Override
    public int getByteSize() {
	return content.getBytes().length;
    }

}
