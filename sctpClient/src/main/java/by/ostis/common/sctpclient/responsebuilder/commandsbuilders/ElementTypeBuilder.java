package by.ostis.common.sctpclient.responsebuilder.commandsbuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import by.ostis.common.sctpclient.model.response.ScElementType;
import by.ostis.common.sctpclient.model.response.SctpResponseHeader;
import by.ostis.common.sctpclient.model.response.SctpResultType;
import by.ostis.common.sctpclient.responsebuilder.ResponseBodyBuilder;

public class ElementTypeBuilder implements ResponseBodyBuilder {

	
	@Override
	public List<Object> getBody(byte[] bytes, SctpResponseHeader responseHeader) {
		List<Object> body=new ArrayList<>();
		SctpResultType resultType=responseHeader.getResultType();
		if(resultType.equals(SctpResultType.SCTP_RESULT_OK)){
			byte[] code=Arrays.copyOf(bytes,ScElementType.SC_ELEMENT_BYTE_SIZE);
			for(ScElementType elementType:ScElementType.values()){
				byte[] elementCode=elementType.getType();
				if(Arrays.equals(code, elementCode)){
					body.add(elementType);
				}
			}
		}
		return body;
	}

}
