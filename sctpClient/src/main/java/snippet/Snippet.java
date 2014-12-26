package snippet;


import by.ostis.common.sctpclient.client.SctpClient;
import by.ostis.common.sctpclient.client.SctpClientImpl;
import by.ostis.common.sctpclient.model.ScAddress;

public class Snippet {
	public static void main(String[] args) {
		SctpClient client = new SctpClientImpl();
		client.init("localhost", 55770);
		ScAddress address = new ScAddress((short)0,(short)0);
		System.out.print("sc-machine response:" + client.checkElement(address).toString());
	}
}

