package by.ostis.common.sctpclient.transport;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.ostis.common.sctpclient.exception.ErrorMessage;
import by.ostis.common.sctpclient.exception.TransportException;
import by.ostis.common.sctpclient.model.response.SctpResponse;
import by.ostis.common.sctpclient.model.response.SctpResponseHeader;
import by.ostis.common.sctpclient.model.response.SctpResultType;
import by.ostis.common.sctpclient.utils.constants.SctpCommandType;

class BytesSctpResponseBuilder implements SctpResponseBuilder {

    private static final int ID_BYTE_SIZE = 4;
    private static final int RESULT_TYPE_CODE_SIZE = 1;
    private static final int SIZE_BYTE_SIZE = 4;

    private RespBodyBuilderProvider respBodyProvider;
    private Logger logger = LogManager.getLogger(BytesSctpResponseBuilder.class);

    public BytesSctpResponseBuilder() {
	super();
	respBodyProvider = new RespBodyBuilderProvider();
    }

    @Override
    public SctpResponse build(InputStream source) throws TransportException {

	SctpResponse response = new SctpResponse();
	SctpResponseHeader header = new SctpResponseHeader();
	try {

	    byte code;
	    code = (byte) source.read();
	    header.setCommandType(SctpCommandType.getByCode(code));

	    byte[] bytes = getBytesFromResp(source, ID_BYTE_SIZE);
	    int commandId = getIntFromBytes(bytes);
	    header.setCommandId(commandId);

	    byte result = bytes[RESULT_TYPE_CODE_SIZE];
	    header.setResultType(SctpResultType.getByCode(result));

	    bytes = getBytesFromResp(source, SIZE_BYTE_SIZE);
	    int argumentSize = getIntFromBytes(bytes);
	    header.setArgumentSize(argumentSize);

	    response.setHeader(header);

	    SctpCommandType commandType = header.getCommandType();

	    byte[] parameterBytes = getBytesFromResp(source, header.getArgumentSize());

	    RespBodyBuilder bodyBuider = respBodyProvider.create(commandType);
	    response.setBody(bodyBuider.getBody(parameterBytes, header));

	} catch (IOException e) {
	    logger.error(e.getMessage());
	    throw new TransportException(ErrorMessage.RESPONSE_READ_ERROR);
	}
	return response;
    }

    private byte[] getBytesFromResp(InputStream source, int count) throws IOException {
	byte[] result = new byte[count];
	source.read(result);
	return result;
    }

    private int getIntFromBytes(byte[] bytes) {
	ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
	byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
	return byteBuffer.getInt();
    }

}
