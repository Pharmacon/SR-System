package by.ostis.common.sctpclientIT.test.testpackages;

import java.util.List;

import by.ostis.common.sctpclient.client.SctpClient;
import by.ostis.common.sctpclient.client.SctpClientImpl;
import by.ostis.common.sctpclient.model.ScAddress;
import by.ostis.common.sctpclient.model.ScString;
import by.ostis.common.sctpclient.model.response.SctpResponse;
import by.ostis.common.sctpclient.utils.constants.ScElementType;
import by.ostis.common.sctpclientIT.constants.TestMessage;
import by.ostis.common.sctpclientIT.test.AbstractIntegrationTest;
import by.ostis.common.sctpclientIT.test.AbstractTestPackage;
import by.ostis.common.sctpclientIT.test.IntegrationTest;

/**
 * @author Andrew Nepogoda Feb 15, 2015
 */
public class DeafaultTestPackage extends AbstractTestPackage {

    private static final String SERVER_URL = "localhost";

    private static final int SERVER_PORT = 55770;

    public DeafaultTestPackage(final String packageName) {

        super(packageName);
    }

    @Override
    public void runTestPackage() {

        for (final IntegrationTest test : this.tests) {
            try {
                test.run();
            } catch (Exception e) {

            }
        }
        printTestsState();
    }

