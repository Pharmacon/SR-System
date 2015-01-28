package by.ostis.common.sctpclient.client;

import java.util.List;

import by.ostis.common.sctpclient.model.ScAddress;
import by.ostis.common.sctpclient.model.ScParameter;
import by.ostis.common.sctpclient.model.ScRefContent;
import by.ostis.common.sctpclient.model.response.SctpResponse;
import by.ostis.common.sctpclient.utils.constants.IteratorType;

public interface SctpClient {
	
	public void init(String host, int port);

	public void shutdown();

	public ScRefContent getScLinkContent(ScAddress address);

	public SctpResponse checkElementExistence(ScAddress address);
	
	public SctpResponse searchElement(ScRefContent identifier);
	
	public SctpResponse searchByIterator(IteratorType iteratorType, List<ScParameter> params);
}
