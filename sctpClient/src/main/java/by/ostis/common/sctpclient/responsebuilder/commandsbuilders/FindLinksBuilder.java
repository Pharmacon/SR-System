package by.ostis.common.sctpclient.responsebuilder.commandsbuilders;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import by.ostis.common.sctpclient.model.ScAddress;
import by.ostis.common.sctpclient.model.response.SctpResponseHeader;
import by.ostis.common.sctpclient.model.response.SctpResultType;
import by.ostis.common.sctpclient.responsebuilder.ResponseBodyBuilder;
import by.ostis.common.sctpclient.responsebuilder.TypeBuilder;
import by.ostis.common.sctpclient.utils.constants.ScParameterSize;

public class FindLinksBuilder implements ResponseBodyBuilder{

	
	private static final int LINKS_NUMBER_END_INDEX = 4;
	private static final int LINKS_NUMBER_BEGIN_INDEX = 0;
	
	private static final int LINKS_ADDRESSES_BEGIN_INDEX=4;

	@Override
	public List<Object> getBody(byte[] bytes, SctpResponseHeader responseHeader) {
		List<Object> list=new ArrayList<Object>();
		SctpResultType resultType=responseHeader.getResultType();
		if(SctpResultType.SCTP_RESULT_OK==resultType){
			int linksNumber=getLinkNumbers(bytes);
			Collection<ScAddress> addresses=getAddresses(bytes, linksNumber);
			list.addAll(addresses);
		}
		return list;
	}
	
	private int getLinkNumbers(byte[] bytes){
		ByteBuffer byteBuffer=ByteBuffer.wrap(bytes,LINKS_NUMBER_BEGIN_INDEX, LINKS_NUMBER_END_INDEX);
		byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
		return byteBuffer.getInt();
	}
	
	private Collection<ScAddress> getAddresses(byte[] bytes,int number){
		List<ScAddress> list=new LinkedList<ScAddress>();
		int beginIndex=LINKS_ADDRESSES_BEGIN_INDEX;
		for(int i=0;i<number;i++){
			ScAddress address=TypeBuilder.buildScAddress(bytes, beginIndex);
			list.add(address);
			beginIndex+=ScParameterSize.SC_ADDRESS.getSize();
		}
		return list;
	}

}
