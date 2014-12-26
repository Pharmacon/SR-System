package by.ostis.common.sctpclient.responsebuilder.commandsbuilders;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import by.ostis.common.sctpclient.model.response.SctpResponseHeader;
import by.ostis.common.sctpclient.model.response.SctpResultType;
import by.ostis.common.sctpclient.responsebuilder.ResponseBodyBuilder;
import by.ostis.common.sctpclient.utils.constants.ScElementType;

public class ElementTypeBuilder implements ResponseBodyBuilder {

	
	@Override
	public List<Object> getBody(byte[] bytes, SctpResponseHeader responseHeader) {
		List<Object> body=new ArrayList<>();
		SctpResultType resultType=responseHeader.getResultType();
		if(resultType.equals(SctpResultType.SCTP_RESULT_OK)){
			byte[] elementCode=Arrays.copyOf(bytes,ScElementType.SC_ELEMENT_BYTE_SIZE);
			short code = ByteBuffer.wrap(elementCode).order(ByteOrder.LITTLE_ENDIAN).getShort();
			for(ScElementType elementType:ScElementType.values()){
				if(code==elementType.getValue()){
					body.add(elementType);
				}
			}
		}
		return body;
	}

}
