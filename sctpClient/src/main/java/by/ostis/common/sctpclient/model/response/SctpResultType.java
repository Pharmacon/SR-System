package by.ostis.common.sctpclient.model.response;

public enum SctpResultType {

	SCTP_RESULT_OK((byte) 0), SCTP_RESULT_FAIL((byte) 1), SCTP_RESULT_ERROR_NO_ELEMENT(
			(byte) 2);

	private byte code;

	private SctpResultType(byte code) {
		this.code = code;
	}

	public byte getCode() {
		return code;
	}

	public static SctpResultType getByCode(byte result) {
		for (SctpResultType resultType : values()) {
			if (resultType.getCode() == result) {
				return resultType;
			}
		}
		throw new IllegalArgumentException("Unsupported sctpResultType");
	}

}
