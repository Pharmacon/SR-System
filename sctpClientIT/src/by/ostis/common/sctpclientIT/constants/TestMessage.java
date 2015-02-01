package by.ostis.common.sctpclientIT.constants;

public enum TestMessage {
	CONNECTION_FAILED("Connection Failed"), RUN_TEST_PACKAGE("Run test package"), TEST_FAILED(
			"Test failed"), TEST_SUCCESS("Test success");
	private String value;

	private TestMessage(final String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

}
