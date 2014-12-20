package by.ostis.common.sctpclient.responsebuilder.commandsbuilders;

import java.util.ArrayList;
import java.util.List;

import by.ostis.common.sctpclient.model.response.SctpResponseHeader;
import by.ostis.common.sctpclient.responsebuilder.ResponseBodyBuilder;
import by.ostis.common.sctpclient.utils.constants.SctpCommandType;

public class EmptyResponseBodyBuider implements ResponseBodyBuilder{

	
	
	@Override
	public List<Object> getBody(byte[] bytes, SctpResponseHeader responseHeader) {
		return new ArrayList<Object>();
	}

}
