package by.ostis.common.sctpclient.responsebuilder.commandsbuilders;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

import by.ostis.common.sctpclient.model.response.SctpResponseHeader;
import by.ostis.common.sctpclient.model.response.SctpResultType;
import by.ostis.common.sctpclient.responsebuilder.ResponseBodyBuilder;
import by.ostis.common.sctpclient.utils.constants.SctpCommandType;

public class IdWhenSuccessBuilder implements ResponseBodyBuilder {

	@Override
	public List<Object> getBody(byte[] bytes, SctpResponseHeader responseHeader) {
		List<Object> list=new ArrayList<Object>();
		SctpResultType resultType=responseHeader.getResultType();
		if(SctpResultType.SCTP_RESULT_OK==resultType){
			ByteBuffer byteBuffer=ByteBuffer.wrap(bytes);
			byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
			int id=byteBuffer.getInt();
			list.add(id);
		}
		return list;
	}

}
