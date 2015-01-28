package by.ostis.common.sctpclient.transport;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import by.ostis.common.sctpclient.client.DefaultRequestBuilder;
import by.ostis.common.sctpclient.client.RequestBuilder;
import by.ostis.common.sctpclient.exception.TransportException;
import by.ostis.common.sctpclient.model.ScAddress;
import by.ostis.common.sctpclient.model.ScParameter;
import by.ostis.common.sctpclient.model.request.RequestHeaderType;
import by.ostis.common.sctpclient.model.request.SctpRequest;
import by.ostis.common.sctpclient.model.response.SctpResponse;
import by.ostis.common.sctpclient.model.response.SctpResponseHeader;
import by.ostis.common.sctpclient.model.response.SctpResultType;
import by.ostis.common.sctpclient.utils.constants.IteratorType;
import by.ostis.common.sctpclient.utils.constants.ScElementType;
import by.ostis.common.sctpclient.utils.constants.SctpCommandType;

public class SctpRequestSenderTest {
	
	@Test
	public void requestBuilderSuccessTest(){
		ScAddress checkedAddress = new ScAddress(0, 135);
		
		RequestBuilder requestBuilder = new DefaultRequestBuilder();
		SctpRequest request = requestBuilder.buildRequest(RequestHeaderType.CHECK_ELEMENT_COMMAND, checkedAddress);
		byte[] reqBytes = SctpRequestBytesBuilder.build(request); 
		byte[] expectedBytes = new byte[]{1, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, -121, 0 };
		
		Assert.assertArrayEquals(expectedBytes,reqBytes);
	}
	
	@Test
	public void responseBuilderCheckCommandSuccessTest(){
		try {
			byte[] checkedBytesResponse = new byte[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			InputStream checkedInputStream = new ByteArrayInputStream(checkedBytesResponse);
			
			SctpResponseBuilder responseBuilder = new BytesSctpResponseBuilder();
			
			SctpResponse response = responseBuilder.build(checkedInputStream);
			SctpResponseHeader header = response.getHeader();
			
			Assert.assertNotNull(header);
			Assert.assertEquals(header.getCommandType(), SctpCommandType.CHECK_ELEMENT);
			Assert.assertEquals(header.getCommandId(), 0);
			Assert.assertEquals(header.getResultType(), SctpResultType.SCTP_RESULT_OK);
			Assert.assertEquals(header.getArgumentSize(), 0);
		} catch (TransportException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void responseBuilderCheckCommandFailureTest(){
		try {
			byte[] checkedBytesResponse = new byte[]{2, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			InputStream checkedInputStream = new ByteArrayInputStream(checkedBytesResponse);
			
			SctpResponseBuilder responseBuilder = new BytesSctpResponseBuilder();
			
			SctpResponse response = responseBuilder.build(checkedInputStream);
			SctpResponseHeader header = response.getHeader();
			
			Assert.assertNotNull(header);
			Assert.assertNotSame(header.getCommandType(), SctpCommandType.CHECK_ELEMENT);
			Assert.assertEquals(header.getCommandId(), 0);
			Assert.assertEquals(header.getResultType(), SctpResultType.SCTP_RESULT_OK);
			Assert.assertEquals(header.getArgumentSize(), 0);
		} catch (TransportException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void buildIteratorSuccesTestTest(){
		ScAddress addressFirst = new ScAddress(0, 135);
		ScElementType elemntTypeSecond = ScElementType.SC_TYPE_ARC_POS;
		ScElementType elementTypeThird = ScElementType.SC_TYPE_NODE;
		ScIterator iterator3FAA = ScIteratorFactory.create3FAA(addressFirst, elemntTypeSecond, elementTypeThird);
		RequestBuilder requestBuilder = new DefaultRequestBuilder();
		SctpRequest request = requestBuilder.buildRequest(RequestHeaderType.ITERATOR_SEARCH, iterator3FAA);
		
	}

}
