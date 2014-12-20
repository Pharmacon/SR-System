package by.ostis.common.sctpclient.transport;

import by.ostis.common.sctpclient.model.request.SctpRequest;
import by.ostis.common.sctpclient.model.response.SctpResponse;

public interface ISctpRequestSender {
public void init(String host,int port);
public void shutdown();
public SctpResponse sendRequest(SctpRequest request);
}
