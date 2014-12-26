package by.ostis.common.sctpclient.transport;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import by.ostis.common.sctpclient.model.request.SctpRequest;
import by.ostis.common.sctpclient.model.response.SctpResponse;
import by.ostis.common.sctpclient.responsebuilder.SctpResponseBuilder;
import by.ostis.common.sctpclient.transport.handlers.SctpRequestHandler;
import by.ostis.common.sctpclient.transport.handlers.SctpRequestHandlerImpl;

public class SctpRequestSenderImpl implements SctpRequestSender {
	private InputStream inputStream;
	private OutputStream outputStream;
	private Socket socket;
	private SctpRequestHandler requestHandler = new SctpRequestHandlerImpl();
	private SctpResponseBuilder responseBuilder = new SctpResponseBuilder();

	@Override
	public void init(String host, int port) {
		try {
			socket = new Socket(host, port);
			inputStream = socket.getInputStream();
			outputStream = socket.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated code
			e.printStackTrace();
		}
	}

	@Override
	public void shutdown() {
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated code
			e.printStackTrace();
		}
	}

	@Override
	public SctpResponse sendRequest(SctpRequest request) {
		byte[] data = requestHandler.handleRequest(request);
		try {
			outputStream.write(data);
			inputStream.read(data, 0, 10);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseBuilder.buildSctpResponse(data);
	}

}
