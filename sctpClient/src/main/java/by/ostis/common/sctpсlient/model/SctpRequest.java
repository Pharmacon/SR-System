package by.ostis.common.sctpсlient.model;

import java.util.ArrayList;
import java.util.List;

import by.ostis.common.sctpсlient.utils.constants.SctpCommandType;

public class SctpRequest {
	public class SctpRequestHeader {
		private SctpCommandType commandaType;

		private byte flag;

		private int commandId;

		private int argumentSize;

		public SctpRequestHeader(SctpCommandType commandType, byte flag,
				int commandId, int argumentSize) {
			this.commandaType = commandType;
			this.argumentSize = argumentSize;
			this.commandId = commandId;
			this.flag = flag;
		}

		public SctpCommandType getCommandaType() {
			return commandaType;
		}

		public byte getFlag() {
			return flag;
		}

		public int getCommandId() {
			return commandId;
		}

		public int getArgumentSize() {
			return argumentSize;
		}
	}

	private SctpRequestHeader header;

	public SctpRequestHeader getHeader() {
		return header;
	}

	public void setHeader(SctpRequestHeader header) {
		this.header = header;
	}

	private List<Object> body = new ArrayList<>();

	public List<Object> getBody() {
		return body;
	}

	public void addParameterToBody(Object param) {
		body.add(param);
	}
}
