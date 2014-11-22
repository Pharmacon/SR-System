package by.ostis.common.utils;

public enum CommandType {

    SCTP_CMD_UKNOWN(0x00);

    private long value;

    private CommandType(long value) {

        this.value = value;
    }

    public long getValue() {

        return value;
    }

}
