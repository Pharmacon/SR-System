package by.ostis.common.sctpclientIT.test.testpackages;

import by.ostis.common.sctpclient.client.SctpClient;
import by.ostis.common.sctpclient.client.SctpClientImpl;
import by.ostis.common.sctpclient.model.ScAddress;
import by.ostis.common.sctpclient.model.ScString;
import by.ostis.common.sctpclient.model.response.SctpResponse;
import by.ostis.common.sctpclient.model.response.SctpResultType;
import by.ostis.common.sctpclient.utils.constants.ScElementType;
import by.ostis.common.sctpclientIT.constants.TestMessage;
import by.ostis.common.sctpclientIT.test.AbstractIntegrationTest;
import by.ostis.common.sctpclientIT.test.AbstractTestPackage;
import by.ostis.common.sctpclientIT.test.IntegrationTest;

/**
 * @author Andrew Nepogoda Feb 15, 2015
 */
public class DeafaultTestPackage extends AbstractTestPackage {

    private static final String SERVER_URL  = "localhost";

    private static final int    SERVER_PORT = 555770;

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

        this.tests.add(new AbstractIntegrationTest(
                "check create element method") {

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
        this.tests.add(new AbstractIntegrationTest(
                "check delete element method") {

            @Override
            public void run() {

                final SctpClient client = new SctpClientImpl();
                client.init(SERVER_URL, SERVER_PORT);
                SctpResponse response = client
                        .createElement(ScElementType.SC_TYPE_NODE);
                if (response.getHeader().getResultType() == SctpResultType.SCTP_RESULT_OK) {
                    final ScAddress newElement = (ScAddress) response.getBody()
                            .get(0);
                    response = client.deleteElement(newElement);
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
        this.tests.add(new AbstractIntegrationTest("check create arc method") {

            @Override
            public void run() {

                final SctpClient client = new SctpClientImpl();
                client.init(SERVER_URL, SERVER_PORT);

                SctpResponse node1Resp = client
                        .createElement(ScElementType.SC_TYPE_NODE);
                SctpResponse node2Resp = client
                        .createElement(ScElementType.SC_TYPE_NODE);

                if ((node1Resp.getHeader().getResultType() == SctpResultType.SCTP_RESULT_OK)
                        && (node2Resp.getHeader().getResultType() == SctpResultType.SCTP_RESULT_OK)) {

                    final ScAddress node1 = (ScAddress) node1Resp.getBody()
                            .get(0);
                    final ScAddress node2 = (ScAddress) node2Resp.getBody()
                            .get(0);
                    SctpResponse arcResp = client.createScArc(
                            ScElementType.SC_TYPE_ARC_ACCESS, node1, node2);
                    if (arcResp.getHeader().getResultType() == SctpResultType.SCTP_RESULT_FAIL) {
                        setState(TestMessage.TEST_FAILED.getValue());
                    } else {
                        setState(TestMessage.TEST_SUCCESS.getValue());
                    }

                } else {
                    setState(TestMessage.TEST_FAILED.getValue());
                }
            }
        });
        this.tests.add(new AbstractIntegrationTest(
                "check create sc link method") {

            @Override
            public void run() {

                final SctpClient client = new SctpClientImpl();
                client.init(SERVER_URL, SERVER_PORT);
                SctpResponse response = client.createScLink();
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
        this.tests.add(new AbstractIntegrationTest(
                "check search element by sys_idtf method") {

            @Override
            public void run() {

                final SctpClient client = new SctpClientImpl();
                client.init(SERVER_URL, SERVER_PORT);
                SctpResponse response = client.searchElement(new ScString(
                        "lang_ru"));
                if (response.getHeader().getResultType() == SctpResultType.SCTP_RESULT_OK) {
                    final ScAddress newElement = (ScAddress) response.getBody()
                            .get(0);
                    setState(TestMessage.TEST_SUCCESS.getValue() + "ScAdress:"
                            + newElement.toString());
                } else {
                    setState(TestMessage.TEST_FAILED.getValue());
                }
            }
        });
        this.tests.add(new AbstractIntegrationTest(
                "check arc begin & end method") {

            @Override
            public void run() {

                final SctpClient client = new SctpClientImpl();
                client.init(SERVER_URL, SERVER_PORT);

                SctpResponse node1Resp = client
                        .createElement(ScElementType.SC_TYPE_NODE);
                SctpResponse node2Resp = client
                        .createElement(ScElementType.SC_TYPE_NODE);

                if ((node1Resp.getHeader().getResultType() == SctpResultType.SCTP_RESULT_OK)
                        && (node2Resp.getHeader().getResultType() == SctpResultType.SCTP_RESULT_OK)) {

                    final ScAddress node1 = (ScAddress) node1Resp.getBody()
                            .get(0);
                    final ScAddress node2 = (ScAddress) node2Resp.getBody()
                            .get(0);
                    SctpResponse arcResp = client.createScArc(
                            ScElementType.SC_TYPE_ARC_ACCESS, node1, node2);
                    if (arcResp.getHeader().getResultType() == SctpResultType.SCTP_RESULT_OK) {
                        final ScAddress arc = (ScAddress) arcResp.getBody()
                                .get(0);
                        SctpResponse bedEndResp = client.getArcBeginAndEnd(arc);
                        if (bedEndResp.getHeader().getResultType() == SctpResultType.SCTP_RESULT_OK) {
                            final ScAddress begElement = (ScAddress) bedEndResp
                                    .getBody().get(0);
                            final ScAddress endElement = (ScAddress) bedEndResp
                                    .getBody().get(1);
                            setState(TestMessage.TEST_SUCCESS.getValue()
                                    + "Arc begin:" + begElement.toString()
                                    + " Arc end:" + endElement.toString());
                        } else {
                            setState(TestMessage.TEST_FAILED.getValue());
                        }

                    } else {
                        setState(TestMessage.TEST_FAILED.getValue());
                    }

                } else {
                    setState(TestMessage.TEST_FAILED.getValue());
                }

            }
        });
        this.tests
                .add(new AbstractIntegrationTest("check search links method") {

                    @Override
                    public void run() {

                        final SctpClient client = new SctpClientImpl();
                        client.init(SERVER_URL, SERVER_PORT);
                        SctpResponse response = client
                                .searchScLinks(new ScString("Русский язык"));
                        if (response.getHeader().getResultType() == SctpResultType.SCTP_RESULT_OK) {
                            final int linksCount = response.getBody().size();
                            setState(TestMessage.TEST_SUCCESS.getValue()
                                    + " Links count: " + linksCount);
                        } else {
                            setState(TestMessage.TEST_FAILED.getValue());
                        }
                    }
                });
        this.tests
                .add(new AbstractIntegrationTest("check search links method") {

                    @Override
                    public void run() {

                        final SctpClient client = new SctpClientImpl();
                        client.init(SERVER_URL, SERVER_PORT);
                        SctpResponse response = client
                                .searchScLinks(new ScString("Русский язык"));
                        if (response.getHeader().getResultType() == SctpResultType.SCTP_RESULT_OK) {
                            final int linksCount = response.getBody().size();
                            setState(TestMessage.TEST_SUCCESS.getValue()
                                    + " Links count: " + linksCount);
                        } else {
                            setState(TestMessage.TEST_FAILED.getValue());
                        }
                    }
                });
        this.tests
                .add(new AbstractIntegrationTest("check set sys_idtf method") {

                    @Override
                    public void run() {

                        final SctpClient client = new SctpClientImpl();
                        client.init(SERVER_URL, SERVER_PORT);
                        SctpResponse nodeResp = client
                                .createElement(ScElementType.SC_TYPE_NODE);
                        if (nodeResp.getHeader().getResultType() == SctpResultType.SCTP_RESULT_OK) {
                            final ScAddress newElement = (ScAddress) nodeResp
                                    .getBody().get(0);
                            String idtf = "test_idtf";
                            SctpResponse setSysIdtfResp = client
                                    .setSystemIdentifier(newElement,
                                            new ScString(idtf));
                            if (setSysIdtfResp.getHeader().getResultType() == SctpResultType.SCTP_RESULT_OK) {

                                SctpResponse getElementResp = client
                                        .searchElement(new ScString(idtf));
                                if ((getElementResp.getHeader().getResultType() == SctpResultType.SCTP_RESULT_OK)
                                        && newElement
                                                .equals((ScAddress) getElementResp
                                                        .getBody().get(0))) {
                                    setState(TestMessage.TEST_SUCCESS
                                            .getValue());
                                } else {
                                    setState(TestMessage.TEST_FAILED.getValue());
                                }
                                setState(TestMessage.TEST_SUCCESS.getValue());
                            } else {
                                setState(TestMessage.TEST_FAILED.getValue());
                            }

                        } else {
                            setState(TestMessage.TEST_FAILED.getValue());
                        }
                    }
                });
        this.tests.add(new AbstractIntegrationTest(
                "check get & set link content methods") {

            @Override
            public void run() {

                final SctpClient client = new SctpClientImpl();
                client.init(SERVER_URL, SERVER_PORT);
                SctpResponse response = client.createScLink();
                if (response.getHeader().getResultType() == SctpResultType.SCTP_RESULT_OK) {
                    ScAddress link = (ScAddress) response.getBody().get(0);
                    ScString content = new ScString("test content");
                    response = client.setScRefContent(link, content);
                    if (response.getHeader().getResultType() == SctpResultType.SCTP_RESULT_OK) {
                        response = client.getScLinkContent(link);
                        if ((response.getHeader().getResultType() == SctpResultType.SCTP_RESULT_OK)
                                && content.equals((ScString) response.getBody()
                                        .get(0))) {
                            setState(TestMessage.TEST_SUCCESS.getValue());
                        } else {
                            setState(TestMessage.TEST_FAILED.getValue());
                        }
                    } else {
                        setState(TestMessage.TEST_FAILED.getValue());
                    }
                } else {
                    setState(TestMessage.TEST_FAILED.getValue());
                }
            }
        });
    }
}
