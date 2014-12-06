package by.ostis.common.sctpсlient.model;

import by.ostis.common.sctpсlient.utils.SctpCommandType;

public class SctpRequest {

	private SctpCommandType commandaType;
	
	private byte flag;
	
	private int commandId;
	
	private int argumentSize;

	public SctpCommandType getCommandaType() {
		return commandaType;
	}

	public void setCommandaType(SctpCommandType commandaType) {
		this.commandaType = commandaType;
	}

	public byte getFlag() {
		return flag;
	}

	public void setFlag(byte flag) {
		this.flag = flag;
	}

	public int getCommandId() {
		return commandId;
	}

	public void setCommandId(int commandId) {
		this.commandId = commandId;
	}

	public int getArgumentSize() {
		return argumentSize;
	}

	public void setArgumentSize(int argumentSize) {
		this.argumentSize = argumentSize;
	}
	
	
	
}
