package by.ostis.common.sctpclient.transport.handlers;

import by.ostis.common.sctpclient.model.response.SctpResponse;

public interface ISctpResponseHandler {
public SctpResponse handleResponse(byte[] response);
}
