package by.ostis.common.sctpclient.model.response;

public enum ScElementType {
	
	;
	private byte[] type;

	private ScElementType(byte[] type) {
		this.type = type;
	}

	public byte[] getType() {
		return type;
	}
	
	public static final int SC_ELEMENT_BYTE_SIZE=2;
	
}
