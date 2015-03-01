package by.ostis.common.sctpclient.model;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import by.ostis.common.sctpclient.utils.constants.ScParameterSize;

public class ScString implements ScParameter {

	private String content;
	private int capacity;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public ScString(int capacity, String content) {
		this.capacity = capacity;
		this.content = content;
	}

	@Override
	public byte[] getBytes() {
		ByteBuffer capacityBuffer = ByteBuffer
				.allocate(ScParameterSize.SC_CAPACITY.getSize());
		capacityBuffer.order(ByteOrder.LITTLE_ENDIAN);
		capacityBuffer.putInt(capacity);
		ByteBuffer generalBuffer = ByteBuffer
				.allocate(ScParameterSize.SC_CAPACITY.getSize() + capacity);
		generalBuffer.put(capacityBuffer.array());
		generalBuffer.put(content.getBytes());
		return generalBuffer.array();
	}

	@Override
	public int getByteSize() {
		return capacity;
	}

}
