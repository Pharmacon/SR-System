package by.ostis.common.sctpclient.model.request;

import java.util.ArrayList;
import java.util.List;

import by.ostis.common.sctpclient.model.ScParameter;

public class SctpRequestBody {

    private List<ScParameter> body;

    public SctpRequestBody() {
	body = new ArrayList<ScParameter>();
    }

    public List<ScParameter> getParameterList() {
	return new ArrayList<ScParameter>(body);
    }

    public void addParameter(ScParameter parameter) {
	body.add(parameter);
    }

    public void addParameters(ScParameter... parameters) {
	for (ScParameter scParameter : parameters) {
	    body.add(scParameter);
	}
    }

    public int getByteLenght() {
	int byteLenght = 0;
	for (ScParameter scParameter : body) {
	    byteLenght += scParameter.getByteSize();
	}
	return byteLenght;
    }

    public int getSize() {
	return body.size();
    }

}
