package by.ostis.common.sctpclient.transport;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import by.ostis.common.sctpclient.exception.TransportException;
import by.ostis.common.sctpclient.model.response.SctpResponse;
import by.ostis.common.sctpclient.model.response.SctpResponseHeader;
import by.ostis.common.sctpclient.model.response.SctpResultType;
import by.ostis.common.sctpclient.utils.SctpResponseHeaderFactory;
import by.ostis.common.sctpclient.utils.constants.SctpCommandType;

public class SctpResponseBuilderTest {

    @Test
    public void checkCommandResponseHeaderSuccessTest() {
	try {
	    byte[] checkedBytesResponse = new byte[] { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	    InputStream checkedInputStream = new ByteArrayInputStream(checkedBytesResponse);

	    SctpResponseBuilder responseBuilder = new BytesSctpResponseBuilder();
	    SctpResponse checkedResponse = responseBuilder.build(checkedInputStream);
	    SctpResponseHeader checkedResponseHeader = checkedResponse.getHeader();

	    SctpResponseHeader excpectedResponseHeader = SctpResponseHeaderFactory.createNewResponse(
		    SctpCommandType.CHECK_ELEMENT_CMD, 0, SctpResultType.SCTP_RESULT_OK, 0);

	    Assert.assertEquals(checkedResponseHeader.toString(), excpectedResponseHeader.toString());
	} catch (TransportException e) {
	    Assert.fail(e.getMessage());
	}
    }

    @Test
    public void checkCommandResponseHeaderFailureTest() {
	try {
	    byte[] checkedBytesResponse = new byte[] { 2, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	    InputStream checkedInputStream = new ByteArrayInputStream(checkedBytesResponse);

	    SctpResponseBuilder responseBuilder = new BytesSctpResponseBuilder();

	    SctpResponse checkedResponse = responseBuilder.build(checkedInputStream);
	    SctpResponseHeader checkedResponseHeader = checkedResponse.getHeader();

	    SctpResponseHeader excpectedResponseHeader = SctpResponseHeaderFactory.createNewResponse(
		    SctpCommandType.CHECK_ELEMENT_CMD, 0, SctpResultType.SCTP_RESULT_OK, 0);
	    
	    Assert.assertNotSame(checkedResponseHeader.toString(), excpectedResponseHeader.toString());
	    	
	} catch (TransportException e) {
	    Assert.fail(e.getMessage());
	}
    }

    // private boolean checkResponseHeader(byte[] checedByteReponse,
    // SctpCommandType commandType, int commandId,
    // SctpResultType resultType, int argumentSize) throws TransportException{
    //
    // byte[] checkedBytesResponse = new byte[] { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0
    // };
    // InputStream checkedInputStream = new ByteArrayInputStream(
    // checkedBytesResponse);
    //
    // SctpResponseBuilder responseBuilder = new BytesSctpResponseBuilder();
    // SctpResponse checkedResponse = responseBuilder
    // .build(checkedInputStream);
    // SctpResponseHeader checkedResponseHeader = checkedResponse.getHeader();
    //
    // SctpResponseHeader excpectedResponseHeader = SctpResponseHeaderFactory
    // .createNewResponse(SctpCommandType.CHECK_ELEMENT_CMD, 0,
    // SctpResultType.SCTP_RESULT_OK, 0);
    //
    // return
    // checkedResponseHeader.toString().equals(excpectedResponseHeader.toString());
    // }

}
