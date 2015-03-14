package by.ostis.common.sctpclient.transport;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import by.ostis.common.sctpclient.exception.AnswerParseException;
import by.ostis.common.sctpclient.model.ScAddress;
import by.ostis.common.sctpclient.model.ScIterator;
import by.ostis.common.sctpclient.model.request.SctpRequest;
import by.ostis.common.sctpclient.model.request.SctpRequestBody;
import by.ostis.common.sctpclient.model.response.SctpResponseHeader;
import by.ostis.common.sctpclient.model.response.SctpResultType;
import by.ostis.common.sctpclient.utils.InputStreamReader;
import by.ostis.common.sctpclient.utils.constants.ScElementType;
import by.ostis.common.sctpclient.utils.constants.ScIteratorType;
import by.ostis.common.sctpclient.utils.constants.ScParameterSize;
import by.ostis.common.sctpclient.utils.constants.SctpCommandType;

final class RespBodyBuilderProvider {

    private class AddressWhenSuccessBuilder implements RespBodyBuilder<ScAddress> {

        @Override
        public ScAddress getAnswer(final byte[] bytes, final SctpResponseHeader responseHeader) {

            final SctpResultType resultType = responseHeader.getResultType();
            ScAddress scAddress = null;
            if (SctpResultType.SCTP_RESULT_OK == resultType) {
                scAddress = TypeBuilder.buildScAddress(bytes);
            }
            return scAddress;
        }

    }

    private class ElementTypeBuilder implements RespBodyBuilder<ScElementType> {

        @Override
        public ScElementType getAnswer(final byte[] bytes, final SctpResponseHeader responseHeader) {

            final SctpResultType resultType = responseHeader.getResultType();
            if (resultType.equals(SctpResultType.SCTP_RESULT_OK)) {
                final byte[] elementCode = Arrays.copyOf(bytes,
                        ScElementType.SC_ELEMENT_TYPE_BYTE_SIZE);
                final short code = ByteBuffer.wrap(elementCode).order(ByteOrder.LITTLE_ENDIAN)
                        .getShort();
                for (final ScElementType elementType : ScElementType.values()) {
                    if (code == elementType.getValue()) {
                        return elementType;
                    }
                }
            }
            return null;
        }

    }

    private class EmptyResponseBodyBuider implements RespBodyBuilder<Boolean> {

        @Override
        public Boolean getAnswer(final byte[] bytes, final SctpResponseHeader responseHeader) {

            final SctpResultType resultType = responseHeader.getResultType();
            return SctpResultType.SCTP_RESULT_OK == resultType;
        }

    }

    private class FindLinksBuilder implements RespBodyBuilder<List<ScAddress>> {

        private static final int LINKS_NUMBER_END_INDEX = 4;

        private static final int LINKS_NUMBER_BEGIN_INDEX = 0;

        private static final int LINKS_ADDRESSES_BEGIN_INDEX = 4;

        @Override
        public List<ScAddress> getAnswer(final byte[] bytes, final SctpResponseHeader responseHeader) {

            final List<ScAddress> list = new ArrayList<ScAddress>();
            final SctpResultType resultType = responseHeader.getResultType();
            if (SctpResultType.SCTP_RESULT_OK == resultType) {
                final int linksNumber = getLinkNumbers(bytes);
                final Collection<ScAddress> addresses = getAddresses(bytes, linksNumber);
                list.addAll(addresses);
            }
            return list;
        }

        private int getLinkNumbers(final byte[] bytes) {

            final ByteBuffer byteBuffer = ByteBuffer.wrap(bytes, LINKS_NUMBER_BEGIN_INDEX,
                    LINKS_NUMBER_END_INDEX);
            byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
            return byteBuffer.getInt();
        }

        private Collection<ScAddress> getAddresses(final byte[] bytes, final int number) {

            final List<ScAddress> list = new LinkedList<ScAddress>();
            int beginIndex = LINKS_ADDRESSES_BEGIN_INDEX;
            for (int i = 0; i < number; i++) {
                final ScAddress address = TypeBuilder.buildScAddress(bytes, beginIndex);
                list.add(address);
                beginIndex += ScParameterSize.SC_ADDRESS.getSize();
            }
            return list;
        }

    }

    private class GetArcVertexesBuilder implements RespBodyBuilder<List<ScAddress>> {

        private static final int END_ADDRESS_BEGIN_INDEX = 4;

