package by.ostis.common.sctpclient.transport;

import org.junit.Assert;
import org.junit.Test;

import by.ostis.common.sctpclient.model.DefaultRequestBuilder;
import by.ostis.common.sctpclient.model.RequestBuilder;
import by.ostis.common.sctpclient.model.ScAddress;
import by.ostis.common.sctpclient.model.request.RequestHeaderType;
import by.ostis.common.sctpclient.model.request.SctpRequest;

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
}
