package by.ostis.common.sctpсlient.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

import by.ostis.common.sctpсlient.model.ScAdress;
import by.ostis.common.sctpсlient.utils.ResultCode;

public class SctpClient implements ISctpClient{
	private Socket socket=null;
	private OutputStream os;
	private InputStream is;
	@Override
	public void init(String host, int port) {
		try {
			socket=new Socket(host,port);
			os=socket.getOutputStream();
			is=socket.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void shutdown() {
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ResultCode erraseElement(ScAdress adr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getScLinkContent(ScAdress adr) {
		ByteBuffer commonBuf=ByteBuffer.allocate(12);
		try {
			os.write(commonBuf.array());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			byte [] result=new byte[10];
			try {
				is.read(result, 0, 10);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.print(result.toString());
		return null;
	}
	private byte[] receiveDate(int size){
		return null;
	}
}
