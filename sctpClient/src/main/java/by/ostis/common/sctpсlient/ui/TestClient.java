package by.ostis.common.sctpсlient.ui;


import by.ostis.common.sctpсlient.client.ISctpClient;
import by.ostis.common.sctpсlient.client.SctpClient;
import by.ostis.common.sctpсlient.model.ScAdress;

public class TestClient {

	public static void main(String[] args) {
		ISctpClient client=new SctpClient();
		client.init("localhost",55770);
		client.getScLinkContent(new ScAdress((short)0,(short)5078));
	}

}
