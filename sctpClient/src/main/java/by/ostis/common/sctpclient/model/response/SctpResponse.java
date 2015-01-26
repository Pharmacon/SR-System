package by.ostis.common.sctpclient.model.response;

import java.util.ArrayList;
import java.util.List;

public class SctpResponse {

	private SctpResponseHeader header;
	private List<Object> body = new ArrayList<>();

	public void addParameterToBody(Object param) {
		body.add(param);
	}

	public List<Object> getBody() {
		return body;
	}

	public SctpResponseHeader getHeader() {
		return header;
	}

	public void setHeader(SctpResponseHeader header) {
		this.header = header;
	}

	public void setBody(List<Object> body) {
		this.body = body;
	}

}
