package by.ostis.common.sctpсlient.transport.handlers;

import by.ostis.common.sctpсlient.model.SctpResponse;

public interface ISctpResponseHandler {
public SctpResponse handleResponse(byte[] response);
}
