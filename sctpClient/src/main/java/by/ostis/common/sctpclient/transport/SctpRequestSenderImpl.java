package by.ostis.common.sctpclient.transport;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.ostis.common.sctpclient.model.ScParameter;
import by.ostis.common.sctpclient.model.request.SctpRequest;
import by.ostis.common.sctpclient.model.request.SctpRequestBody;
import by.ostis.common.sctpclient.model.request.SctpRequestHeader;
import by.ostis.common.sctpclient.model.response.SctpResponse;
import by.ostis.common.sctpclient.model.response.SctpResponseHeader;
import by.ostis.common.sctpclient.model.response.SctpResultType;
import by.ostis.common.sctpclient.utils.constants.ScParameterSize;
import by.ostis.common.sctpclient.utils.constants.SctpCommandType;

public class SctpRequestSenderImpl implements SctpRequestSender {

	private static final int COMMAND_TYPE_CODE_INDEX = 0;
	private static final int ID_BEGIN_INDEX = 1;
	private static final int ID_BYTE_SIZE = 4;
	private static final int RESULT_TYPE_CODE_SIZE = 1;
	private static final int SIZE_BEGIN_INDEX = 6;
	private static final int SIZE_BYTE_SIZE = 4;
	private static final int PARAMETERS_BEGIN_INDEX = 10;

	private InputStream inputStream;
	private OutputStream outputStream;
	private Socket socket;
	private RespBodyBuilderProvider respBodyBuilder;
	
	private Logger logger = LogManager.getLogger(SctpRequestSender.class);

	public SctpRequestSenderImpl() {
		super();
		respBodyBuilder = new RespBodyBuilderProvider();
	}

	@Override
	public void init(String host, int port) {
		try {
			socket = new Socket(host, port);
			inputStream = socket.getInputStream();
			outputStream = socket.getOutputStream();
		} catch (IOException e) {
			logger.error(e.getMessage());
			//BARBOS Throw custom exception
		}
	}

	@Override
	public void shutdown() {
		try {
			closeResources();
		} catch (IOException e) {
			logger.error(e.getMessage());
			//BARBOS Throw custom exception
		}
	}

	private void closeResources() throws IOException {
		socket.close();
		inputStream.close();
		outputStream.close();
	}

	@Override
	public SctpResponse sendRequest(SctpRequest request) {
		try {
			byte[] data = getRequestBytes(request);
			outputStream.write(data);
		} catch (IOException e) {
			logger.error(e.getMessage());
			//BARBOS Throw custom exception
		}
		return getResponse();
	}
	
	private SctpResponse getResponse() {
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
			// BARBOS throw custom exception
			// BARBOS Add logging
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
