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
	
	private RespBodyBuilderProvider respBodyBuilder;

	private Logger logger = LogManager.getLogger(SctpRequestSender.class);

	public SctpRequestSenderImpl() {
		super();
		responseBuilder = new BytesSctpResponseBuilder();
	}

	@Override
	public void init(String host, int port) throws InitializationException {
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
	public void shutdown() throws ShutdownException {
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
	public SctpResponse sendRequest(SctpRequest request)
			throws TransportException {
		try {
			byte[] data = SctpRequestBytesBuilder.build(request);
			outputStream.write(data);
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new TransportException(ErrorMessage.REQUEST_SEND_ERROR);
		}
		return responseBuilder.build(inputStream);
	}
	

	private SctpResponse getResponse() throws TransportException {
		SctpResponse response = new SctpResponse();
		SctpResponseHeader header = new SctpResponseHeader();
		try {

			byte code;
			code = (byte) inputStream.read();
			header.setCommandType(SctpCommandType.getByCode(code));

			byte[] bytes = getBytesFromResp(ID_BYTE_SIZE);
			int commandId = getIntFromBytes(bytes);
			header.setCommandId(commandId);

			byte result = bytes[RESULT_TYPE_CODE_SIZE];
			header.setResultType(SctpResultType.getByCode(result));

			bytes = getBytesFromResp(SIZE_BYTE_SIZE);
			int argumentSize = getIntFromBytes(bytes);
			header.setArgumentSize(argumentSize);

			response.setHeader(header);

			SctpCommandType commandType = header.getCommandType();

			byte[] parameterBytes = getBytesFromResp(header.getArgumentSize());

			RespBodyBuilder bodyBuider = respBodyBuilder.create(commandType);
			response.setBody(bodyBuider.getBody(parameterBytes, header));

		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new TransportException(ErrorMessage.RESPONSE_READ_ERROR);
		}
		return response;
	}

	private byte[] getRequestBytes(SctpRequest request) {
		byte[] bodyByteArray = parseRequestBody(request.getBody());
		byte[] headerByteArray = parseRequestHeader(request.getHeader());
		ByteBuffer resultBuffer = ByteBuffer.allocate(bodyByteArray.length
				+ headerByteArray.length);
		resultBuffer.put(headerByteArray);
		resultBuffer.put(bodyByteArray);
		return resultBuffer.array();
	}

	private byte[] parseRequestHeader(SctpRequestHeader requestHeader) {
		ByteBuffer tempBuffer = ByteBuffer.allocate(ScParameterSize.SC_HEADER
				.getSize());
		tempBuffer.order(ByteOrder.LITTLE_ENDIAN);
		tempBuffer.put(requestHeader.getCommandType().getValue());
		tempBuffer.put(requestHeader.getFlag());
		tempBuffer.putInt(requestHeader.getCommandId());
		tempBuffer.putInt(requestHeader.getArgumentSize());
		return tempBuffer.array();
	}

	private byte[] parseRequestBody(SctpRequestBody requestBody) {
		ByteBuffer tempBuffer = ByteBuffer
				.allocate(requestBody.getByteLenght());
		List<ScParameter> parameterList = requestBody.getParameterList();
		for (ScParameter parameter : parameterList) {
			tempBuffer.put(parameter.getBytes());
		}
		return tempBuffer.array();
	}

	private byte[] getBytesFromResp(int count) throws IOException {
		byte[] result = new byte[count];
		inputStream.read(result);
		return result;
	}

	private int getIntFromBytes(byte[] bytes) {
		ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
		byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
		return byteBuffer.getInt();
	}
}
