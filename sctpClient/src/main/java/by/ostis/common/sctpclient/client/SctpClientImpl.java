package by.ostis.common.sctpclient.client;

import java.util.List;

import by.ostis.common.sctpclient.exception.SctpClientException;
import by.ostis.common.sctpclient.exception.IllegalResultCodeException;
import by.ostis.common.sctpclient.exception.InitializationException;
import by.ostis.common.sctpclient.exception.ShutdownException;
import by.ostis.common.sctpclient.exception.TransportException;
import by.ostis.common.sctpclient.model.ScAddress;
import by.ostis.common.sctpclient.model.ScContentSize;
import by.ostis.common.sctpclient.model.ScIterator;
import by.ostis.common.sctpclient.model.ScIteratorFactory;
import by.ostis.common.sctpclient.model.ScParameter;
import by.ostis.common.sctpclient.model.ScString;
import by.ostis.common.sctpclient.model.request.RequestHeaderType;
import by.ostis.common.sctpclient.model.request.SctpRequest;
import by.ostis.common.sctpclient.model.response.SctpResponse;
import by.ostis.common.sctpclient.transport.ElementNotFoundException;
import by.ostis.common.sctpclient.transport.SctpRequestSender;
import by.ostis.common.sctpclient.transport.SctpRequestSenderImpl;
import by.ostis.common.sctpclient.utils.AssertionUtils;
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
    public SctpResponse<String> getScLinkContent(final ScAddress address)
            throws SctpClientException {

        try {
            return sendRequest(SctpCommandType.GET_LINK_CONTENT_COMMAND, address);
        } catch (IllegalResultCodeException e) {
            return SctpResponse.<String> EMPTY_RESPONSE();
        }
    }

    @Override
    public SctpResponse<Boolean> checkElementExistence(final ScAddress address)
            throws SctpClientException {

        AssertionUtils.notNull(address);
        SctpResponse<Boolean> elemExist;
        try {
            elemExist = sendRequest(SctpCommandType.CHECK_ELEMENT_COMMAND, address);
        } catch (ElementNotFoundException e) {
            elemExist = new SctpResponse<Boolean>();
            elemExist.setAnswer(false);
        }
        return elemExist;
    }

    @Override
    public SctpResponse<ScAddress> searchElement(final ScString identifier)
            throws SctpClientException {

        AssertionUtils.notNull(identifier);
        try {
            return sendRequest(SctpCommandType.FIND_ELEMENT_BY_SYSIDTF_COMMAND, new ScContentSize(
                    identifier.getByteSize()), identifier);
            // TODO change handled exception to SctpClientException after full
            // ResultCode support
        } catch (ElementNotFoundException e) {
            return SctpResponse.<ScAddress> EMPTY_RESPONSE();
        }
    }

    public SctpResponse<Boolean> deleteElement(final ScAddress address) throws SctpClientException {

        AssertionUtils.notNull(address);
        SctpResponse<Boolean> result;
        try {
            return sendRequest(SctpCommandType.ERASE_ELEMENT_COMMAND, address);
            // TODO change handled exception to SctpClientException after full
            // ResultCode support
        } catch (ElementNotFoundException e) {
            result = new SctpResponse<Boolean>();
            result.setAnswer(false);
        }
        return result;
    }

    @Override
    public SctpResponse<ScAddress> createElement(final ScElementType type)
            throws SctpClientException {

        AssertionUtils.notNull(type);
        try {
            return sendRequest(SctpCommandType.CREATE_NODE_COMMAND, type);
        } catch (ElementNotFoundException e) {
            return SctpResponse.<ScAddress> EMPTY_RESPONSE();
        }
    }

    @Override
    public SctpResponse<ScAddress> createScLink() throws SctpClientException {

        try {
            return sendRequest(SctpCommandType.CREATE_LINK_COMMAND, NO_PARAMETERS);
        } catch (ElementNotFoundException e) {
            return SctpResponse.<ScAddress> EMPTY_RESPONSE();
        }
    }

    @Override
    public SctpResponse<ScAddress> createScArc(final ScElementType type,
            final ScAddress begAddress, final ScAddress endAddress) throws SctpClientException {

        AssertionUtils.notNull(type, begAddress, endAddress);
        try {
            return sendRequest(SctpCommandType.CREATE_ARC_COMMAND, type, begAddress, endAddress);
        } catch (ElementNotFoundException e) {
            return SctpResponse.<ScAddress> EMPTY_RESPONSE();
        }
    }

    @Override
    public SctpResponse<List<ScAddress>> getArcBeginAndEnd(final ScAddress arcAddress)
            throws SctpClientException {

        AssertionUtils.notNull(arcAddress);
        try {
            return sendRequest(SctpCommandType.GET_ARC_VERTEXES_COMMAND, arcAddress);
        } catch (ElementNotFoundException e) {
            return SctpResponse.<List<ScAddress>> EMPTY_RESPONSE();
        }
    }

    @Override
    public SctpResponse<List<ScAddress>> searchScLinks(final ScString content)
            throws SctpClientException {

        AssertionUtils.notNull(content);
        try {
            return sendRequest(SctpCommandType.FIND_LINKS_COMMAND,
                    new ScContentSize(content.getByteSize()), content);
        } catch (ElementNotFoundException e) {
            return SctpResponse.<List<ScAddress>> EMPTY_RESPONSE();
        }
    }

    @Override
    public SctpResponse<Boolean> setScRefContent(final ScAddress address, final ScString content)
            throws SctpClientException {

        AssertionUtils.notNull(address, content);
        SctpResponse<Boolean> result;
        try {
            return sendRequest(SctpCommandType.SET_LINK_CONTENT_COMMAND, address,
                    new ScContentSize(content.getByteSize()), content);
            // TODO change handled exception to SctpClientException after full
            // ResultCode support
        } catch (ElementNotFoundException e) {
            result = new SctpResponse<Boolean>();
            result.setAnswer(false);
        }
        return result;
    }

    @Override
    public SctpResponse<Boolean> setSystemIdentifier(final ScAddress address,
            final ScString identifier) throws SctpClientException {

        AssertionUtils.notNull(address, identifier);
        SctpResponse<Boolean> result;
        try {
            return sendRequest(SctpCommandType.SET_SYSIDTF_COMMAND, address, new ScContentSize(
                    identifier.getByteSize()), identifier);
        } catch (Exception e) {
            result = new SctpResponse<Boolean>();
            result.setAnswer(false);
        }
        return result;
    }

    @Override
    public SctpResponse<List<ScIterator>> searchByIterator(ScIteratorType iteratorType,
            List<ScParameter> params) throws SctpClientException {

        AssertionUtils.notNull(iteratorType, params);
        AssertionUtils.notNull(params.toArray());
        ScIterator iterator = ScIteratorFactory.buildScIterator(params);

        try {
            return sendRequest(SctpCommandType.ITERATE_ELEMENTS_COMMAND, iteratorType, iterator);
        } catch (ElementNotFoundException e) {
            return SctpResponse.<List<ScIterator>> EMPTY_RESPONSE();
        }
    }

    private <T> SctpResponse<T> sendRequest(SctpCommandType sctpCommandType,
            ScParameter... parameters) throws SctpClientException {

        SctpResponse<T> response = new SctpResponse<T>();
        RequestHeaderType requestHeaderType = RequestHeaderType.getByCommandId(sctpCommandType);
        try {
            SctpRequest request = this.requestBuilder.buildRequest(requestHeaderType, parameters);
            response = this.sender.sendRequest(request);
        } catch (final TransportException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ElementNotFoundException e) {
            // TODO e never thrown Add implementation in later versions.
        }
        return response;
    }

}
