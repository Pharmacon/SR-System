package by.ostis.common.sctpclient.client;

import by.ostis.common.sctpclient.model.ScParameter;
import by.ostis.common.sctpclient.model.request.RequestHeaderType;
import by.ostis.common.sctpclient.model.request.SctpRequest;
import by.ostis.common.sctpclient.model.request.SctpRequestBody;
import by.ostis.common.sctpclient.model.request.SctpRequestHeader;

public class DefaultRequestBuilder implements RequestBuilder {

    @Override
    public SctpRequest buildRequest(RequestHeaderType headerType, ScParameter... parameters) {

        SctpRequest newRequest = new SctpRequest();
        SctpRequestBody requestBody = new SctpRequestBody();
        requestBody.addParameters(parameters);
        SctpRequestHeader requestHeader = headerType.getRequestHeader();
        requestHeader.setArgumentSize(requestBody.getByteLenght());
        newRequest.setHeader(requestHeader);
        newRequest.setBody(requestBody);

        return newRequest;
    }

}
