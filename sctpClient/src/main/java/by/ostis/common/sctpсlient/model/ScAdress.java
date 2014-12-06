package by.ostis.common.sctpсlient.model;

public class ScAdress {
	private short segment;
	private short offset;
	public ScAdress(short segment,short offset){
		this.segment=segment;
		this.offset=offset;
	}
	public int getSegment() {
		return segment;
	}
	public void setSegment(short segment) {
		this.segment = segment;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(short offset) {
		this.offset = offset;
	}
}
