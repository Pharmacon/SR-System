package by.ostis.common.sctpclient.transport.handlers;

import by.ostis.common.sctpclient.model.request.SctpRequest;

public interface SctpRequestHandler {
public byte[] handleRequest(SctpRequest request);
}
