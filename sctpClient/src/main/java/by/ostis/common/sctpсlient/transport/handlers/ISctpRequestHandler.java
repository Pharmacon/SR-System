package by.ostis.common.sctpсlient.transport.handlers;

import by.ostis.common.sctpсlient.model.request.SctpRequest;

public interface ISctpRequestHandler {
public byte[] handleRequest(SctpRequest request);
}
