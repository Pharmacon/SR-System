package by.ostis.common.sctpclient.model;

import by.ostis.common.sctpclient.model.request.RequestHeaderType;
import by.ostis.common.sctpclient.model.request.SctpRequest;

public interface RequestBuilder {

	public SctpRequest buildRequest(RequestHeaderType requestHeaderType,
			ScParameter... parameters);

}
