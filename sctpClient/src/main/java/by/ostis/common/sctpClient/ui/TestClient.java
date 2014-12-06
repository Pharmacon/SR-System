package by.ostis.common.sctpClient.ui;

import java.nio.ByteBuffer;

import by.ostis.common.client.ISctpClient;
import by.ostis.common.client.SctpClient;
import by.ostis.common.model.ScAddress;

public class TestClient {

	public static void main(String[] args) {
		ISctpClient client=new SctpClient();
		client.init("localhost",55770);
		client.checkElement(new ScAddress((short)0, (short)1397));
//		client.getScLinkContent(new ScAdress(0,5078));
//		//client.getScLinkContent(new ScAdress(0,4853));
//		client.getScLinkContent(new ScAdress(0,5078));
		client.shutdown();
		
		//System.out.println(0x01);
	
	}

}
