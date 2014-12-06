package by.ostis.common.sctpсlient.utils;

public enum EventType {

    SC_EVENT_UNKNOWN(-1),
    SC_EVENT_ADD_OUTPUT_ARC(0),
    SC_EVENT_ADD_INPUT_ARC(1),
    SC_EVENT_REMOVE_OUTPUT_ARC(2),
    SC_EVENT_REMOVE_INPUT_ARC(3),
    SC_EVENT_REMOVE_ELEMENT(4);

    private long value;

    private EventType(long value) {

        this.value = value;
    }

    public long getValue() {

        return value;
    }
}
