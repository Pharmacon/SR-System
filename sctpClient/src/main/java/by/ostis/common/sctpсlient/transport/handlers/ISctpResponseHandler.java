package by.ostis.common.sctpсlient.transport.handlers;

import by.ostis.common.sctpсlient.model.request.SctpResponse;

public interface ISctpResponseHandler {
public SctpResponse handleResponse(byte[] response);
}
