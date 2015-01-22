package by.ostis.common.sctpclient.client;

import by.ostis.common.sctpclient.model.ScAddress;
import by.ostis.common.sctpclient.model.ScRefContent;
import by.ostis.common.sctpclient.model.response.SctpResponse;

public interface SctpClient {
	
	public void init(String host, int port);

	public void shutdown();

	public ScRefContent getScLinkContent(ScAddress address);

	public SctpResponse checkElementExistence(ScAddress address);
	
	public SctpResponse searchElement(ScRefContent identifier);
	
}
