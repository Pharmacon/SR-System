package by.ostis.common.sctpclient.model;

import by.ostis.common.sctpclient.utils.constants.ScArcType;

public class ScArcTypeParam implements ScParameter {
	private ScArcType type;

	public ScArcTypeParam(final ScArcType type) {
		this.type = type;
	}

	@Override
	public byte[] getBytes() {
		// TODO need to implement
		return null;
	}

	@Override
	public int getSize() {
		return ScArcType.SC_ARC_TYPE_BYTE_SIZE;
	}

}
