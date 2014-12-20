package by.ostis.common.sctpclient.responsebuilder.commandsbuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import by.ostis.common.sctpclient.model.ScAddress;
import by.ostis.common.sctpclient.model.response.SctpResponseHeader;
import by.ostis.common.sctpclient.responsebuilder.ResponseBodyBuilder;
import by.ostis.common.sctpclient.responsebuilder.TypeBuilder;

public class GetArcBuilder implements ResponseBodyBuilder{

	private static final int END_ADDRESS_END_INDEX = 8;
	private static final int END_ADDRESS_BEGIN_INDEX = 4;

	@Override
	public List<Object> getBody(byte[] bytes, SctpResponseHeader responseHeader) {
		List<Object> list=new ArrayList<Object>();
		ScAddress begin=TypeBuilder.buildScAddress(bytes);
		byte[] endBytes=Arrays.copyOfRange(bytes, END_ADDRESS_BEGIN_INDEX, END_ADDRESS_END_INDEX);
		ScAddress end=TypeBuilder.buildScAddress(bytes);
		list.add(begin);
		list.add(end);
		return list;
	}

}
