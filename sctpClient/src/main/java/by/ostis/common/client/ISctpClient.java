package by.ostis.common.client;

import by.ostis.common.utils.ResultCode;
import by.ostis.common.model.ScAdress;
public interface ISctpClient {
	public void init(String host,int port);
	public void shutdown();
	public ResultCode  erraseElement(ScAdress adr);
	public byte[] getScLinkContent(ScAdress adr);
}
