package by.ostis.common.sctpclient.client;

import by.ostis.common.sctpclient.exception.InitializationException;
import by.ostis.common.sctpclient.exception.ShutdownException;
import by.ostis.common.sctpclient.exception.TransportException;
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
		try {
			sender.init(host, port);
		} catch (InitializationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void shutdown() {
		try {
			sender.shutdown();
		} catch (ShutdownException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ScRefContent getScLinkContent(ScAddress adr) {
		return null;
	}

	public ScElementType checkElement(ScAddress address) {
		SctpResponse response = new SctpResponse();
		try {
			response = sender.sendRequest(requestBuilder.buildRequest(
					RequestHeaderType.CHECK_ELEMENT_COMMAND, address));
		} catch (TransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (ScElementType) response.getBody().get(0);
	}

}
