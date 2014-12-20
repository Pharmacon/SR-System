package by.ostis.common.sctpclient.ui;


import by.ostis.common.sctpclient.client.ISctpClient;
import by.ostis.common.sctpclient.client.SctpClient;

public class TestClient {

	public static void main(String[] args) {
		ISctpClient client=new SctpClient();
		client.init("localhost",55770);
//		client.getScLinkContent(new ScAdress((short)0,(short)5078));
	}

}
