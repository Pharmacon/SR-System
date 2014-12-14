package by.ostis.common.sctpсlient.model;

import java.util.ArrayList;
import java.util.List;

import by.ostis.common.sctpсlient.utils.constants.ResultCode;
import by.ostis.common.sctpсlient.utils.constants.SctpCommandType;

public class SctpResponse {
	public class SctpResponseHeader {
		private SctpCommandType commandType;
		private int commandId;
		private ResultCode returnCode;
		private int argumentSize;
		
		public SctpResponseHeader(SctpCommandType commandType, int commandId,
				ResultCode returnCode, int argumentSize) {
			this.commandType = commandType;
			this.commandId = commandId;
			this.returnCode = returnCode;
			this.argumentSize = argumentSize;

		}

		public SctpCommandType getCommandType() {
			return commandType;
		}

		public int getCommandId() {
			return commandId;
		}

		public ResultCode getReturnCode() {
			return returnCode;
		}


		public int getArgumentSize() {
			return argumentSize;
		}
	}
	private SctpResponseHeader header;
	private List<Object> body=new ArrayList<>();
	public void addParameterToBody(Object param){
		body.add(param);
	}
	public List<Object> getBody() {
		return body;
	}
	public SctpResponseHeader getHeader() {
		return header;
	}
	public void setHeader(SctpResponseHeader header) {
		this.header = header;
	}
	
}
