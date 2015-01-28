package by.ostis.common.sctpclient.client;

import by.ostis.common.sctpclient.model.ScParameter;
import by.ostis.common.sctpclient.model.request.RequestHeaderType;
import by.ostis.common.sctpclient.model.request.SctpRequest;

public interface RequestBuilder {

	public SctpRequest buildRequest(RequestHeaderType requestHeaderType,
			ScParameter... parameters);

}
