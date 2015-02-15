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
    public String toString(){
        return content;
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ScString other = (ScString) obj;
        if (content == null) {
            if (other.content != null)
                return false;
        } else if (!content.equals(other.content))
            return false;
        return true;
    }
}
