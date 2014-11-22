package by.ostis.common.utils;

public enum IteratorType {

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

    private IteratorType(long value) {

        this.value = value;
    }

    public long getValue() {

        return value;
    }
}
