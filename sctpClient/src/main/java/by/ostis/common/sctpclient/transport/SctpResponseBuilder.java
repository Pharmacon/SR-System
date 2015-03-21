package by.ostis.common.sctpclient.transport;

import java.io.InputStream;

import by.ostis.common.sctpclient.exception.SctpClientException;
import by.ostis.common.sctpclient.model.request.SctpRequest;
import by.ostis.common.sctpclient.model.response.SctpResponse;

interface SctpResponseBuilder<T> {

    SctpResponse<T> build(InputStream source, SctpRequest sctpRequest) throws SctpClientException;

}
