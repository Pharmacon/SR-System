package by.ostis.common.sctpclient.responsebuilder.commandsbuilders;

import java.util.ArrayList;
import java.util.List;

import by.ostis.common.sctpclient.model.ScAddress;
import by.ostis.common.sctpclient.model.response.SctpResponseHeader;
import by.ostis.common.sctpclient.model.response.SctpResultType;
import by.ostis.common.sctpclient.responsebuilder.ResponseBodyBuilder;
import by.ostis.common.sctpclient.responsebuilder.TypeBuilder;


/**
 * Return ScAddress if result type is OK
 */
public class AddressWhenSuccessBuilder implements ResponseBodyBuilder{

	@Override
	public List<Object> getBody(byte[] bytes, SctpResponseHeader responseHeader) {
		List<Object> list=new ArrayList<Object>();
		SctpResultType resultType=responseHeader.getResultType();
		if(SctpResultType.SCTP_RESULT_OK==resultType){
			ScAddress scAddress=TypeBuilder.buildScAddress(bytes);
			list.add(scAddress);
		}
		return list;
	}
	

}
