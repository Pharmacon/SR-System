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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + argumentSize;
		result = prime * result + commandId;
		result = prime * result
				+ ((commandType == null) ? 0 : commandType.hashCode());
		result = prime * result
				+ ((resultType == null) ? 0 : resultType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SctpResponseHeader other = (SctpResponseHeader) obj;
		if (argumentSize != other.argumentSize)
			return false;
		if (commandId != other.commandId)
			return false;
		if (commandType != other.commandType)
			return false;
		if (resultType != other.resultType)
			return false;
		return true;
	}
    
    

}
