package by.ostis.common.sctpclient.client;

import java.util.List;

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

    public SctpResponse<String> getScLinkContent(ScAddress address);

    public SctpResponse<Boolean> checkElementExistence(ScAddress address);

    public SctpResponse<List<ScIterator>> searchByIterator(
            ScIteratorType iteratorType, List<ScParameter> params);

    public SctpResponse<ScAddress> searchElement(ScString identifier);

    public SctpResponse<Boolean> deleteElement(ScAddress address);

    public SctpResponse<ScAddress> createElement(ScElementType type);

    public SctpResponse<ScAddress> createScLink();

    public SctpResponse<ScAddress> createScArc(ScElementType type,
            ScAddress begAddress, ScAddress endAddress);

    // TODO
    public SctpResponse<Void> getArcBeginAndEnd(ScAddress arcAddress);

    public SctpResponse<List<ScAddress>> searchScLinks(ScString content);

    public SctpResponse<Boolean> setScRefContent(ScAddress address,
            ScString content);

    public SctpResponse<Boolean> setSystemIdentifier(ScAddress address,
            ScString identifier);

}
