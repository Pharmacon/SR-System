package by.ostis.common.sctpclient.utils.constants;

public enum ScArcType {
    SC_TYPE_ARC_POS((short) 0x80), SC_TYPE_ARC_NEG((short) 0x100), SC_TYPE_ARC_FUZ((short) 0x200), SC_TYPE_ARC_TEMP(
	    (short) 0x400), SC_TYPE_ARC_PERM((short) 0x800);
    private short value;
    public static int SC_ARC_TYPE_BYTE_SIZE = 2;

    private ScArcType(final short value) {
	this.value = value;
    }

    public short getValue() {
	return this.value;
    }
}
