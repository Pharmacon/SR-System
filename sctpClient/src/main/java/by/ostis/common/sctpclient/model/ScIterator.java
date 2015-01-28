package by.ostis.common.sctpclient.model;

import java.nio.ByteBuffer;
import java.util.List;

public class ScIterator implements ScParameter {

	private List<ScParameter> paremeters;

	public ScIterator registerParameter(ScParameter param) {
		paremeters.add(param);
		return this;
	}

	@Override
	public byte[] getBytes() {
		ByteBuffer tempBuffer = ByteBuffer
				.allocate(getByteSize());
		for (ScParameter parameter : paremeters) {
			tempBuffer.put(parameter.getBytes());
		}
		return tempBuffer.array();
	}

	@Override
	public int getByteSize() {
		int byteLenght = 0;
		for (ScParameter scParameter : paremeters) {
			byteLenght += scParameter.getByteSize();
		}
		return byteLenght;
	}

}
