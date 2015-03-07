package by.ostis.common.sctpclient.transport;

import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import by.ostis.common.sctpclient.model.ScAddress;
import by.ostis.common.sctpclient.model.ScIterator;
import by.ostis.common.sctpclient.model.response.SctpResponseHeader;
import by.ostis.common.sctpclient.model.response.SctpResultType;
import by.ostis.common.sctpclient.utils.constants.ScElementType;
import by.ostis.common.sctpclient.utils.constants.ScParameterSize;
import by.ostis.common.sctpclient.utils.constants.SctpCommandType;

final class RespBodyBuilderProvider {

    private class AddressWhenSuccessBuilder implements
            RespBodyBuilder<List<ScAddress>> {

        @Override
        public List<ScAddress> getAnswer(final byte[] bytes,
                final SctpResponseHeader responseHeader) {

            final List<ScAddress> list = new ArrayList<ScAddress>();
            final SctpResultType resultType = responseHeader.getResultType();
            if (SctpResultType.SCTP_RESULT_OK == resultType) {
                final ScAddress scAddress = TypeBuilder.buildScAddress(bytes);
                list.add(scAddress);
            }
            return list;
        }

    }

    private class ElementTypeBuilder implements RespBodyBuilder<ScElementType> {

        @Override
        public ScElementType getAnswer(final byte[] bytes,
                final SctpResponseHeader responseHeader) {

            final SctpResultType resultType = responseHeader.getResultType();
            if (resultType.equals(SctpResultType.SCTP_RESULT_OK)) {
                final byte[] elementCode = Arrays.copyOf(bytes,
                        ScElementType.SC_ELEMENT_TYPE_BYTE_SIZE);
                final short code = ByteBuffer.wrap(elementCode)
                        .order(ByteOrder.LITTLE_ENDIAN).getShort();
                for (final ScElementType elementType : ScElementType.values()) {
                    if (code == elementType.getValue()) {
                        return elementType;
                    }
                }
            }
            return null;
        }

    }

    private class EmptyResponseBodyBuider implements RespBodyBuilder<Object> {

        @Override
        public Boolean getAnswer(final byte[] bytes,
                final SctpResponseHeader responseHeader) {

            final SctpResultType resultType = responseHeader.getResultType();
            return SctpResultType.SCTP_RESULT_OK == resultType;
        }

    }

    private class FindLinksBuilder implements RespBodyBuilder<List<ScAddress>> {

        private static final int LINKS_NUMBER_END_INDEX      = 4;

        private static final int LINKS_NUMBER_BEGIN_INDEX    = 0;

        private static final int LINKS_ADDRESSES_BEGIN_INDEX = 4;

        @Override
        public List<ScAddress> getAnswer(final byte[] bytes,
                final SctpResponseHeader responseHeader) {

            final List<ScAddress> list = new ArrayList<ScAddress>();
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

    private class GetArcBuilder implements RespBodyBuilder<List<ScAddress>> {

        private static final int END_ADDRESS_BEGIN_INDEX = 4;

        @Override
        public List<ScAddress> getAnswer(final byte[] bytes,
                final SctpResponseHeader responseHeader) {

            final List<ScAddress> list = new ArrayList<ScAddress>();
            final ScAddress begin = TypeBuilder.buildScAddress(bytes);
            final ScAddress end = TypeBuilder.buildScAddress(bytes,
                    END_ADDRESS_BEGIN_INDEX);
            list.add(begin);
            list.add(end);
            return list;
        }

    }

    class IdWhenSuccessBuilder implements RespBodyBuilder<Integer> {

        @Override
        public Integer getAnswer(final byte[] bytes,
                final SctpResponseHeader responseHeader) {

            final SctpResultType resultType = responseHeader.getResultType();
            if (SctpResultType.SCTP_RESULT_OK == resultType) {
                final ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
                byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
                return byteBuffer.getInt();
            }
            return null;
        }

    }

    class LinkContentBuilder implements RespBodyBuilder<String> {

        @Override
        public String getAnswer(final byte[] bytes,
                final SctpResponseHeader responseHeader) {

            return new String(bytes);
        }

    }

    class SctpIteratorResponseBuilder implements
            RespBodyBuilder<List<ScIterator>> {

        private static final int INTEGER_BYTE_SIZE = 4;

        @Override
        public List<ScIterator> getAnswer(byte[] bytes,
                SctpResponseHeader responseHeader) {

            ByteArrayInputStream source = new ByteArrayInputStream(bytes);
            final SctpResultType resultType = responseHeader.getResultType();
            if (SctpResultType.SCTP_RESULT_OK == resultType) {
                final ByteBuffer byteBuffer = ByteBuffer.wrap(bytes, 0,
                        INTEGER_BYTE_SIZE);
                byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
                int iteratorCount = byteBuffer.getInt();
                for (int i = 0; i < iteratorCount; ++i) {
                    int offset = i * ScParameterSize.SC_ADDRESS.getSize();

                }
            }
            // TODO Auto-generated method stub
            return null;
        }
    }

    public RespBodyBuilder create(final SctpCommandType command) {

        switch (command) {
        // TODO change return type
            case CHECK_ELEMENT_COMMAND:
                return new EmptyResponseBodyBuider();
            case GET_ELEMENT_TYPE_COMMAND:
                return new ElementTypeBuilder();
                // TODO change return type
            case ERASE_ELEMENT_COMMAND:
                return new EmptyResponseBodyBuider();
            case CREATE_NODE_COMMAND:
                return new AddressWhenSuccessBuilder();
            case CREATE_LINK_COMMAND:
                return new AddressWhenSuccessBuilder();
            case CREATE_ARC_COMMAND:
                return new AddressWhenSuccessBuilder();
            case GET_ARC_COMMAND:
                return new GetArcBuilder();
            case GET_LINK_CONTENT_COMMAND:
                return new LinkContentBuilder();
            case FIND_LINKS_COMMAND:
                return new FindLinksBuilder();
            case SET_LINK_CONTENT_COMMAND:
                return new EmptyResponseBodyBuider();
            case EVENT_CREATE_COMMAND:
                return new IdWhenSuccessBuilder();
            case EVENT_DESTROY_COMMAND:
                return new IdWhenSuccessBuilder();
            case FIND_ELEMENT_BY_SYSIDTF_COMMAND:
                return new AddressWhenSuccessBuilder();
                // TODO change return type
            case SET_SYSIDTF_COMMAND:
                return new EmptyResponseBodyBuider();
            default:
                // TODO: 0x02 Ask ElementTypes and add to ScElementType enum
                // TODO: 0x08 - underfined on sc-machine wiki
                // TODO: 0x0c
                // TODO: 0x0d
                // TODO: 0x10
                // TODO:0xa2
                // TODO:0xa3 ask version encoding
                throw new IllegalArgumentException("Not support command="
                        + command);
        }
    }

}
