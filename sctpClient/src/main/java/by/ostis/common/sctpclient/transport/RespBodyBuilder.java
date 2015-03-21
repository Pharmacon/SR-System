package by.ostis.common.sctpclient.transport;

import by.ostis.common.sctpclient.exception.SctpClientException;
import by.ostis.common.sctpclient.model.response.SctpResponseHeader;

interface RespBodyBuilder<T> {

    T getAnswer(byte[] bytes, SctpResponseHeader responseHeader) throws SctpClientException;
}
