package by.ostis.common.sctpclient.transport;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.ostis.common.sctpclient.exception.ErrorMessage;
import by.ostis.common.sctpclient.exception.InitializationException;
import by.ostis.common.sctpclient.exception.ShutdownException;
import by.ostis.common.sctpclient.exception.TransportException;
import by.ostis.common.sctpclient.model.request.SctpRequest;
import by.ostis.common.sctpclient.model.response.SctpResponse;

public class SctpRequestSenderImpl implements SctpRequestSender {

	private InputStream inputStream;
	private OutputStream outputStream;
	private Socket socket;
	
	private SctpResponseBuilder responseBuilder;
	
	private Logger logger = LogManager.getLogger(SctpRequestSender.class);

	public SctpRequestSenderImpl() {
		super();
		responseBuilder = new BytesSctpResponseBuilder();
	}

	@Override
	public void init(String host, int port) throws InitializationException{
		try {
			socket = new Socket(host, port);
			inputStream = socket.getInputStream();
			outputStream = socket.getOutputStream();
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new InitializationException(ErrorMessage.TRANSPORT_INIT_ERROR);
		}
	}

	@Override
	public void shutdown() throws ShutdownException{
		try {
			closeResources();
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new ShutdownException(ErrorMessage.SHUTDOWN_ERROR);
		}
	}

	private void closeResources() throws IOException {
		socket.close();
		inputStream.close();
		outputStream.close();
	}

	@Override
	public SctpResponse sendRequest(SctpRequest request) throws TransportException{
		try {
			byte[] data = SctpRequestBytesBuilder.build(request);
			outputStream.write(data);
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new TransportException(ErrorMessage.REQUEST_SEND_ERROR);
		}
		return responseBuilder.build(inputStream);
	}
	
}
