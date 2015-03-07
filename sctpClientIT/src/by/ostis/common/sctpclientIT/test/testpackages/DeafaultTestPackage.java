package by.ostis.common.sctpclientIT.test.testpackages;

import java.util.List;

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

    private static final int    SERVER_PORT = 55770;

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
                SctpResponse<ScAddress> createResponse = client
                        .createElement(ScElementType.SC_TYPE_NODE);
                ScAddress newElement = createResponse.getAnswer();
                if (null != newElement) {
                    SctpResponse<Boolean> checkExResponse = client
                            .checkElementExistence(newElement);
                    if (checkExResponse.getAnswer()) {
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
                "check delete element method") {

            @Override
            public void run() {

                final SctpClient client = new SctpClientImpl();
                client.init(SERVER_URL, SERVER_PORT);
                SctpResponse<ScAddress> createResponse = client
                        .createElement(ScElementType.SC_TYPE_NODE);
                ScAddress newElement = createResponse.getAnswer();
                if (null != newElement) {
                    SctpResponse<Boolean> deleteResponse = client
                            .deleteElement(newElement);
                    if (deleteResponse.getAnswer()) {
                        setState(TestMessage.TEST_SUCCESS.getValue());
                    } else {
                        setState(TestMessage.TEST_FAILED.getValue());
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

                SctpResponse<ScAddress> node1Resp = client
                        .createElement(ScElementType.SC_TYPE_NODE);
                SctpResponse<ScAddress> node2Resp = client
                        .createElement(ScElementType.SC_TYPE_NODE);
                final ScAddress node1 = node1Resp.getAnswer();
                final ScAddress node2 = node2Resp.getAnswer();
                if (null != node1 && null != node2) {

                    SctpResponse<ScAddress> arcResp = client.createScArc(
                            ScElementType.SC_TYPE_ARC_ACCESS, node1, node2);
                    if (null != arcResp.getAnswer()) {
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
                "check create sc link method") {

            @Override
            public void run() {

                final SctpClient client = new SctpClientImpl();
                client.init(SERVER_URL, SERVER_PORT);
                SctpResponse<ScAddress> createResponse = client.createScLink();
                final ScAddress newElement = (ScAddress) createResponse
                        .getAnswer();
                if (null != newElement) {

                    SctpResponse<Boolean> checkExResponse = client
                            .checkElementExistence(newElement);
                    if (checkExResponse.getAnswer()) {
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
                "check search element by sys_idtf method") {

            @Override
            public void run() {

                final SctpClient client = new SctpClientImpl();
                client.init(SERVER_URL, SERVER_PORT);
                SctpResponse<ScAddress> response = client
                        .searchElement(new ScString("lang_en"));
                final ScAddress newElement = response.getAnswer();
                if (null != newElement) {
                    setState(TestMessage.TEST_SUCCESS.getValue() + " ScAdress:"
                            + newElement.toString());
                } else {
                    setState(TestMessage.TEST_FAILED.getValue());
                }
            }
        });
        /*
         * need to add functionality at client for this test this.tests.add(new
         * AbstractIntegrationTest( "check arc begin & end method") {
         * 
         * @Override public void run() {
         * 
         * final SctpClient client = new SctpClientImpl();
         * client.init(SERVER_URL, SERVER_PORT);
         * 
         * SctpResponse<ScAddress> node1Resp = client
         * .createElement(ScElementType.SC_TYPE_NODE); SctpResponse<ScAddress>
         * node2Resp = client .createElement(ScElementType.SC_TYPE_NODE);
         * 
         * if ((node1Resp.getHeader().getResultType() ==
         * SctpResultType.SCTP_RESULT_OK) &&
         * (node2Resp.getHeader().getResultType() ==
         * SctpResultType.SCTP_RESULT_OK)) {
         * 
         * final ScAddress node1 = node1Resp.getAnswer(); final ScAddress node2
         * = node2Resp.getAnswer(); SctpResponse<ScAddress> arcResp =
         * client.createScArc( ScElementType.SC_TYPE_ARC_ACCESS, node1, node2);
         * if (arcResp.getHeader().getResultType() ==
         * SctpResultType.SCTP_RESULT_OK) { final ScAddress arc = (ScAddress)
         * arcResp.getAnswer(); SctpResponse<Void> begEndResp =
         * client.getArcBeginAndEnd(arc); if
         * (begEndResp.getHeader().getResultType() ==
         * SctpResultType.SCTP_RESULT_OK) { final ScAddress begElement =
         * begEndResp.getAnswer(); final ScAddress endElement =
         * begEndResp.getAnswer(); setState(TestMessage.TEST_SUCCESS.getValue()
         * + "Arc begin:" + begElement.toString() + " Arc end:" +
         * endElement.toString()); } else {
         * setState(TestMessage.TEST_FAILED.getValue()); }
         * 
         * } else { setState(TestMessage.TEST_FAILED.getValue()); }
         * 
         * } else { setState(TestMessage.TEST_FAILED.getValue()); }
         * 
         * } });
         */
        this.tests
                .add(new AbstractIntegrationTest("check search links method") {

                    @Override
                    public void run() {

                        final SctpClient client = new SctpClientImpl();
                        client.init(SERVER_URL, SERVER_PORT);
                        SctpResponse<List<ScAddress>> response = client
                                .searchScLinks(new ScString("Русский язык"));
                        if (response.getHeader().getResultType() == SctpResultType.SCTP_RESULT_OK) {
                            final int linksCount = response.getAnswer().size();
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
                        SctpResponse<ScAddress> nodeResp = client
                                .createElement(ScElementType.SC_TYPE_NODE);
                        final ScAddress newElement = nodeResp.getAnswer();
                        if (null != newElement) {
                            
                            String idtf = "test_idtf";
                            SctpResponse<Boolean> setSysIdtfResp = client
                                    .setSystemIdentifier(newElement,
                                            new ScString(idtf));
                            if (setSysIdtfResp.getAnswer()) {

                                SctpResponse<ScAddress> getElementResp = client
                                        .searchElement(new ScString(idtf));
                                ScAddress reloadElem = getElementResp.getAnswer();
                                if (null != reloadElem && newElement.equals(reloadElem)) {
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
                SctpResponse<ScAddress> createResponse = client.createScLink();
                if (createResponse.getHeader().getResultType() == SctpResultType.SCTP_RESULT_OK) {
                    ScAddress link = (ScAddress) createResponse.getAnswer();
                    ScString content = new ScString("test content");
                    SctpResponse<Boolean> setContentResponse = client
                            .setScRefContent(link, content);
                    if (setContentResponse.getAnswer()) {
                        SctpResponse<String> getContentResponse = client
                                .getScLinkContent(link);
                        if ((setContentResponse.getHeader().getResultType() == SctpResultType.SCTP_RESULT_OK)
                                && content.equals(getContentResponse
                                        .getAnswer())) {
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
