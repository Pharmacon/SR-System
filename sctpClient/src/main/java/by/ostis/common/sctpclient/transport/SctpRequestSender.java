package by.ostis.common.sctpclient.transport;

import by.ostis.common.sctpclient.model.request.SctpRequest;
import by.ostis.common.sctpclient.model.response.SctpResponse;

public interface SctpRequestSender {
	void init(String host, int port);

	void shutdown();

	SctpResponse sendRequest(SctpRequest request);
}
