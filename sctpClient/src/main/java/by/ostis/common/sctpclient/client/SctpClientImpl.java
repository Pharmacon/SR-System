package by.ostis.common.sctpclient.client;

import by.ostis.common.sctpclient.model.ScAddress;
import by.ostis.common.sctpclient.model.ScRefContent;
import by.ostis.common.sctpclient.transport.SctpRequestSender;
import by.ostis.common.sctpclient.transport.SctpRequestSenderImpl;
import by.ostis.common.sctpclient.utils.constants.ScElementType;

public class SctpClientImpl implements SctpClient {

	private SctpRequestSender sender = new SctpRequestSenderImpl();

	@Override
	public void init(String host, int port) {
		sender.init(host, port);
	}

	@Override
	public void shutdown() {
		sender.shutdown();
	}

	public ScRefContent getScLinkContent(ScAddress adr) {

		// SctpRequestBuilder builder=new
		return null;
	}

	private byte[] receiveDate(int size) {
		return null;
	}

	public ScElementType checkElement(ScAddress address) {
//		SctpRequest request = new SctpRequest();
//		SctpRequestHeader header = request.new SctpRequestHeader(
//				SctpCommandType.CHECK_ELEMENT, (byte) 0, 0, 4);
//		request.addParameterToBody(address);
//		request.setHeader(header);
//		SctpResponse response = sender.sendRequest(request);
		
		// ByteBuffer bb = ByteBuffer.allocate(14);
		// bb.put(SctpCommandType.CHECK_ELEMENT.getValue()).put((byte) 0)
		// .putInt(0)
		//
		// .put((byte) 4).put((byte) 0).put((byte) 0).put((byte) 0);
		// bb.putShort(address.getSegment()).putShort(address.getOffset());
		// for (int i = 0; i < bb.array().length; ++i) {
		// System.out.print(bb.array()[i] + " ");
		// }
		//
		// try {
		// os.write(bb.array());
		// byte[] result = new byte[10];
		// System.out.println("\nresponse");
		// is.read(result, 0, 10);
		// // PRINT
		// System.out.print(result[0]);
		// System.out.print('|');
		// for (int i = 1; i < 5; i++) {
		// System.out.print(result[i]);
		// }
		// System.out.print('|');
		// System.out.print(result[5]);
		// System.out.print('|');
		// for (int i = 6; i < 10; i++) {
		// System.out.print(result[i]);
		// }
		// System.out.print('\n');
		// return result;
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		return null;
	}

}
