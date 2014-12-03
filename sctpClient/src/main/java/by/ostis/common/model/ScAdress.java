package by.ostis.common.model;

public class ScAdress {
	private int segment;
	private int offset;
	public ScAdress(int segment,int offset){
		this.segment=segment;
		this.offset=offset;
	}
	public int getSegment() {
		return segment;
	}
	public void setSegment(int segment) {
		this.segment = segment;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
}
