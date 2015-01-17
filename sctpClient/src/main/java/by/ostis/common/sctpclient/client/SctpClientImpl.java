package by.ostis.common.sctpclient.client;

import by.ostis.common.sctpclient.model.DefaultRequestBuilder;
import by.ostis.common.sctpclient.model.RequestBuilder;
import by.ostis.common.sctpclient.model.ScAddress;
import by.ostis.common.sctpclient.model.ScRefContent;
import by.ostis.common.sctpclient.model.request.RequestHeaderType;
import by.ostis.common.sctpclient.model.response.SctpResponse;
import by.ostis.common.sctpclient.transport.SctpRequestSender;
import by.ostis.common.sctpclient.transport.SctpRequestSenderImpl;
import by.ostis.common.sctpclient.utils.constants.ScElementType;

public class SctpClientImpl implements SctpClient {

	private SctpRequestSender sender = new SctpRequestSenderImpl();
	private RequestBuilder requestBuilder = new DefaultRequestBuilder();

	@Override
	public void init(String host, int port) {
		sender.init(host, port);
	}

	@Override
	public void shutdown() {
		sender.shutdown();
	}

	public ScRefContent getScLinkContent(ScAddress adr) {
		return null;
	}

	public ScElementType checkElement(ScAddress address) {
		SctpResponse response = sender.sendRequest(requestBuilder.buildRequest(
				RequestHeaderType.CHECK_ELEMENT_COMMAND, address));
		return (ScElementType) response.getBody().get(0);
	}

}
