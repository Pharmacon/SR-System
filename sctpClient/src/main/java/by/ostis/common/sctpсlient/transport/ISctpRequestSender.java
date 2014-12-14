package by.ostis.common.sctpсlient.transport;

import by.ostis.common.sctpсlient.model.SctpResponse;
import by.ostis.common.sctpсlient.model.request.SctpRequest;

public interface ISctpRequestSender {
public void init(String host,int port);
public void shutdown();
public SctpResponse sendRequest(SctpRequest request);
}
