package by.ostis.common.sctpclient.utils.constants;

public enum ScElementType {
	SC_TYPE_NODE((short) 0x1), SC_TYPE_LINK((short) 0x2), SC_TYPE_EDGE_COMMON(
			(short) 0x4), SC_TYPE_ARC_COMMON((short) 0x8), SC_TYPE_ARC_ACCESS(
			(short) 0x10);
	private short value;
	public static int SC_ELEMENT_BYTE_SIZE = 2;

	private ScElementType(short value) {
		this.value = value;
	}

	public short getValue() {
		return value;
	}

}
