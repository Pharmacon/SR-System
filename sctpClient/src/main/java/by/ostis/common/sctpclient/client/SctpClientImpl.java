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
import by.ostis.common.sctpclient.model.ScArcTypeParam;
import by.ostis.common.sctpclient.model.ScElemTypeParam;
import by.ostis.common.sctpclient.model.ScString;
import by.ostis.common.sctpclient.model.request.RequestHeaderType;
import by.ostis.common.sctpclient.model.request.SctpRequest;
import by.ostis.common.sctpclient.model.response.SctpResponse;
import by.ostis.common.sctpclient.transport.SctpRequestSender;
import by.ostis.common.sctpclient.transport.SctpRequestSenderImpl;
import by.ostis.common.sctpclient.utils.constants.IteratorType;
import by.ostis.common.sctpclient.utils.constants.ScArcType;
import by.ostis.common.sctpclient.utils.constants.ScElementType;

public class SctpClientImpl implements SctpClient {

	private SctpRequestSender sender = new SctpRequestSenderImpl();
	private RequestBuilder requestBuilder = new DefaultRequestBuilder();

	@Override
	public void init(final String host, final int port) {
		try {
			this.sender.init(host, port);
		} catch (final InitializationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void shutdown() {
		try {
			this.sender.shutdown();
		} catch (final ShutdownException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ScString getScLinkContent(final ScAddress adr) {
		return null;
	}

	@Override
	public SctpResponse checkElementExistence(final ScAddress address) {
		SctpResponse response = new SctpResponse();
		try {
			response = this.sender.sendRequest(this.requestBuilder
					.buildRequest(RequestHeaderType.CHECK_ELEMENT, address));
		} catch (final TransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public SctpResponse searchElement(final ScString identifier) {
		SctpResponse response = new SctpResponse();
		try {
			response = sender
					.sendRequest(requestBuilder.buildRequest(
							RequestHeaderType.SEARCH_ELEMENT_BY_IDENTIFIER,
							identifier));
		} catch (final TransportException e) {
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

	public SctpResponse deleteElement(final ScAddress address) {
		SctpResponse response = new SctpResponse();
		try {
			// TODO need to test
			response = this.sender.sendRequest(this.requestBuilder
					.buildRequest(RequestHeaderType.DELETE_ELEMENT, address));
		} catch (final TransportException e) {
			// TODO handle exception
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public SctpResponse createElement(final ScElementType type) {
		SctpResponse response = new SctpResponse();
		try {
			// TODO need to test
			response = this.sender.sendRequest(this.requestBuilder
					.buildRequest(RequestHeaderType.CREATE_ELEMENT,
							new ScElemTypeParam(type)));
		} catch (final TransportException e) {
			// TODO handle exception
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public SctpResponse createScLink() {
		SctpResponse response = new SctpResponse();
		try {
			// TODO need to test
			response = this.sender.sendRequest(this.requestBuilder
					.buildRequest(RequestHeaderType.CREATE_SC_LINK));
		} catch (final TransportException e) {
			// TODO handle exception
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public SctpResponse createScArc(final ScArcType type,
			final ScAddress begAddress, final ScAddress endAddress) {
		SctpResponse response = new SctpResponse();
		try {
			response = this.sender.sendRequest(this.requestBuilder
					.buildRequest(RequestHeaderType.CREATE_SC_ARC,
							new ScArcTypeParam(type), begAddress, endAddress));
		} catch (TransportException e) {
			// TODO handle exception
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public SctpResponse getArcBeginAndEnd(final ScAddress arcAddress) {
		SctpResponse response = new SctpResponse();
		try {
			// TODO need to test
			response = this.sender.sendRequest(this.requestBuilder
					.buildRequest(RequestHeaderType.FIND_ARC_BEGIN_AND_END,
							arcAddress));
		} catch (final TransportException e) {
			// TODO handle exception
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public SctpResponse searchScLinks(final ScString content) {
		SctpResponse response = new SctpResponse();
		try {
			// TODO need to test
			response = this.sender.sendRequest(this.requestBuilder
					.buildRequest(RequestHeaderType.SEARCH_SC_LINKS, content));
		} catch (final TransportException e) {
			// TODO handle exception
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public SctpResponse setScRefContent(final ScAddress address,
			final ScString content) {
		SctpResponse response = new SctpResponse();
		try {
			// TODO need to test
			response = this.sender.sendRequest(this.requestBuilder
					.buildRequest(RequestHeaderType.SET_SC_LINK_CONTENT,
							address, content));
		} catch (final TransportException e) {
			// TODO handle exception
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public SctpResponse setSystemIdentifier(final ScAddress address,
			final ScString identifier) {
		SctpResponse response = new SctpResponse();
		try {
			// TODO need to test
			response = this.sender.sendRequest(this.requestBuilder
					.buildRequest(RequestHeaderType.SET_SYSIDTF, address,
							identifier));
		} catch (final TransportException e) {
			// TODO handle exception
			e.printStackTrace();
		}
		return response;
	}
}
