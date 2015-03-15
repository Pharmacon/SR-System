package by.ostis.common.sctpclient.model.request;

import by.ostis.common.sctpclient.utils.constants.SctpCommandType;

public enum RequestHeaderType {

    CHECK_ELEMENT(SctpCommandType.CHECK_ELEMENT_COMMAND, (byte) 0, 0),
    DELETE_ELEMENT(SctpCommandType.ERASE_ELEMENT_COMMAND, (byte) 0, 0),
    SEARCH_ELEMENT_BY_IDENTIFIER(
            SctpCommandType.FIND_ELEMENT_BY_SYSIDTF_COMMAND, (byte) 0, 0),
    CREATE_ELEMENT(SctpCommandType.CREATE_NODE_COMMAND, (byte) 0, 0),
    CREATE_SC_LINK(SctpCommandType.CREATE_LINK_COMMAND, (byte) 0, 0),
    CREATE_SC_ARC(SctpCommandType.CREATE_ARC_COMMAND, (byte) 0, 0),
    SEARCH_SC_LINKS(SctpCommandType.FIND_LINKS_COMMAND, (byte) 0, 0),
    SET_SC_LINK_CONTENT(SctpCommandType.SET_LINK_CONTENT_COMMAND, (byte) 0, 0),
    SET_SYSIDTF(SctpCommandType.SET_SYSIDTF_COMMAND, (byte) 0, 0),
    FIND_ARC_BEGIN_AND_END(SctpCommandType.GET_ARC_VERTEXES_COMMAND, (byte) 0, 0),
    ITERATOR_SEARCH(SctpCommandType.ITERATE_ELEMENTS_COMMAND, (byte) 0, 0),
    GET_LINK_CONTENT(SctpCommandType.GET_LINK_CONTENT_COMMAND, (byte) 0, 0);

    private SctpCommandType commandType;

    private byte            flag;

    private int             commandId;

    public static RequestHeaderType getByCommandId(SctpCommandType commandId) {

        for (RequestHeaderType headerType : values()) {
            if (headerType.commandType == commandId) {
                return headerType;
            }
        }
        throw new IllegalArgumentException("Unsupported Sctp command type");
    }

    private RequestHeaderType(final SctpCommandType commandType,
            final byte flag, final int commandId) {

        this.commandType = commandType;
        this.flag = flag;
        this.commandId = commandId;
    }

    public SctpRequestHeader getRequestHeader() {

        final SctpRequestHeader header = new SctpRequestHeader();
        header.setCommandType(this.commandType);
        header.setCommandId(this.commandId);
        header.setFlag(this.flag);
        return header;
    }

}
