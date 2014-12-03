package by.ostis.common.sctpClient.ui;

import by.ostis.common.client.ISctpClient;
import by.ostis.common.client.SctpClient;
import by.ostis.common.model.ScAdress;

public class TestClient {

	public static void main(String[] args) {
		ISctpClient client=new SctpClient();
		client.init("192.168.1.10",55770);
		client.getScLinkContent(new ScAdress());

	}

}
