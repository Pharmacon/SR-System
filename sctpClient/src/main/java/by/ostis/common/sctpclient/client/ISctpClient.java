package by.ostis.common.sctpclient.client;
import by.ostis.common.sctpclient.model.ScAddress;


public interface ISctpClient {
	public void init(String host, int port);
	public void shutdown();
	public byte[] getScLinkContent(ScAddress address);
	public byte[] checkElement(ScAddress address);
}