    @Override
    protected void setTests() {

        this.tests.add(new AbstractIntegrationTest("check create element method") {

            @Override
            public void run() {

                try {
                    final SctpClient client = new SctpClientImpl();
                    client.init(SERVER_URL, SERVER_PORT);
                    SctpResponse<ScAddress> createResponse = client
                            .createElement(ScElementType.SC_TYPE_NODE);
                    if (!createResponse.isEmpty()) {
                        ScAddress newElement = createResponse.getAnswer();
                        setState(TestMessage.TEST_SUCCESS.getValue());
                        setTestMessage(newElement.toString());
                    } else {
                        setState(TestMessage.TEST_FAILED.getValue());
                        setTestMessage(TestMessage.EMPTY_RESPONSE.getValue());
                    }
                } catch (Exception e) {
                    setState(TestMessage.TEST_FAILED.getValue());
                    setTestMessage(e.getStackTrace().toString());
                }
            }
        });
        this.tests.add(new AbstractIntegrationTest("check delete element method") {

            @Override
            public void run() {

                try {
                    final SctpClient client = new SctpClientImpl();
                    client.init(SERVER_URL, SERVER_PORT);
                    SctpResponse<ScAddress> createResponse = client
                            .createElement(ScElementType.SC_TYPE_NODE);
                    if (!createResponse.isEmpty()) {
                        SctpResponse<Boolean> deleteResponse = client.deleteElement(createResponse
                                .getAnswer());
                        if (deleteResponse.getAnswer()) {
                            setState(TestMessage.TEST_SUCCESS.getValue());
                        } else {
                            setState(TestMessage.TEST_FAILED.getValue());
                            setTestMessage("Node isn't delete");
                        }

                    } else {
                        setState(TestMessage.TEST_FAILED.getValue());
                        setTestMessage(TestMessage.EMPTY_RESPONSE.getValue()
                                + " while create test node");
                    }
                } catch (Exception e) {
                    setState(TestMessage.TEST_FAILED.getValue());
                    setTestMessage(e.getStackTrace().toString());
                }
            }
        });
        this.tests.add(new AbstractIntegrationTest("check create arc method") {

            @Override
            public void run() {

                try {
                    final SctpClient client = new SctpClientImpl();
                    client.init(SERVER_URL, SERVER_PORT);

                    SctpResponse<ScAddress> node1Resp = client
                            .createElement(ScElementType.SC_TYPE_NODE);
                    SctpResponse<ScAddress> node2Resp = client
                            .createElement(ScElementType.SC_TYPE_NODE);

                    if (!node1Resp.isEmpty() && !node2Resp.isEmpty()) {
                        final ScAddress node1 = node1Resp.getAnswer();
                        final ScAddress node2 = node2Resp.getAnswer();
                        SctpResponse<ScAddress> arcResp = client.createScArc(
                                ScElementType.SC_TYPE_ARC_ACCESS, node1, node2);
                        if (!arcResp.isEmpty()) {
                            setState(TestMessage.TEST_SUCCESS.getValue());
                            setTestMessage("Triple: node1: " + node1 + " arc: "
                                    + arcResp.getAnswer() + " node2: " + node2);
                        } else {
                            setState(TestMessage.TEST_FAILED.getValue());
                            setTestMessage(TestMessage.EMPTY_RESPONSE.getValue()
                                    + " while create test arc");
                        }

                    } else {
                        setState(TestMessage.TEST_FAILED.getValue());
                        setTestMessage(TestMessage.EMPTY_RESPONSE.getValue()
                                + " while create test node");
                    }
                } catch (Exception e) {
                    setState(TestMessage.TEST_FAILED.getValue());
                    setTestMessage(e.getStackTrace().toString());
                }
            }
        });
        this.tests.add(new AbstractIntegrationTest("check create sc link method") {

            @Override
            public void run() {

                try {
                    final SctpClient client = new SctpClientImpl();
                    client.init(SERVER_URL, SERVER_PORT);
                    SctpResponse<ScAddress> createResponse = client.createScLink();
                    if (!createResponse.isEmpty()) {
                        final ScAddress newElement = createResponse.getAnswer();
                        setState(TestMessage.TEST_SUCCESS.getValue());
                        setTestMessage(newElement.toString());
                    } else {
                        setState(TestMessage.TEST_FAILED.getValue());
                        setTestMessage(TestMessage.EMPTY_RESPONSE.getValue());
                    }

                } catch (Exception e) {
                    setState(TestMessage.TEST_FAILED.getValue());
                    setTestMessage(e.getStackTrace().toString());
                }
            }
        });
        this.tests.add(new AbstractIntegrationTest("check arc begin & end method") {

            @Override
            public void run() {

                try {
                    final SctpClient client = new SctpClientImpl();
                    client.init(SERVER_URL, SERVER_PORT);

                    SctpResponse<ScAddress> node1Resp = client
                            .createElement(ScElementType.SC_TYPE_NODE);
                    SctpResponse<ScAddress> node2Resp = client
                            .createElement(ScElementType.SC_TYPE_NODE);

                    if (!node1Resp.isEmpty() && !node2Resp.isEmpty()) {

                        final ScAddress node1 = node1Resp.getAnswer();
                        final ScAddress node2 = node2Resp.getAnswer();
                        SctpResponse<ScAddress> arcResp = client.createScArc(
                                ScElementType.SC_TYPE_ARC_ACCESS, node1, node2);
                        if (!arcResp.isEmpty()) {
                            final ScAddress arc = arcResp.getAnswer();
                            SctpResponse<List<ScAddress>> begEndResp = client
                                    .getArcBeginAndEnd(arc);
                            if (!begEndResp.isEmpty()) {
                                final ScAddress begElement = begEndResp.getAnswer().get(0);
                                final ScAddress endElement = begEndResp.getAnswer().get(1);
                                if (begElement.equals(node1) && endElement.equals(node2)) {
                                    setState(TestMessage.TEST_SUCCESS.getValue());
                                    setTestMessage("Arc begin:" + begElement + " Arc end:"
                                            + endElement);
                                } else {
                                    setState(TestMessage.TEST_FAILED.getValue());
                                    setTestMessage("Arc begin:" + begElement + " Arc end:"
                                            + endElement + " and node1:" + node1 + " node2:"
                                            + node2 + " not equals");
                                }
                            } else {
                                setState(TestMessage.TEST_FAILED.getValue());
                                setTestMessage(TestMessage.EMPTY_RESPONSE
                                        + " while get arc begin and end");
                            }

                        } else {
                            setState(TestMessage.TEST_FAILED.getValue());
                            setTestMessage(TestMessage.EMPTY_RESPONSE.getValue()
                                    + " while create test arc");
                        }

                    } else {
                        setState(TestMessage.TEST_FAILED.getValue());
                        setTestMessage(TestMessage.EMPTY_RESPONSE.getValue()
                                + " while create test node");
                    }
                } catch (Exception e) {
                    setState(TestMessage.TEST_FAILED.getValue());
                    setTestMessage(e.getStackTrace().toString());
                }

            }
        });
        this.tests.add(new AbstractIntegrationTest("check search element by sys_idtf method") {

            @Override
            public void run() {

                try {
                    final SctpClient client = new SctpClientImpl();
                    client.init(SERVER_URL, SERVER_PORT);
                    SctpResponse<ScAddress> response = client
                            .searchElement(new ScString("lang_en"));
                    if (!response.isEmpty()) {
                        final ScAddress newElement = response.getAnswer();
                        setState(TestMessage.TEST_SUCCESS.getValue());
                        setTestMessage(newElement.toString());
                    } else {
                        setState(TestMessage.TEST_FAILED.getValue());
                        setTestMessage(TestMessage.EMPTY_RESPONSE.getValue());
                    }
                } catch (Exception e) {
                    setState(TestMessage.TEST_FAILED.getValue());
                    setTestMessage(e.getStackTrace().toString());
                }
            }
        });
        this.tests.add(new AbstractIntegrationTest("check search links method") {

            @Override
            public void run() {

                try {
                    final SctpClient client = new SctpClientImpl();
                    client.init(SERVER_URL, SERVER_PORT);
                    SctpResponse<List<ScAddress>> response = client.searchScLinks(new ScString(
                            "Русский язык"));
                    if (!response.isEmpty()) {
                        final int linksCount = response.getAnswer().size();
                        setState(TestMessage.TEST_SUCCESS.getValue());
                        setTestMessage("Links count: " + linksCount);
                    } else {
                        setState(TestMessage.TEST_FAILED.getValue());
                        setTestMessage(TestMessage.EMPTY_RESPONSE.getValue());
                    }
                } catch (Exception e) {
                    setState(TestMessage.TEST_FAILED.getValue());
                    setTestMessage(e.getStackTrace().toString());
                }
            }
        });
        this.tests.add(new AbstractIntegrationTest("check set sys_idtf method") {

            @Override
            public void run() {

                try {
                    final SctpClient client = new SctpClientImpl();
                    client.init(SERVER_URL, SERVER_PORT);
                    SctpResponse<ScAddress> nodeResp = client
                            .createElement(ScElementType.SC_TYPE_NODE);
                    if (!nodeResp.isEmpty()) {
                        final ScAddress newElement = nodeResp.getAnswer();
                        String idtf = "test1_idtf";
                        SctpResponse<Boolean> setSysIdtfResp = client.setSystemIdentifier(
                                newElement, new ScString(idtf));
                        if (setSysIdtfResp.getAnswer()) {

                            SctpResponse<ScAddress> getElementResp = client
                                    .searchElement(new ScString(idtf));
                            ScAddress reloadElem = getElementResp.getAnswer();
                            if (!getElementResp.isEmpty() && newElement.equals(reloadElem)) {
                                setState(TestMessage.TEST_SUCCESS.getValue());
                                setTestMessage("Element with address:" + newElement + "has idtf:"
                                        + idtf);
                            } else {
                                setState(TestMessage.TEST_FAILED.getValue());
                                setTestMessage("Elemt with idtf:" + idtf + "does not exist");
                            }
                        } else {
                            setState(TestMessage.TEST_FAILED.getValue());
                            setTestMessage("Response return false, identifier didn't set");
                        }

                    } else {
                        setState(TestMessage.TEST_FAILED.getValue());
                        setTestMessage(TestMessage.EMPTY_RESPONSE.getValue()
                                + " while create test node");
                    }
                } catch (Exception e) {
                    setState(TestMessage.TEST_FAILED.getValue());
                    setTestMessage(e.getStackTrace().toString());
                }
            }
        });
        this.tests.add(new AbstractIntegrationTest("check get & set link content methods") {

            @Override
            public void run() {

                try {
                    final SctpClient client = new SctpClientImpl();
                    client.init(SERVER_URL, SERVER_PORT);
                    SctpResponse<ScAddress> createResponse = client.createScLink();
                    if (!createResponse.isEmpty()) {
                        ScAddress link = createResponse.getAnswer();
                        String linkContent = "test content";
                        ScString content = new ScString(linkContent);
                        SctpResponse<Boolean> setContentResponse = client.setScRefContent(link,
                                content);
                        if (setContentResponse.getAnswer()) {
                            SctpResponse<String> getContentResponse = client.getScLinkContent(link);
                            if (!setContentResponse.isEmpty()
                                    && content.getContent().equals(getContentResponse.getAnswer())) {
                                setState(TestMessage.TEST_SUCCESS.getValue());
                                setTestMessage("Link:" + link + " has content:" + linkContent);
                            } else {
                                setState(TestMessage.TEST_FAILED.getValue());
                                setTestMessage("Link wtih content" + linkContent + " doesn't exist");
                            }
                        } else {
                            setState(TestMessage.TEST_FAILED.getValue());
                            setTestMessage("Response return false, link content didn't set");
                        }
                    } else {
                        setState(TestMessage.TEST_FAILED.getValue());
                        setTestMessage(TestMessage.EMPTY_RESPONSE.getValue()
                                + " while create test node");
                    }

                } catch (Exception e) {
                    setState(TestMessage.TEST_FAILED.getValue());
                    setTestMessage(e.getStackTrace().toString());
                }
            }
        });

    }
}
