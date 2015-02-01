package by.ostis.common.sctpclientIT.test.testpackages;

import by.ostis.common.sctpclient.client.SctpClient;
import by.ostis.common.sctpclient.client.SctpClientImpl;
import by.ostis.common.sctpclient.model.ScAddress;
import by.ostis.common.sctpclient.model.response.SctpResponse;
import by.ostis.common.sctpclient.model.response.SctpResultType;
import by.ostis.common.sctpclient.utils.constants.ScElementType;
import by.ostis.common.sctpclientIT.constants.TestMessage;
import by.ostis.common.sctpclientIT.test.AbstractIntegrationTest;
import by.ostis.common.sctpclientIT.test.AbstractTestPackage;
import by.ostis.common.sctpclientIT.test.IntegrationTest;

public class DeafaultTestPackage extends AbstractTestPackage {

	private static final String SERVER_URL = "localhost";
	private static final int SERVER_PORT = 555770;

	public DeafaultTestPackage(final String packageName) {
		super(packageName);
	}

	@Override
	public void runTestPackage() {
		for (final IntegrationTest test : this.tests) {
			test.run();
		}
		printTestsState();
	}

	@Override
	protected void setTests() {
		this.tests.add(new AbstractIntegrationTest(1) {

			@Override
			public void run() {
				final SctpClient client = new SctpClientImpl();
				client.init(SERVER_URL, SERVER_PORT);

				SctpResponse response = client
						.createElement(ScElementType.SC_TYPE_NODE);
				if (response.getHeader().getResultType() == SctpResultType.SCTP_RESULT_OK) {
					final ScAddress newElement = (ScAddress) response.getBody()
							.get(0);
					response = client.checkElementExistence(newElement);
					if (response.getHeader().getResultType() == SctpResultType.SCTP_RESULT_FAIL) {
						setState(TestMessage.TEST_FAILED.getValue());
					} else {
						setState(TestMessage.TEST_SUCCESS.getValue());
					}

				} else {
					setState(TestMessage.TEST_FAILED.getValue());
				}
			}
		});
	}
}
