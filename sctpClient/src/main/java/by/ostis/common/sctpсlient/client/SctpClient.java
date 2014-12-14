package by.ostis.common.sctpсlient.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

import by.ostis.common.sctpсlient.model.ScAddress;
import by.ostis.common.sctpсlient.utils.constants.SctpCommandType;

public class SctpClient implements ISctpClient {
	private Socket socket = null;
	private OutputStream os;
	private InputStream is;

	public void init(String host, int port) {
		try {
			socket = new Socket(host, port);
			os = socket.getOutputStream();
			is = socket.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void shutdown() {
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public byte[] getScLinkContent(ScAddress adr) {
		// ByteBuffer paramsBuf=new ByteBuffer(30);
		// paramsBuf.append(adr.getSegment());
		// paramsBuf.append(adr.getOffset());
		// paramsBuf.trimToSize();
		// ByteBuffer commonBuf=new ByteBuffer(30);
		// commonBuf.append(0x09);
		// commonBuf.append(0);
		// commonBuf.append(0);
		// commonBuf.append(paramsBuf.size());
		// commonBuf.append(paramsBuf.toString());
		// commonBuf.trimToSize();
		// try {
		// os.write(commonBuf.toArray());
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// byte [] result=new byte[10];
		// try {
		// is.read(result, 0, 10);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// System.out.print(result.toString());
		return null;
	}

	private byte[] receiveDate(int size) {
		return null;
	}

	public byte[] checkElement(ScAddress address) {
		ByteBuffer bb = ByteBuffer.allocate(14);
		bb.put(SctpCommandType.CHECK_ELEMENT.getValue()).put((byte) 0)
				.putInt(0)

				.put((byte) 4).put((byte) 0).put((byte) 0).put((byte) 0);
		bb.putShort(address.getSegment()).putShort(address.getOffset());
		for (int i = 0; i < bb.array().length; ++i) {
			System.out.print(bb.array()[i] + " ");
		}

		try {
			os.write(bb.array());
			byte[] result = new byte[10];
			System.out.println("\nresponse");
			is.read(result, 0, 10);
			// PRINT
			System.out.print(result[0]);
			System.out.print('|');
			for (int i = 1; i < 5; i++) {
				System.out.print(result[i]);
			}
			System.out.print('|');
			System.out.print(result[5]);
			System.out.print('|');
			for (int i = 6; i < 10; i++) {
				System.out.print(result[i]);
			}
			System.out.print('\n');
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


}
