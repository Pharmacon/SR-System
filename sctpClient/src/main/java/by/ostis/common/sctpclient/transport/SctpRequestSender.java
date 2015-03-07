package by.ostis.common.sctpclient.transport;

import by.ostis.common.sctpclient.exception.InitializationException;
import by.ostis.common.sctpclient.exception.ShutdownException;
import by.ostis.common.sctpclient.exception.TransportException;
import by.ostis.common.sctpclient.model.request.SctpRequest;
import by.ostis.common.sctpclient.model.response.SctpResponse;

public interface SctpRequestSender {

	void init(String host, int port) throws InitializationException;

	void shutdown() throws ShutdownException;

	<Type> SctpResponse<Type> sendRequest(SctpRequest request) throws TransportException;
}
