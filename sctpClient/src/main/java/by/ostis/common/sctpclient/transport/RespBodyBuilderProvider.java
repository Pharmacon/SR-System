package by.ostis.common.sctpclient.transport;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import by.ostis.common.sctpclient.model.ScAddress;
import by.ostis.common.sctpclient.model.ScString;
import by.ostis.common.sctpclient.model.response.SctpResponseHeader;
import by.ostis.common.sctpclient.model.response.SctpResultType;
import by.ostis.common.sctpclient.utils.constants.ScElementType;
import by.ostis.common.sctpclient.utils.constants.ScParameterSize;
import by.ostis.common.sctpclient.utils.constants.SctpCommandType;

final class RespBodyBuilderProvider {

	private class AddressWhenSuccessBuilder implements RespBodyBuilder {

		@Override
		public List<Object> getBody(final byte[] bytes,
				final SctpResponseHeader responseHeader) {
			final List<Object> list = new ArrayList<Object>();
			final SctpResultType resultType = responseHeader.getResultType();
			if (SctpResultType.SCTP_RESULT_OK == resultType) {
				final ScAddress scAddress = TypeBuilder.buildScAddress(bytes);
				list.add(scAddress);
			}
			return list;
		}

	}

	class ElementTypeBuilder implements RespBodyBuilder {

		@Override
		public List<Object> getBody(final byte[] bytes,
				final SctpResponseHeader responseHeader) {
			final List<Object> body = new ArrayList<>();
			final SctpResultType resultType = responseHeader.getResultType();
			if (resultType.equals(SctpResultType.SCTP_RESULT_OK)) {
				final byte[] elementCode = Arrays.copyOf(bytes,
						ScElementType.SC_ELEMENT_BYTE_SIZE);
				final short code = ByteBuffer.wrap(elementCode)
						.order(ByteOrder.LITTLE_ENDIAN).getShort();
				for (final ScElementType elementType : ScElementType.values()) {
					if (code == elementType.getValue()) {
						body.add(elementType);
					}
				}
			}
			return body;
		}

	}

	class EmptyResponseBodyBuider implements RespBodyBuilder {

		@Override
		public List<Object> getBody(final byte[] bytes,
				final SctpResponseHeader responseHeader) {
			return new ArrayList<Object>();
		}

	}

	class FindLinksBuilder implements RespBodyBuilder {

		private static final int LINKS_NUMBER_END_INDEX = 4;
		private static final int LINKS_NUMBER_BEGIN_INDEX = 0;

		private static final int LINKS_ADDRESSES_BEGIN_INDEX = 4;

		@Override
		public List<Object> getBody(final byte[] bytes,
				final SctpResponseHeader responseHeader) {
			final List<Object> list = new ArrayList<Object>();
			final SctpResultType resultType = responseHeader.getResultType();
			if (SctpResultType.SCTP_RESULT_OK == resultType) {
				final int linksNumber = getLinkNumbers(bytes);
				final Collection<ScAddress> addresses = getAddresses(bytes,
						linksNumber);
				list.addAll(addresses);
			}
			return list;
		}

		private int getLinkNumbers(final byte[] bytes) {
			final ByteBuffer byteBuffer = ByteBuffer.wrap(bytes,
					LINKS_NUMBER_BEGIN_INDEX, LINKS_NUMBER_END_INDEX);
			byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
			return byteBuffer.getInt();
		}

		private Collection<ScAddress> getAddresses(final byte[] bytes,
				final int number) {
			final List<ScAddress> list = new LinkedList<ScAddress>();
			int beginIndex = LINKS_ADDRESSES_BEGIN_INDEX;
			for (int i = 0; i < number; i++) {
				final ScAddress address = TypeBuilder.buildScAddress(bytes,
						beginIndex);
				list.add(address);
				beginIndex += ScParameterSize.SC_ADDRESS.getSize();
			}
			return list;
		}

	}

	class GetArcBuilder implements RespBodyBuilder {

		// private static final int END_ADDRESS_END_INDEX = 8;
		// private static final int END_ADDRESS_BEGIN_INDEX = 4;

		@Override
		public List<Object> getBody(final byte[] bytes,
				final SctpResponseHeader responseHeader) {
			final List<Object> list = new ArrayList<Object>();
			final ScAddress begin = TypeBuilder.buildScAddress(bytes);
			/*
			 * byte[] endBytes = Arrays.copyOfRange(bytes,
			 * END_ADDRESS_BEGIN_INDEX, END_ADDRESS_END_INDEX);
			 */
			final ScAddress end = TypeBuilder.buildScAddress(bytes);
			list.add(begin);
			list.add(end);
			return list;
		}

	}

	class IdWhenSuccessBuilder implements RespBodyBuilder {

		@Override
		public List<Object> getBody(final byte[] bytes,
				final SctpResponseHeader responseHeader) {
			final List<Object> list = new ArrayList<Object>();
			final SctpResultType resultType = responseHeader.getResultType();
			if (SctpResultType.SCTP_RESULT_OK == resultType) {
				final ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
				byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
				final int id = byteBuffer.getInt();
				list.add(id);
			}
			return list;
		}

	}

	class LinkContentBuilder implements RespBodyBuilder {

		@Override
		public List<Object> getBody(final byte[] bytes,
				final SctpResponseHeader responseHeader) {
			final List<Object> list = new ArrayList<Object>();
			final ScString content = new ScString(bytes.length, new String(
					bytes));
			list.add(content);
			return list;
		}

	}

	RespBodyBuilderProvider() {
		super();
	}

	public RespBodyBuilder create(final SctpCommandType command) {

		switch (command) {
		case CHECK_ELEMENT_CMD:
			return new EmptyResponseBodyBuider();
		case GET_ELEMENT_TYPE_CMD:
			return new ElementTypeBuilder();
		case ERASE_ELEMENT_CMD:
			return new EmptyResponseBodyBuider();
		case CREATE_NODE_CMD:
			return new AddressWhenSuccessBuilder();
		case CREATE_LINK_CMD:
			return new AddressWhenSuccessBuilder();
		case CREATE_ARC_CMD:
			return new AddressWhenSuccessBuilder();
		case GET_ARC_CMD:
			return new GetArcBuilder();
		case GET_LINK_CONTENT_CMD:
			return new LinkContentBuilder();
		case FIND_LINKS_CMD:
			return new FindLinksBuilder();
		case SET_LINK_CONTENT_CMD:
			return new EmptyResponseBodyBuider();
		case EVENT_CREATE_CMD:
			return new IdWhenSuccessBuilder();
		case EVENT_DESTROY_CMD:
			return new IdWhenSuccessBuilder();
		case FIND_ELEMENT_BY_SYSIDTF_CMD:
			return new AddressWhenSuccessBuilder();
		case SET_SYSIDTF_CMD:
			return new EmptyResponseBodyBuider();
		default:
			// TODO: 0x02 Ask ElementTypes and add to ScElementType enum
			// TODO: 0x08 - underfined on sc-machine wiki
			// TODO: 0x0c
			// TODO: 0x0d
			// TODO: 0x10
			// TODO:0xa2
			// TODO:0xa3 ask version encoding
			throw new IllegalArgumentException("Not support command=" + command);
		}
	}

}
