package by.ostis.common.sctpclient.client;

import java.util.List;

import by.ostis.common.sctpclient.exception.InitializationException;
import by.ostis.common.sctpclient.exception.ShutdownException;
import by.ostis.common.sctpclient.exception.TransportException;
import by.ostis.common.sctpclient.model.ScAddress;
import by.ostis.common.sctpclient.model.ScIterator;
import by.ostis.common.sctpclient.model.ScParameter;
import by.ostis.common.sctpclient.model.ScString;
import by.ostis.common.sctpclient.model.request.RequestHeaderType;
import by.ostis.common.sctpclient.model.request.SctpRequest;
import by.ostis.common.sctpclient.model.response.SctpResponse;
import by.ostis.common.sctpclient.transport.SctpRequestSender;
import by.ostis.common.sctpclient.transport.SctpRequestSenderImpl;
import by.ostis.common.sctpclient.utils.constants.ScElementType;
import by.ostis.common.sctpclient.utils.constants.ScIteratorType;
import by.ostis.common.sctpclient.utils.constants.SctpCommandType;

public class SctpClientImpl implements SctpClient {

	private SctpRequestSender sender = new SctpRequestSenderImpl();
	private static final ScParameter[] NO_PARAMETERS = {};
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
	public SctpResponse<String> getScLinkContent(final ScAddress address) {
		return sendRequest(SctpCommandType.GET_LINK_CONTENT_COMMAND, address);
	}

	@Override
	public SctpResponse<Boolean> checkElementExistence(final ScAddress address) {
		return sendRequest(SctpCommandType.CHECK_ELEMENT_COMMAND, address);
	}

	@Override
	public SctpResponse<ScAddress> searchElement(final ScString identifier) {
		return sendRequest(SctpCommandType.FIND_ELEMENT_BY_SYSIDTF_COMMAND,
				identifier);
	}

	public SctpResponse<Boolean> deleteElement(final ScAddress address) {
		return sendRequest(SctpCommandType.ERASE_ELEMENT_COMMAND, address);
	}

	@Override
	public SctpResponse<ScAddress> createElement(final ScElementType type) {
		return sendRequest(SctpCommandType.CHECK_ELEMENT_COMMAND, type);
	}

	@Override
	public SctpResponse<ScAddress> createScLink() {
		return sendRequest(SctpCommandType.CREATE_LINK_COMMAND, NO_PARAMETERS);
	}

	@Override
	public SctpResponse<ScAddress> createScArc(final ScElementType type,
			final ScAddress begAddress, final ScAddress endAddress) {
		return sendRequest(SctpCommandType.CREATE_ARC_COMMAND, begAddress,
				endAddress);
	}

	// TODO
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
	public SctpResponse<List<ScAddress>> searchScLinks(final ScString content) {
		return sendRequest(SctpCommandType.FIND_LINKS_COMMAND, content);
	}

	@Override
	public SctpResponse<Boolean> setScRefContent(final ScAddress address,
			final ScString content) {
		return sendRequest(SctpCommandType.SET_LINK_CONTENT_COMMAND, address,
				content);
	}

	@Override
	public SctpResponse<Boolean> setSystemIdentifier(final ScAddress address,
			final ScString identifier) {
		return sendRequest(SctpCommandType.SET_SYSIDTF_COMMAND, address,
				identifier);
	}

	@Override
	public SctpResponse<List<ScIterator>> searchByIterator(
			ScIteratorType iteratorType, List<ScParameter> params) {
		ScIterator iterator = new ScIterator();
		switch (iteratorType) {

		case SCTP_ITERATOR_3F_A_A:
			// iterator = ScIteratorFactory.create3FAA(scAddressFirst,
			// scElementTypeSecond, scElementTypeThird);
			break;

		case SCTP_ITERATOR_3F_A_F:
			// iterator = ScIteratorFactory.create3FAA(scAddressFirst,
			// scElementTypeSecond, scElementTypeThird);
			break;
		case SCTP_ITERATOR_3_A_A_F:
			// iterator = ScIteratorFactory.create3AAF(scElementTypeFirst,
			// scElementTypeSecond, scAddress);
			break;
		case SCTP_ITERATOR_5F_A_A_A_F:
			// iterator = ScIteratorFactory.create5FAAAF(scAddressFirst,
			// scElementTypeSecond, scElementTypeThird, scElementTypeForth,
			// scAddressFifth);
			break;
		case SCTP_ITERATOR_5_A_A_F_A_A:
			// iterator = ScIteratorFactory.create5AAFAA(scElementTypeFirst,
			// scElementTypeSecond, scAddressThird, scElementTypeForth,
			// scElementTypeFifth);
			break;
		case SCTP_ITERATOR_5_A_A_F_A_F:
			// iterator = ScIteratorFactory.create5AAFAF(scElementTypeFirst,
			// scElementTypeSecond, scAddressThird, scElementTypeForth,
			// scAddressFifth);
			break;
		case SCTP_ITERATOR_5_F_A_A_A_A:
			// iterator = ScIteratorFactory.create5FAAAA(scAddressFirst,
			// scElementTypeSecond, scElementThird, scElementTypeForth,
			// scElementTypeFifth);
			break;
		case SCTP_ITERATOR_5_F_A_F_A_A:
			// iterator = ScIteratorFactory.create5FAFAA(scAddressFirst,
			// scElementTypeSecond, scAddressThird, scElementTypeForth,
			// scElementTypeFifth);
			break;
		case SCTP_ITERATOR_5_F_A_F_A_F:
			// iterator = ScIteratorFactory.create5FAFAF(scAddressFirst,
			// scElementTypeSecond, scAddressThird, scElementTypeForth,
			// scAddressFifth);
			break;
		default:
			break;

		}
		return sendRequest(SctpCommandType.ITERATE_ELEMENTS_COMMAND,
				iteratorType, iterator);
	}
	
	private <T> SctpResponse<T> sendRequest(SctpCommandType sctpCommandType,
			ScParameter... parameters) {
		SctpResponse<T> response = new SctpResponse<T>();
		RequestHeaderType requestHeaderType = RequestHeaderType
				.getByCommandId(sctpCommandType);
		try {
			SctpRequest request = this.requestBuilder.buildRequest(
					requestHeaderType, parameters);
			response = this.sender.sendRequest(request);
		} catch (final TransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

}
