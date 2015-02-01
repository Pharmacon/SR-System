package by.ostis.common.sctpclientIT.test;

public abstract class AbstractIntegrationTest implements IntegrationTest {
	private int testNumber;
	private String state = "not run";

	public int getTestNumber() {
		return this.testNumber;
	}

	public AbstractIntegrationTest(final int number) {
		this.testNumber = number;
	}

	public String getState() {
		return this.state;
	}

	public void setState(final String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Test number: " + this.testNumber + "State: " + this.state;
	}
}
