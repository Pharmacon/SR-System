package by.ostis.common.sctpclient.model;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import by.ostis.common.sctpclient.utils.constants.ScParameterSize;

public class ScAddress implements ScParameter {

    private short segment;
    private short offset;

    public ScAddress(short segment, short offset) {
	this.segment = segment;
	this.offset = offset;
    }

    public ScAddress(int segment, int offset) {
	this((short) segment, (short) offset);

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
    public String toString(){
        return "segment:"+this.segment+" offset:"+this.offset;
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + offset;
        result = prime * result + segment;
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
        ScAddress other = (ScAddress) obj;
        if (offset != other.offset)
            return false;
        if (segment != other.segment)
            return false;
        return true;
    }
}
