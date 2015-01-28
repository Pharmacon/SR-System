package by.ostis.common.sctpclient.client;

import java.util.List;

import by.ostis.common.sctpclient.exception.InitializationException;
import by.ostis.common.sctpclient.exception.ShutdownException;
import by.ostis.common.sctpclient.exception.TransportException;
import by.ostis.common.sctpclient.model.ScAddress;
import by.ostis.common.sctpclient.model.ScIterator;
import by.ostis.common.sctpclient.model.ScIteratorFactory;
import by.ostis.common.sctpclient.model.ScParameter;
import by.ostis.common.sctpclient.model.ScRefContent;
import by.ostis.common.sctpclient.model.request.RequestHeaderType;
import by.ostis.common.sctpclient.model.request.SctpRequest;
import by.ostis.common.sctpclient.model.response.SctpResponse;
import by.ostis.common.sctpclient.transport.SctpRequestSender;
import by.ostis.common.sctpclient.transport.SctpRequestSenderImpl;
import by.ostis.common.sctpclient.utils.constants.IteratorType;

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

	public SctpResponse checkElementExistence(ScAddress address) {
		SctpResponse response = new SctpResponse();
		try {
			response = sender.sendRequest(requestBuilder.buildRequest(
					RequestHeaderType.CHECK_ELEMENT_COMMAND, address));
		} catch (TransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public SctpResponse searchElement(ScRefContent identifier) {
		SctpResponse response = new SctpResponse();
		try {
			response = sender
					.sendRequest(requestBuilder.buildRequest(
							RequestHeaderType.SEARCH_ELEMENT_BY_IDENTIFIER,
							identifier));
		} catch (TransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public SctpResponse searchByIterator(IteratorType iteratorType,
			List<ScParameter> params) {

		ScIterator scIterator;
		switch (iteratorType) {
		
		case SCTP_ITERATOR_3F_A_A: {
			scIterator = ScIteratorFactory.create3FAA(params);
			break;
		}

		}
		return null;
	}

}
