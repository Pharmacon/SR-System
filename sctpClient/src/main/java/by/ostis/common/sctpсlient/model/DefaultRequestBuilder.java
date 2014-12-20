package by.ostis.common.sctpсlient.model;

import by.ostis.common.sctpсlient.model.request.RequestHeaderType;
import by.ostis.common.sctpсlient.model.request.SctpRequest;
import by.ostis.common.sctpсlient.model.request.SctpRequestBody;
import by.ostis.common.sctpсlient.model.request.SctpRequestHeader;

public class DefaultRequestBuilder implements RequestBuilder {

	@Override
	public SctpRequest buildRequest(RequestHeaderType headerType,
			ScParameter... parameters) {

		SctpRequest newRequest = new SctpRequest();
		SctpRequestBody requestBody = new SctpRequestBody();
		requestBody.addParameters(parameters);
		SctpRequestHeader requestHeader = headerType.getRequestHeader();
		requestHeader.setArgumentSize(requestBody.getSize());
		newRequest.setHeader(requestHeader);
		
		return newRequest;
	}

}
