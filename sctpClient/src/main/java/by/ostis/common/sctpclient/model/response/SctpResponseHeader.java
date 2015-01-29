package by.ostis.common.sctpclient.model.response;

import by.ostis.common.sctpclient.utils.constants.SctpCommandType;

public class SctpResponseHeader {

    private SctpCommandType commandType;
    private int commandId;
    private SctpResultType resultType;
    private int argumentSize;

    public SctpResponseHeader() {
    }

    public SctpCommandType getCommandType() {
	return commandType;
    }

    public int getCommandId() {
	return commandId;
    }

    public int getArgumentSize() {
	return argumentSize;
    }

    public void setCommandType(SctpCommandType commandType) {
	this.commandType = commandType;
    }

    public void setCommandId(int commandId) {
	this.commandId = commandId;
    }

    public void setArgumentSize(int argumentSize) {
	this.argumentSize = argumentSize;
    }

    public SctpResultType getResultType() {
	return resultType;
    }

    public void setResultType(SctpResultType resultType) {
	this.resultType = resultType;
    }

    @Override
    public String toString() {
	return "SctpResponseHeader [commandType=" + commandType.name() + ", commandId=" + commandId
		+ ", resultType=" + resultType.name() + ", argumentSize=" + argumentSize + "]";
    }

}
