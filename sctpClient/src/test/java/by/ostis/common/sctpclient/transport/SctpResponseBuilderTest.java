package by.ostis.common.sctpclient.transport;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;

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

@RunWith(Parameterized.class)
public class SctpResponseBuilderTest {

    private byte[] bytes;

    private int sdfas;
    
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
        	{new byte[] { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 24, }
        });
}
    
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

	    Assert.assertThat(checkedResponseHeader.toString(), not(equalTo(excpectedResponseHeader.toString())));

	} catch (TransportException e) {
	    Assert.fail(e.getMessage());
	}
    }

    private boolean checkResponseHeader(byte[] checedByteReponse, SctpCommandType commandType, int commandId,
	    SctpResultType resultType, int argumentSize) throws TransportException {

	byte[] checkedBytesResponse = new byte[] { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	InputStream checkedInputStream = new ByteArrayInputStream(checkedBytesResponse);

	SctpResponseBuilder responseBuilder = new BytesSctpResponseBuilder();
	SctpResponse checkedResponse = responseBuilder.build(checkedInputStream);
	SctpResponseHeader checkedResponseHeader = checkedResponse.getHeader();

	SctpResponseHeader excpectedResponseHeader = SctpResponseHeaderFactory.createNewResponse(
		SctpCommandType.CHECK_ELEMENT_CMD, 0, SctpResultType.SCTP_RESULT_OK, 0);

	return checkedResponseHeader.toString().equals(excpectedResponseHeader.toString());
    }

}
