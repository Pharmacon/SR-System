package by.ostis.common.sctpclient.model.request;

import by.ostis.common.sctpclient.model.ScParameter;

public class SctpRequest {

	private SctpRequestHeader header;
	
	private SctpRequestBody body;

	public SctpRequestHeader getHeader() {
		return header;
	}

	public void setHeader(SctpRequestHeader header) {
		this.header = header;
	}

//	public List<ScParameter> getBody() {
//		return body;
//	}
	
	
	
	public void addParametersToBody(ScParameter ...parameters){
		body.addParameters(parameters);
	}
	
	public SctpRequestBody getBody() {
		return body;
	}

	public void setBody(SctpRequestBody body) {
		this.body = body;
	}

	public void addParameterToBody(ScParameter parameter) {
		body.addParameter(parameter);
	}
}
