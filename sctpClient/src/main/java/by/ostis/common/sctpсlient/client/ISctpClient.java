package by.ostis.common.sctpсlient.client;

import by.ostis.common.sctpсlient.model.ScAdress;
import by.ostis.common.sctpсlient.utils.ResultCode;
public interface ISctpClient {
	public void init(String host,int port);
	public void shutdown();
	public ResultCode  erraseElement(ScAdress adr);
	public byte[] getScLinkContent(ScAdress adr);
}
