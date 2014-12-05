package by.ostis.common.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.sun.corba.se.impl.ior.ByteBuffer;

import by.ostis.common.model.ScAdress;
import by.ostis.common.utils.ResultCode;

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
		ByteBuffer paramsBuf=new ByteBuffer(30);
		paramsBuf.append(adr.getSegment());
		paramsBuf.append(adr.getOffset());
		paramsBuf.trimToSize();
		ByteBuffer commonBuf=new ByteBuffer(30);
		commonBuf.append(0x09);
		commonBuf.append(0);
		commonBuf.append(0);
		commonBuf.append(paramsBuf.size());
		commonBuf.append(paramsBuf.toString());
		commonBuf.trimToSize();
		try {
			os.write(commonBuf.toArray());
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
