package by.ostis.common.sctpclient.responsebuilder;

import java.util.List;

import by.ostis.common.sctpclient.model.response.SctpResponseHeader;
import by.ostis.common.sctpclient.utils.constants.SctpCommandType;

public interface ResponseBodyBuilder {
	
	List<Object> getBody(byte[] bytes,SctpResponseHeader responseHeader);
}
