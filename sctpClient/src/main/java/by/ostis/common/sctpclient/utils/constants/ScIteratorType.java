package by.ostis.common.sctpclient.utils.constants;

import by.ostis.common.sctpclient.model.ScParameter;

public enum ScIteratorType implements ScParameter {

    SCTP_ITERATOR_3F_A_A(0),
    SCTP_ITERATOR_3_A_A_F(1),
    SCTP_ITERATOR_3F_A_F(2),
    SCTP_ITERATOR_5F_A_A_A_F(3),
    SCTP_ITERATOR_5_A_A_F_A_F(4),
    SCTP_ITERATOR_5_F_A_F_A_F(5),
    SCTP_ITERATOR_5_F_A_F_A_A(6),
    SCTP_ITERATOR_5_F_A_A_A_A(7),
    SCTP_ITERATOR_5_A_A_F_A_A(8);

    private long value;

    private ScIteratorType(long value) {

        this.value = value;
    }

    public long getValue() {

        return value;
    }

    @Override
    public byte[] getBytes() {

        return new byte[] { (byte) value };
    }

    @Override
    public int getByteSize() {

        return 1;
    }
}
