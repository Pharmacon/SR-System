package by.ostis.common.sctpclient.transport;

import org.junit.Assert;
import org.junit.Test;

import by.ostis.common.sctpclient.client.DefaultRequestBuilder;
import by.ostis.common.sctpclient.client.RequestBuilder;
import by.ostis.common.sctpclient.model.ScAddress;
import by.ostis.common.sctpclient.model.ScIterator;
import by.ostis.common.sctpclient.model.ScIteratorFactory;
import by.ostis.common.sctpclient.model.request.RequestHeaderType;
import by.ostis.common.sctpclient.model.request.SctpRequest;
import by.ostis.common.sctpclient.utils.constants.ScElementType;
import by.ostis.common.sctpclient.utils.constants.ScIteratorType;

public class SctpRequestBuilderTest {

    @Test
    public void checkCommandRequestBytesSuccessTest() {
	ScAddress checkedAddress = new ScAddress(0, 135);

	RequestBuilder requestBuilder = new DefaultRequestBuilder();
	SctpRequest request = requestBuilder.buildRequest(RequestHeaderType.CHECK_ELEMENT, checkedAddress);
	byte[] reqBytes = SctpRequestBytesBuilder.build(request);
	byte[] expectedBytes = new byte[] { 1, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, -121, 0 };

	Assert.assertArrayEquals(expectedBytes, reqBytes);
    }

    @Test
    public void searchIteratorRequestBytesSuccesTest() {
	ScAddress addressFirst = new ScAddress(0, 135);
	ScElementType elemntTypeSecond = ScElementType.SC_TYPE_ARC_POS;
	ScElementType elementTypeThird = ScElementType.SC_TYPE_NODE;
	ScIterator iterator3FAA = ScIteratorFactory.create3FAA(addressFirst, elemntTypeSecond, elementTypeThird);

	RequestBuilder requestBuilder = new DefaultRequestBuilder();
	SctpRequest checkedRequest = requestBuilder.buildRequest(RequestHeaderType.ITERATOR_SEARCH,
		ScIteratorType.SCTP_ITERATOR_3F_A_A, iterator3FAA);

	byte[] checkedByteRequest = SctpRequestBytesBuilder.build(checkedRequest);

	byte[] expectedBytes = new byte[] { 12, 0, 0, 0, 0, 0, 9, 0, 0, 0, 0, 0, 0, -121, 0, -128, 0, 1, 0 };

	Assert.assertArrayEquals(expectedBytes, checkedByteRequest);
    }

}
