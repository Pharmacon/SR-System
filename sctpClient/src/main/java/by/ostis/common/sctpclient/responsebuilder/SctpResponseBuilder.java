package by.ostis.common.sctpclient.responsebuilder;


import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.ostis.common.sctpclient.model.response.SctpResponse;
import by.ostis.common.sctpclient.model.response.SctpResponseHeader;
import by.ostis.common.sctpclient.model.response.SctpResultType;
import by.ostis.common.sctpclient.responsebuilder.commandsbuilders.ElementTypeBuilder;
import by.ostis.common.sctpclient.responsebuilder.commandsbuilders.EmptyResponseBodyBuider;
import by.ostis.common.sctpclient.responsebuilder.commandsbuilders.FindLinksBuilder;
import by.ostis.common.sctpclient.responsebuilder.commandsbuilders.GetArcBuilder;
import by.ostis.common.sctpclient.responsebuilder.commandsbuilders.IdWhenSuccessBuilder;
import by.ostis.common.sctpclient.responsebuilder.commandsbuilders.LinkContentBuilder;
import by.ostis.common.sctpclient.responsebuilder.commandsbuilders.AddressWhenSuccessBuilder;
import by.ostis.common.sctpclient.utils.constants.SctpCommandType;

public class SctpResponseBuilder {
	
	private static final int COMMAND_TYPE_CODE_INDEX=0;
	private static final int ID_BEGIN_INDEX=1;
	private static final int ID_BYTE_SIZE=4;
	private static final int RESULT_TYPE_CODE_INDEX=5;
	private static final int SIZE_BEGIN_INDEX=6;
	private static final int SIZE_BYTE_SIZE=4;
	private static final int PARAMETERS_BEGIN_INDEX=10;
	
	private byte[] bytes;
	private Map<SctpCommandType,ResponseBodyBuilder> builders=new HashMap<>();
	
	
	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public Map<SctpCommandType, ResponseBodyBuilder> getBuilders() {
		return builders;
	}

	public void setBuilders(Map<SctpCommandType, ResponseBodyBuilder> builders) {
		this.builders = builders;
	}

	
	public SctpResponseBuilder() {
		//0x01
		builders.put(SctpCommandType.CHECK_ELEMENT, new EmptyResponseBodyBuider());
		//TODO: 0x02  Ask ElementTypes and add to ScElementType enum
		builders.put(SctpCommandType.GET_ELEMENT_TYPE, new ElementTypeBuilder());
		//0x03
		builders.put(SctpCommandType.ERASE_ELEMENT, new EmptyResponseBodyBuider());
		//0x04
		builders.put(SctpCommandType.CREATE_NODE, new AddressWhenSuccessBuilder());
		//0x05
		builders.put(SctpCommandType.CREATE_LINK, new AddressWhenSuccessBuilder());
		//0x06
		builders.put(SctpCommandType.CREATE_ARC, new AddressWhenSuccessBuilder());
		//0x07
		builders.put(SctpCommandType.GET_ARC,new GetArcBuilder());
		//TODO: 0x08 - underfined on sc-machine wiki
		//0x09
		builders.put(SctpCommandType.GET_LINK_CONTENT,new LinkContentBuilder());
		//0x0a
		builders.put(SctpCommandType.FIND_LINKS, new FindLinksBuilder());
		//0x0b
		builders.put(SctpCommandType.SET_LINK_CONTENT,new EmptyResponseBodyBuider());
		//TODO: 0x0c
		//TODO: 0x0d
		//0x0e
		builders.put(SctpCommandType.EVENT_CREATE, new IdWhenSuccessBuilder());
		//0x0f
		builders.put(SctpCommandType.EVENT_DESTROY,new IdWhenSuccessBuilder());
		//TODO: 0x10
		//0xa0
		builders.put(SctpCommandType.FIND_ELEMENT_BY_SYSITDF, new AddressWhenSuccessBuilder());
		//0xa1
		builders.put(SctpCommandType.SET_SYSIDTF, new EmptyResponseBodyBuider());
		//TODO:0xa2
		//TODO:0xa3 ask version encoding
		
	}

	public SctpResponse buildSctpResponse(byte[] bytes){
		this.bytes = bytes; 
		SctpResponse response=new SctpResponse();
		SctpResponseHeader header=new SctpResponseHeader();
		header.setCommandId(getId());
		header.setResultType(getSctpResultType());
		header.setArgumentSize(getSize());
		header.setCommandType(getCommandType());
		response.setHeader(header);
		response.setBody(getResponseBody(header));
		return response;
	}
	
	private  SctpCommandType getCommandType(){
		byte code=bytes[COMMAND_TYPE_CODE_INDEX];
		for(SctpCommandType commandType:SctpCommandType.values()){
			if(commandType.getValue()==code){
				return commandType;
			}
		}
		return null;
	}

	private SctpResultType getSctpResultType(){
		byte result=bytes[RESULT_TYPE_CODE_INDEX];
		for(SctpResultType resultType:SctpResultType.values()){
			if(resultType.getCode()==result){
				return resultType;
			}
		}
		return null;
	}
	
	private int getId(){
		return getIntFromBytes(ID_BEGIN_INDEX, ID_BEGIN_INDEX+ID_BYTE_SIZE);
	}
	
	
	private int getSize(){
		return getIntFromBytes(SIZE_BEGIN_INDEX,SIZE_BEGIN_INDEX+ SIZE_BYTE_SIZE);
	}
	
	private int getIntFromBytes(int startIndex,int endIndex){
		ByteBuffer byteBuffer=ByteBuffer.wrap(bytes,startIndex, endIndex);
		byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
		return byteBuffer.getInt();	
	}
	
	
	private List<Object> getResponseBody(SctpResponseHeader header){
		SctpCommandType commandType=header.getCommandType();
		byte[] parameterBytes=Arrays.copyOfRange(bytes, PARAMETERS_BEGIN_INDEX,PARAMETERS_BEGIN_INDEX+header.getArgumentSize());
		ResponseBodyBuilder bodyBuider=builders.get(commandType);
		return bodyBuider.getBody(parameterBytes, header);
	}
}
