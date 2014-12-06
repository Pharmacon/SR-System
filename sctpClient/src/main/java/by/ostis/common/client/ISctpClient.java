package by.ostis.common.client;

import by.ostis.common.model.ScAddress;
import by.ostis.common.utils.constants.ResultCode;

public interface ISctpClient {
	public void init(String host,int port);
	public void shutdown();
	public byte[] getScLinkContent(ScAddress address);
	
	public byte[] checkElement(ScAddress address);
}
