package by.ostis.common.sctpсlient.utils.constants;

public enum ScParameterSize {
	SC_ADDRESS(4);
	
	private int size;

	public int getSize() {
		return size;
	}

	private ScParameterSize(int size) {
		this.size = size;
	}
	
	

}
