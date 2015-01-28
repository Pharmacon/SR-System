package by.ostis.common.sctpclient.utils.constants;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import by.ostis.common.sctpclient.model.ScParameter;

public enum ScElementType implements ScParameter{
	SC_TYPE_NODE(0x1), 
	SC_TYPE_LINK(0x2), 
	SC_TYPE_EDGE_COMMON(0x4), 
	SC_TYPE_ARC_COMMON(0x8), 
	SC_TYPE_ARC_ACCESS(0x10),
	
	SC_TYPE_CONST(0x20),
	SC_TYPE_VAR(0x40),
	
	SC_TYPE_ARC_POS(0x80),
	SC_TYPE_ARC_NEG(0x100),
	SC_TYPE_ARC_FUZ(0x200),
	
	SC_TYPE_ARC_TEMP(0x400),
	SC_TYPE_ARC_PERM(0x800)
	;
	private short value;
	
	public static int SC_ELEMENT_TYPE_BYTE_SIZE = 2;

	private ScElementType(short value) {
		this.value = value;
	}
	
	private ScElementType(int value){
		this((short)value);
	}

	public short getValue() {
		return value;
	}

	@Override
	public byte[] getBytes() {
		ByteBuffer temp = ByteBuffer.allocate(2);
		temp.order(ByteOrder.LITTLE_ENDIAN);
		temp.putShort(value);
		return temp.array();
	}

	@Override
	public int getByteSize() {
		return SC_ELEMENT_TYPE_BYTE_SIZE;
	}

}
