package by.ostis.common.sctpclient.transport.handlers;

import by.ostis.common.sctpclient.model.response.SctpResponse;

public interface SctpResponseHandler {
public SctpResponse handleResponse(byte[] response);
}
