package by.ostis.common.sctpclient.ui;


import by.ostis.common.sctpclient.client.SctpClient;
import by.ostis.common.sctpclient.client.SctpClientImpl;

public class TestClient {

	public static void main(String[] args) {
		SctpClient client=new SctpClientImpl();
		client.init("localhost",55770);
//		client.getScLinkContent(new ScAdress((short)0,(short)5078));
	}

}
