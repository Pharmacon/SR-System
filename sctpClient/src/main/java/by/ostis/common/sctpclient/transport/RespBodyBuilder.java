package by.ostis.common.sctpclient.transport;

import java.util.List;

import by.ostis.common.sctpclient.model.response.SctpResponseHeader;

interface RespBodyBuilder {

    List<Object> getBody(byte[] bytes, SctpResponseHeader responseHeader);
}
