package by.ostis.common.sctpclient.utils;

import by.ostis.common.sctpclient.model.response.SctpResponseHeader;
import by.ostis.common.sctpclient.model.response.SctpResultType;
import by.ostis.common.sctpclient.utils.constants.SctpCommandType;

public class SctpResponseHeaderFactory {

    public static SctpResponseHeader createNewResponse(SctpCommandType commandType, int commandId,
	    SctpResultType resultType, int argumentSize) {
	SctpResponseHeader newSctpResponseHeader = new SctpResponseHeader();
	newSctpResponseHeader.setCommandType(commandType);
	newSctpResponseHeader.setCommandId(commandId);
	newSctpResponseHeader.setResultType(resultType);
	newSctpResponseHeader.setArgumentSize(argumentSize);
	return newSctpResponseHeader;
    }

}
