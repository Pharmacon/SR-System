package by.ostis.common.sctpclientIT.test.testpackages;

import java.util.ArrayList;
import java.util.List;

import by.ostis.common.sctpclient.client.SctpClient;
import by.ostis.common.sctpclient.client.SctpClientImpl;
import by.ostis.common.sctpclient.model.ScAddress;
import by.ostis.common.sctpclient.model.ScIterator;
import by.ostis.common.sctpclient.model.ScParameter;
import by.ostis.common.sctpclient.model.ScString;
import by.ostis.common.sctpclient.model.response.SctpResponse;
import by.ostis.common.sctpclient.utils.constants.ScElementType;
import by.ostis.common.sctpclient.utils.constants.ScIteratorType;
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
        this.tests.add(new AbstractIntegrationTest("check scIterator 3") {

            @Override
            public void run() {

                try {
                    final SctpClient client = new SctpClientImpl();
                    client.init(SERVER_URL, SERVER_PORT);
                    ScElementType el1Type = ScElementType.SC_TYPE_NODE;
                    ScElementType el3Type = ScElementType.SC_TYPE_NODE;
                    ScElementType el5Type = ScElementType.SC_TYPE_NODE;
                    ScElementType arc2Type = ScElementType.SC_TYPE_ARC_POS;
                    ScElementType arc4Type = ScElementType.SC_TYPE_ARC_POS;
                    SctpResponse<ScAddress> createEl1Response = client.createElement(el1Type);
                    SctpResponse<ScAddress> createEl3Response = client.createElement(el3Type);
                    SctpResponse<ScAddress> createEl5Response = client.createElement(el5Type);
                    if (!createEl3Response.isEmpty() && !createEl1Response.isEmpty()
                            && !createEl5Response.isEmpty()) {
                        ScAddress el1 = createEl1Response.getAnswer();
                        ScAddress el3 = createEl3Response.getAnswer();
                        ScAddress el5 = createEl5Response.getAnswer();

                        SctpResponse<ScAddress> createEl2Response = client.createScArc(arc2Type,
                                el1, el3);
                        SctpResponse<ScAddress> createEl4Response = client.createScArc(arc4Type,
                                el1, el5);

                        if (!createEl2Response.isEmpty() && !createEl4Response.isEmpty()) {
                            // ScAddress arc2 = createEl2Response.getAnswer();
                            ScAddress arc4 = createEl4Response.getAnswer();
                            List<ScParameter> params1 = new ArrayList<>();
                            params1.add(el1);
                            params1.add(arc2Type);
                            params1.add(el3Type);
                            ScIteratorType iterType1 = ScIteratorType.SCTP_ITERATOR_3F_A_A;
                            SctpResponse<List<ScIterator>> iterator1Response = client
                                    .searchByIterator(iterType1, params1);
                            List<ScParameter> params2 = new ArrayList<>();
                            params2.add(el1Type);
                            params2.add(arc4Type);
                            params2.add(el5);
                            ScIteratorType iterType2 = ScIteratorType.SCTP_ITERATOR_3_A_A_F;
                            SctpResponse<List<ScIterator>> iterator2Response = client
                                    .searchByIterator(iterType2, params2);
                            List<ScParameter> params3 = new ArrayList<>();
                            params3.add(el1);
                            params3.add(arc4Type);
                            params3.add(el5);
                            ScIteratorType iterType3 = ScIteratorType.SCTP_ITERATOR_3F_A_F;
                            SctpResponse<List<ScIterator>> iterator3Response = client
                                    .searchByIterator(iterType3, params3);
                            if (!iterator1Response.isEmpty() && !iterator2Response.isEmpty()
                                    && iterator3Response.isEmpty()) {
                                List<ScIterator> result1 = iterator1Response.getAnswer();
                                List<ScIterator> result2 = iterator2Response.getAnswer();
                                List<ScIterator> result3 = iterator3Response.getAnswer();
                                // 3F_A_A iterator result check
                                if (result1.size() != 2
                                        && !el1.equals(result1.get(0).getElement(0))
                                        && !el1.equals(result1.get(1).getElement(0))) {
                                    setState(TestMessage.TEST_FAILED.getValue());
                                    setTestMessage("Illegal result ScParameters at iterator type: "
                                            + iterType1.getValue());
                                    return;
                                }
                                // 3A_A_F iterator result check
                                if (result2.size() != 1
                                        && !el1.equals(result2.get(0).getElement(0))
                                        && !arc4.equals(result2.get(0).getElement(1))
                                        && !el5.equals(result2.get(0).getElement(2))) {
                                    setState(TestMessage.TEST_FAILED.getValue());
                                    setTestMessage("Illegal result ScParameters at iterator type: "
                                            + iterType2.getValue());
                                    return;
                                }
                                // 3F_A_F iterator result check
                                if (result3.size() != 1
                                        && !arc4.equals(result3.get(0).getElement(1))
                                        && !el1.equals(result3.get(0).getElement(0))
                                        && !el5.equals(result3.get(0).getElement(2))) {
                                    setState(TestMessage.TEST_FAILED.getValue());
                                    setTestMessage("Illegal result ScParameters at iterator type: "
                                            + iterType3.getValue());
                                    return;
                                }
                                // all check success
                                setState(TestMessage.TEST_SUCCESS.getValue());
                                setTestMessage("Parameters for all 3 iterators valid");
                            } else {
                                setState(TestMessage.TEST_FAILED.getValue());
                                setTestMessage(TestMessage.EMPTY_RESPONSE.getValue()
                                        + " while search iterators");
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
        this.tests.add(new AbstractIntegrationTest("check scIterator 5") {

            @Override
            public void run() {

                try {
                    final SctpClient client = new SctpClientImpl();
                    client.init(SERVER_URL, SERVER_PORT);
                    ScElementType el1Type = ScElementType.SC_TYPE_NODE;
                    ScElementType el3Type = ScElementType.SC_TYPE_NODE;
                    ScElementType el5Type = ScElementType.SC_TYPE_NODE;
                    ScElementType el7Type = ScElementType.SC_TYPE_NODE;
                    ScElementType el9Type = ScElementType.SC_TYPE_NODE;
                    ScElementType arc2Type = ScElementType.SC_TYPE_ARC_POS;
                    ScElementType arc4Type = ScElementType.SC_TYPE_ARC_POS;
                    ScElementType arc6Type = ScElementType.SC_TYPE_ARC_POS;
                    ScElementType arc8Type = ScElementType.SC_TYPE_ARC_POS;
                    SctpResponse<ScAddress> createEl1Response = client.createElement(el1Type);
                    SctpResponse<ScAddress> createEl3Response = client.createElement(el3Type);
                    SctpResponse<ScAddress> createEl5Response = client.createElement(el5Type);
                    SctpResponse<ScAddress> createEl7Response = client.createElement(el7Type);
                    SctpResponse<ScAddress> createEl9Response = client.createElement(el9Type);
                    if (!createEl1Response.isEmpty() && !createEl3Response.isEmpty()
                            && !createEl5Response.isEmpty() && !createEl7Response.isEmpty()
                            && !createEl9Response.isEmpty()) {
                        ScAddress el1 = createEl1Response.getAnswer();
                        ScAddress el3 = createEl3Response.getAnswer();
                        ScAddress el5 = createEl5Response.getAnswer();
                        ScAddress el7 = createEl7Response.getAnswer();
                        ScAddress el9 = createEl9Response.getAnswer();

                        SctpResponse<ScAddress> createEl2Response = client.createScArc(arc2Type,
                                el1, el3);
                        SctpResponse<ScAddress> createEl6Response = client.createScArc(arc6Type,
                                el1, el7);

                        if (!createEl2Response.isEmpty() && !createEl6Response.isEmpty()) {
                            ScAddress arc2 = createEl2Response.getAnswer();
                            ScAddress arc6 = createEl6Response.getAnswer();

                            SctpResponse<ScAddress> createEl4Response = client.createScArc(
                                    arc4Type, el5, arc2);
                            SctpResponse<ScAddress> createEl8Response = client.createScArc(
                                    arc8Type, el9, arc6);
                            if (!createEl4Response.isEmpty() && !createEl8Response.isEmpty()) {
                                ScAddress arc4 = createEl4Response.getAnswer();
                                ScAddress arc8 = createEl8Response.getAnswer();

                                List<ScParameter> params1 = new ArrayList<>();
                                params1.add(el1Type);
                                params1.add(arc2Type);
                                params1.add(el3);
                                params1.add(arc4Type);
                                params1.add(el5Type);
                                ScIteratorType iterType1 = ScIteratorType.SCTP_ITERATOR_5_A_A_F_A_A;
                                SctpResponse<List<ScIterator>> iterator1Response = client
                                        .searchByIterator(iterType1, params1);
                                List<ScParameter> params2 = new ArrayList<>();
                                params2.add(el1Type);
                                params2.add(arc6Type);
                                params2.add(el7);
                                params2.add(arc8Type);
                                params2.add(el9);
                                ScIteratorType iterType2 = ScIteratorType.SCTP_ITERATOR_5_A_A_F_A_F;
                                SctpResponse<List<ScIterator>> iterator2Response = client
                                        .searchByIterator(iterType2, params2);
                                List<ScParameter> params3 = new ArrayList<>();
                                params3.add(el1);
                                params3.add(arc2Type);
                                params3.add(el3Type);
                                params3.add(arc4Type);
                                params3.add(el5Type);
                                ScIteratorType iterType3 = ScIteratorType.SCTP_ITERATOR_5_F_A_A_A_A;
                                SctpResponse<List<ScIterator>> iterator3Response = client
                                        .searchByIterator(iterType3, params3);
                                List<ScParameter> params4 = new ArrayList<>();
                                params4.add(el1);
                                params4.add(arc6Type);
                                params4.add(el7);
                                params4.add(arc8Type);
                                params4.add(el9Type);
                                ScIteratorType iterType4 = ScIteratorType.SCTP_ITERATOR_5_F_A_F_A_A;
                                SctpResponse<List<ScIterator>> iterator4Response = client
                                        .searchByIterator(iterType4, params4);
                                List<ScParameter> params5 = new ArrayList<>();
                                params5.add(el1);
                                params5.add(arc2Type);
                                params5.add(el3);
                                params5.add(arc4Type);
                                params5.add(el5);
                                ScIteratorType iterType5 = ScIteratorType.SCTP_ITERATOR_5_F_A_F_A_F;
                                SctpResponse<List<ScIterator>> iterator5Response = client
                                        .searchByIterator(iterType5, params5);
                                List<ScParameter> params6 = new ArrayList<>();
                                params6.add(el1);
                                params6.add(arc2Type);
                                params6.add(el3Type);
                                params6.add(arc4Type);
                                params6.add(el5);
                                ScIteratorType iterType6 = ScIteratorType.SCTP_ITERATOR_5F_A_A_A_F;
                                SctpResponse<List<ScIterator>> iterator6Response = client
                                        .searchByIterator(iterType6, params6);
                                if (!iterator1Response.isEmpty() && !iterator2Response.isEmpty()
                                        && iterator3Response.isEmpty()
                                        && !iterator4Response.isEmpty()
                                        && !iterator5Response.isEmpty()
                                        && !iterator6Response.isEmpty()) {
                                    List<ScIterator> result1 = iterator1Response.getAnswer();
                                    List<ScIterator> result2 = iterator2Response.getAnswer();
                                    List<ScIterator> result3 = iterator3Response.getAnswer();
                                    List<ScIterator> result4 = iterator4Response.getAnswer();
                                    List<ScIterator> result5 = iterator5Response.getAnswer();
                                    List<ScIterator> result6 = iterator6Response.getAnswer();

                                    // 5_A_A_F_A_A iterator result check
                                    if (result1.size() != 1
                                            && !el1.equals(result1.get(0).getElement(0))
                                            && !arc2.equals(result1.get(0).getElement(1))
                                            && !el3.equals(result1.get(0).getElement(2))
                                            && !arc4.equals(result1.get(0).getElement(3))
                                            && !el5.equals(result1.get(0).getElement(4))) {
                                        setState(TestMessage.TEST_FAILED.getValue());
                                        setTestMessage("Illegal result ScParameters at iterator type: "
                                                + iterType1.getValue());
                                        return;
                                    }
                                    // 5_A_A_F_A_F iterator result check
                                    if (result2.size() != 1
                                            && !el1.equals(result2.get(0).getElement(0))
                                            && !arc6.equals(result2.get(0).getElement(1))
                                            && !el7.equals(result2.get(0).getElement(2))
                                            && !arc8.equals(result2.get(0).getElement(3))
                                            && !el9.equals(result2.get(0).getElement(4))) {
                                        setState(TestMessage.TEST_FAILED.getValue());
                                        setTestMessage("Illegal result ScParameters at iterator type: "
                                                + iterType2.getValue());
                                        return;
                                    }
                                    // 5_F_A_A_A_A iterator result check
                                    if (result3.size() != 2
                                            && !el1.equals(result3.get(0).getElement(0))
                                            && !el1.equals(result3.get(1).getElement(0))) {
                                        setState(TestMessage.TEST_FAILED.getValue());
                                        setTestMessage("Illegal result ScParameters at iterator type: "
                                                + iterType3.getValue());
                                        return;
                                    }
                                    // 5_F_A_F_A_A iterator result check
                                    if (result4.size() != 1
                                            && !el1.equals(result4.get(0).getElement(0))
                                            && !arc6.equals(result4.get(0).getElement(1))
                                            && !el7.equals(result4.get(0).getElement(2))
                                            && !arc8.equals(result4.get(0).getElement(3))
                                            && !el9.equals(result4.get(0).getElement(4))) {
                                        setState(TestMessage.TEST_FAILED.getValue());
                                        setTestMessage("Illegal result ScParameters at iterator type: "
                                                + iterType4.getValue());
                                        return;
                                    }
                                    // 5_F_A_F_A_F iterator result check
                                    if (result5.size() != 1
                                            && !el1.equals(result5.get(0).getElement(0))
                                            && !arc2.equals(result5.get(0).getElement(1))
                                            && !el3.equals(result5.get(0).getElement(2))
                                            && !arc4.equals(result5.get(0).getElement(3))
                                            && !el5.equals(result5.get(0).getElement(4))) {
                                        setState(TestMessage.TEST_FAILED.getValue());
                                        setTestMessage("Illegal result ScParameters at iterator type: "
                                                + iterType5.getValue());
                                        return;
                                    }
                                    // 5_F_A_A_A_F iterator result check
                                    if (result6.size() != 1
                                            && !el1.equals(result6.get(0).getElement(0))
                                            && !arc2.equals(result6.get(0).getElement(1))
                                            && !el3.equals(result6.get(0).getElement(2))
                                            && !arc4.equals(result6.get(0).getElement(3))
                                            && !el5.equals(result6.get(0).getElement(4))) {
                                        setState(TestMessage.TEST_FAILED.getValue());
                                        setTestMessage("Illegal result ScParameters at iterator type: "
                                                + iterType6.getValue());
                                        return;
                                    }
                                    // all check success
                                    setState(TestMessage.TEST_SUCCESS.getValue());
                                    setTestMessage("Parameters for all 5 iterators valid");

                                } else {
                                    setState(TestMessage.TEST_FAILED.getValue());
                                    setTestMessage(TestMessage.EMPTY_RESPONSE.getValue()
                                            + " while search iterators");
                                }

                            } else {
                                setState(TestMessage.TEST_FAILED.getValue());
                                setTestMessage(TestMessage.EMPTY_RESPONSE.getValue()
                                        + " while create test arcs");
                            }
                        } else {
                            setState(TestMessage.TEST_FAILED.getValue());
                            setTestMessage(TestMessage.EMPTY_RESPONSE.getValue()
                                    + " while create test arcs");
                        }

                    } else {
                        setState(TestMessage.TEST_FAILED.getValue());
                        setTestMessage(TestMessage.EMPTY_RESPONSE.getValue()
                                + " while create test nodes");
                    }

                } catch (Exception e) {
                    setState(TestMessage.TEST_FAILED.getValue());
                    setTestMessage(e.getStackTrace().toString());
                }
            }
        });

    }
}
