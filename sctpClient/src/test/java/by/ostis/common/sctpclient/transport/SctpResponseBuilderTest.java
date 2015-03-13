package by.ostis.common.sctpclient.transport;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import by.ostis.common.sctpclient.exception.TransportException;
import by.ostis.common.sctpclient.model.request.SctpRequest;
import by.ostis.common.sctpclient.model.response.SctpResponse;
import by.ostis.common.sctpclient.model.response.SctpResponseHeader;
import by.ostis.common.sctpclient.model.response.SctpResultType;
import by.ostis.common.sctpclient.utils.SctpResponseHeaderFactory;
import by.ostis.common.sctpclient.utils.constants.SctpCommandType;

@RunWith(Parameterized.class)
public class SctpResponseBuilderTest {

    private byte[] checkedBytesResponse;

    private SctpCommandType excpectedCommandType;

    private int excpectedCommandId;

    private SctpResultType excpectedResultType;

    private int expectedArgumentSize;

    public SctpResponseBuilderTest(byte[] checkedBytesResponse, SctpCommandType excpectedCommandType,
	    int excpectedCommandId, SctpResultType excpectedResultType, int expectedArgumentSize) {
	this.checkedBytesResponse = checkedBytesResponse;
	this.excpectedCommandType = excpectedCommandType;
	this.excpectedCommandId = excpectedCommandId;
	this.excpectedResultType = excpectedResultType;
	this.expectedArgumentSize = expectedArgumentSize;
    }

    @Parameters(name = "{index}: Test for {1}")
    public static Collection<Object[]> data() {
	return Arrays.asList(new Object[][] {
		{ new byte[] { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, SctpCommandType.CHECK_ELEMENT_COMMAND, 0,
			SctpResultType.SCTP_RESULT_OK, 0 },

		{ new byte[] { 2, 0, 0, 0, 0, 0, 2, 0, 0, 0, 7, 0 }, SctpCommandType.GET_ELEMENT_TYPE_COMMAND, 0,
			SctpResultType.SCTP_RESULT_OK, 2 },

		{ new byte[] { 3, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, SctpCommandType.ERASE_ELEMENT_COMMAND, 0,
			SctpResultType.SCTP_RESULT_OK, 0 },

		{ new byte[] { 4, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 7, 0 }, SctpCommandType.CREATE_NODE_COMMAND, 0,
			SctpResultType.SCTP_RESULT_OK, 4 },

		{ new byte[] { 5, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 7, 0 }, SctpCommandType.CREATE_LINK_COMMAND, 0,
			SctpResultType.SCTP_RESULT_OK, 4 },

		{ new byte[] { 6, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 7, 0 }, SctpCommandType.CREATE_ARC_COMMAND, 0,
			SctpResultType.SCTP_RESULT_OK, 4 },

		{ new byte[] { 7, 0, 0, 0, 0, 0, 8, 0, 0, 0,0,0,1,1, 0, 0, 7, 0 }, SctpCommandType.GET_ARC_COMMAND, 0,
			SctpResultType.SCTP_RESULT_OK, 8 },

		{ new byte[] { 9, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 7, 0 }, SctpCommandType.GET_LINK_CONTENT_COMMAND, 0,
			SctpResultType.SCTP_RESULT_OK, 4 }, });
    }
    
    
    //TODO add request to response builder method
    @Test
    public void buildResponseSuccessTest() {
	try {
	    InputStream checkedInputStream = new ByteArrayInputStream(checkedBytesResponse);

	    SctpResponseBuilder responseBuilder = new BytesSctpResponseBuilder();
	    
	    SctpResponse checkedResponse = responseBuilder.build(checkedInputStream, new SctpRequest());
	    SctpResponseHeader checkedResponseHeader = checkedResponse.getHeader();

	    SctpResponseHeader excpectedResponseHeader = SctpResponseHeaderFactory.createNewResponse(
		    excpectedCommandType, excpectedCommandId, excpectedResultType, expectedArgumentSize);

	    Assert.assertEquals(excpectedResponseHeader.toString(), checkedResponseHeader.toString());

	} catch (TransportException e) {
	    Assert.fail(e.getMessage());
	}
    }
}
