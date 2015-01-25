package by.ostis.common.sctpclient.model;

import by.ostis.common.sctpclient.utils.constants.ScElementType;

public class ScElemTypeParam implements ScParameter {
	private ScElementType type;

	public ScElemTypeParam(final ScElementType type) {
		this.type = type;
	}

	@Override
	public byte[] getBytes() {
		// TODO need to implement
		return null;
	}

	@Override
	public int getSize() {
		return ScElementType.SC_ELEMENT_BYTE_SIZE;
	}

}
