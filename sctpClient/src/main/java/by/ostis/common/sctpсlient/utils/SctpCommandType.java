package by.ostis.common.sctpсlient.utils;

public enum SctpCommandType {

	UKNOWN(0x00), CHECK_ELEMENT(0x01), // check if specified sc-element exist
	GET_ELEMENT_TYPE(0x02), // return sc-element type
	ERASE_ELEMENT(0x03), // erase specified sc-element
	CREATE_NODE(0x04), // create new sc-node
	CREATE_LINK(0x05), // create new sc-link
	CREATE_ARC(0x06), // create new sc-arc
	GET_ARC(0x07), // return begin and end element of sc-arc
	GET_LINK_CONTENT(0x09), // return content of sc-link
	FIND_LINKS(0x0a), // return sc-links with specified content
	SET_LINK_CONTENT(0x0b), // setup new content for the link
	ITERATE_ELEMENTS(0x0c), // return base template iteration result
	ITERATE_CONSTRUCTION(0x0d), // return advanced template iteration results
	EVENT_CREATE(0x0e), // create subscription to specified event
	EVENT_DESTROY(0x0f), // destroys specified event subscription
	EVENT_EMIT(0x10), // emits specified event to client
	FIND_ELEMENT_BY_SYSITDF(0xa0), // return sc-element by it system identifier
	SET_SYSIDTF(0xa1), // setup new system identifier for sc-element
	STATISTICS(0xa2), // return usage statistics from server
	SHUTDOWN(0xfe); // disconnect client from server;

	private byte value;

	private SctpCommandType(byte value) {

		this.value = value;
	}

	private SctpCommandType(int intValue) {
		this.value = (byte) intValue;
	}

	public byte getValue() {

		return value;
	}

}
