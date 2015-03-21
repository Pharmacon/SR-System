package by.ostis.common.sctpclient.model.request;

import by.ostis.common.sctpclient.utils.constants.SctpCommandType;

public class SctpRequestHeader {

    private SctpCommandType commandType;

    private byte flag;

    private int commandId;

    private int argumentSize;

    public SctpRequestHeader() {

    }

    public SctpCommandType getCommandType() {

        return commandType;
    }

    public void setCommandType(SctpCommandType commandType) {

        this.commandType = commandType;
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

    public SctpRequestHeader(SctpCommandType commandType, byte flag, int commandId, int argumentSize) {

        this.commandType = commandType;
        this.flag = flag;
        this.commandId = commandId;
        this.argumentSize = argumentSize;
    }

}