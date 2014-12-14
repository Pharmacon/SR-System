package by.ostis.common.sctpсlient.model;

import by.ostis.common.sctpсlient.model.request.RequestHeaderType;
import by.ostis.common.sctpсlient.model.request.SctpRequest;

public interface RequestBuilder {

	public SctpRequest buildRequest(RequestHeaderType requestHeaderType,
			ScParameter... parameters);

}
