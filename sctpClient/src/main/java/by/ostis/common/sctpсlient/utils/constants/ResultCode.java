package by.ostis.common.sctpсlient.utils.constants;

public enum ResultCode {

    SCTP_RESULT_OK(0x00),
    SCTP_RESULT_FAIL(0x01),
    SCTP_RESULT_ERROR_NO_ELEMENT(0x02);

    private long value;

    private ResultCode(long value) {
        this.value = value;
    }

    public long getValue() {

        return value;
    }

}
