package by.ostis.common.sctpсlient.model.request;

import by.ostis.common.sctpсlient.utils.constants.SctpCommandType;

public enum RequestHeaderType {

	CHECK_ELEMENT_COMMAND(SctpCommandType.CHECK_ELEMENT, (byte)0, 0);
	
	private SctpCommandType commandType;
	
	private byte flag;
	
	private int commandId;
	

	private RequestHeaderType(SctpCommandType commandType, byte flag,
			int commandId) {
		this.commandType = commandType;
		this.flag = flag;
		this.commandId = commandId;
	}
	
	public SctpRequestHeader getRequestHeader(){
		SctpRequestHeader header = new SctpRequestHeader();
		header.setCommandType(commandType);
		header.setCommandId(commandId);
		header.setFlag(flag);
		return header;
	}
	
	
}
