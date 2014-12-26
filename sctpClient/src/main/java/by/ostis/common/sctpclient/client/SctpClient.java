package by.ostis.common.sctpclient.client;
import by.ostis.common.sctpclient.model.ScAddress;
import by.ostis.common.sctpclient.model.ScRefContent;
import by.ostis.common.sctpclient.utils.constants.ScElementType;


public interface SctpClient {
	public void init(String host, int port);
	public void shutdown();
	public ScRefContent getScLinkContent(ScAddress address);
	public ScElementType checkElement(ScAddress address);
}