        @Override
        public List<ScAddress> getAnswer(final byte[] bytes, final SctpResponseHeader responseHeader) {

            final List<ScAddress> list = new ArrayList<ScAddress>();
            final ScAddress begin = TypeBuilder.buildScAddress(bytes);
            final ScAddress end = TypeBuilder.buildScAddress(bytes, END_ADDRESS_BEGIN_INDEX);
            list.add(begin);
            list.add(end);
            return list;
        }

    }

    class IdWhenSuccessBuilder implements RespBodyBuilder<Integer> {

        @Override
        public Integer getAnswer(final byte[] bytes, final SctpResponseHeader responseHeader) {

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
        public String getAnswer(final byte[] bytes, final SctpResponseHeader responseHeader) {

            return new String(bytes);
        }

    }

    class SctpIteratorResponseBuilder implements RespBodyBuilder<List<ScIterator>> {

        private SctpRequest sctpRequest;

        private static final int ITERATOR_TYPE_PARAMETER_INDEX = 0;

        public SctpIteratorResponseBuilder(SctpRequest sctpRequest) {

            this.sctpRequest = sctpRequest;
        }

        @Override
        public List<ScIterator> getAnswer(byte[] bytes, SctpResponseHeader responseHeader)
                throws AnswerParseException {

            final ScIteratorType iteratorType = getIteratorTypeFromRequest();
            int parameterNumber = ScIteratorParameterNumberResolver
                    .getParameterNumberByIteratorType(iteratorType);
            List<ScIterator> result = new ArrayList<ScIterator>();
            ByteArrayInputStream source = new ByteArrayInputStream(bytes);
            try {
                int answerIterCount = InputStreamReader.readInt(source);
                final SctpResultType resultType = responseHeader.getResultType();
                if (SctpResultType.SCTP_RESULT_OK == resultType) {
                    for (int iterIndex = 0; iterIndex < answerIterCount; ++iterIndex) {
                        // ScIterator scIterator =
                        // buildScIterator(parameterNumber, source);
                        ScIterator scIterator = new ScIterator();
                        for (int j = 0; j < parameterNumber; ++j) {
                            ScAddress scAddress = TypeBuilder.buildScAddress(source);
                            scIterator.registerParameter(scAddress);
                        }
                        result.add(scIterator);
                    }
                    return result;
                }
                // TODO throw exception
                return null;

            } catch (IOException e) {
                throw new AnswerParseException(e.getMessage());
            }
        }

        // private ScIterator buildScIterator(int paramNumber,
        // ByteArrayInputStream source)
        // throws IOException {
        //
        // ScIterator scIterator = new ScIterator();
        // for (int j = 0; j < paramNumber; ++j) {
        // ScAddress scAddress = TypeBuilder.buildScAddress(source);
        // scIterator.registerParameter(scAddress);
        // }
        // return scIterator;
        // }

        private ScIteratorType getIteratorTypeFromRequest() {

            SctpRequestBody requestBody = sctpRequest.getBody();
            return (ScIteratorType) requestBody.getParameterList().get(
                    ITERATOR_TYPE_PARAMETER_INDEX);
        }
    }

    public RespBodyBuilder create(final SctpCommandType command, SctpRequest sctpRequest) {

        switch (command) {
            case CHECK_ELEMENT_COMMAND:
                return new EmptyResponseBodyBuider();
            case GET_ELEMENT_TYPE_COMMAND:
                return new ElementTypeBuilder();
            case ERASE_ELEMENT_COMMAND:
                return new EmptyResponseBodyBuider();
            case CREATE_NODE_COMMAND:
                return new AddressWhenSuccessBuilder();
            case CREATE_LINK_COMMAND:
                return new AddressWhenSuccessBuilder();
            case CREATE_ARC_COMMAND:
                return new AddressWhenSuccessBuilder();
            case GET_ARC_VERTEXES_COMMAND:
                return new GetArcVertexesBuilder();
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
            case SET_SYSIDTF_COMMAND:
                return new EmptyResponseBodyBuider();
            case ITERATE_ELEMENTS_COMMAND:
                return new SctpIteratorResponseBuilder(sctpRequest);
            default:
                // TODO: 0x02 Ask ElementTypes and add to ScElementType enum
                // TODO: 0x08 - undefined on sc-machine wiki
                // TODO: 0x0e - Event subscription
                // TODO: 0x0f - Delete event subscription
                // TODO: 0x0d - Complex construction iteration
                // TODO: 0x10 - Passed event inquiry
                // TODO:0xa2 - Server statistics in time interval
                // TODO:0xa3 - Ask protocol version(ask encoding)
                throw new IllegalArgumentException("Not support command= " + command);
        }
    }

}
