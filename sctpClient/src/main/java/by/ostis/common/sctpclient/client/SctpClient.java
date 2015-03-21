package by.ostis.common.sctpclient.client;

import java.util.List;

import by.ostis.common.sctpclient.exception.SctpClientException;
import by.ostis.common.sctpclient.model.ScAddress;
import by.ostis.common.sctpclient.model.ScIterator;
import by.ostis.common.sctpclient.model.ScParameter;
import by.ostis.common.sctpclient.model.ScString;
import by.ostis.common.sctpclient.model.response.SctpResponse;
import by.ostis.common.sctpclient.utils.constants.ScElementType;
import by.ostis.common.sctpclient.utils.constants.ScIteratorType;

public interface SctpClient {

    public void init(String host, int port);

    public void shutdown();

    public SctpResponse<String> getScLinkContent(ScAddress address) throws SctpClientException;

    public SctpResponse<Boolean> checkElementExistence(ScAddress address)
            throws SctpClientException;

    public SctpResponse<List<ScIterator>> searchByIterator(ScIteratorType iteratorType,
            List<ScParameter> params) throws SctpClientException;

    public SctpResponse<ScAddress> searchElement(ScString identifier) throws SctpClientException;

    public SctpResponse<Boolean> deleteElement(ScAddress address) throws SctpClientException;

    public SctpResponse<ScAddress> createElement(ScElementType type) throws SctpClientException;

    public SctpResponse<ScAddress> createScLink() throws SctpClientException;

    public SctpResponse<ScAddress> createScArc(ScElementType type, ScAddress begAddress,
            ScAddress endAddress) throws SctpClientException;

    public SctpResponse<List<ScAddress>> getArcBeginAndEnd(ScAddress arcAddress)
            throws SctpClientException;

    public SctpResponse<List<ScAddress>> searchScLinks(ScString content) throws SctpClientException;

    public SctpResponse<Boolean> setScRefContent(ScAddress address, ScString content)
            throws SctpClientException;

    public SctpResponse<Boolean> setSystemIdentifier(ScAddress address, ScString identifier)
            throws SctpClientException;

}
