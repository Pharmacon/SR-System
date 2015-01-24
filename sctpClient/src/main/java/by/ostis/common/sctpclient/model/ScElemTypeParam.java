package by.ostis.common.sctpclient.model;

import by.ostis.common.sctpclient.utils.constants.ScArcType;
import by.ostis.common.sctpclient.utils.constants.ScElementType;

public class ScElemTypeParam implements ScParameter {
	private ScElementType type;
	public ScElemTypeParam(ScElementType type){
		this.type = type;
	}
	@Override
	public byte[] getBytes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

}
