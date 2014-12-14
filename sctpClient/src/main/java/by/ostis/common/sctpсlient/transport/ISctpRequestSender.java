package by.ostis.common.sctpсlient.transport;

import by.ostis.common.sctpсlient.model.request.SctpRequest;
import by.ostis.common.sctpсlient.model.request.SctpResponse;

public interface ISctpRequestSender {
public void init(String host,int port);
public void shutdown();
public SctpResponse sendRequest(SctpRequest request);
}
