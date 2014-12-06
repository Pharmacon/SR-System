package by.ostis.common.sctpсlient.client;
import by.ostis.common.sctpсlient.model.ScAddress;


public interface ISctpClient {
	public void init(String host,int port);
	public void shutdown();
	public byte[] getScLinkContent(ScAddress address);
	
	public byte[] checkElement(ScAddress address);
}
