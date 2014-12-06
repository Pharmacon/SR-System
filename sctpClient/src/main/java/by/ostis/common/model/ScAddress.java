package by.ostis.common.model;

public class ScAddress {
	private short segment;
	private short offset;
	
	public ScAddress(short segment, short offset) {
		super();
		this.segment = segment;
		this.offset = offset;
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

	
}
