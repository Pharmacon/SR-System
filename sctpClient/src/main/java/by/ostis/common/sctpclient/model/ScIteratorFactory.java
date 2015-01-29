package by.ostis.common.sctpclient.model;

import java.util.List;

public class ScIteratorFactory {
    public static ScIterator create3FAA(ScParameter scAddress, ScParameter scElementTypeSecond,
	    ScParameter scElementType) {
	return new ScIterator().registerParameter(scAddress).registerParameter(scElementTypeSecond)
		.registerParameter(scElementType);
    }

    public static ScIterator create3FAA(List<ScParameter> params) {
	ScIterator newScIterator = new ScIterator();
	for (ScParameter scParameter : params) {
	    newScIterator.registerParameter(scParameter);
	}
	return newScIterator;
    }
}
