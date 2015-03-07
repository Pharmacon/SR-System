package by.ostis.common.sctpclient.utils.constants;

public enum ScParameterSize {
    SC_ADDRESS(4),
    SC_CAPACITY(4),
    SC_HEADER(10);

    private int size;

    public int getSize() {

        return size;
    }

    private ScParameterSize(int size) {

        this.size = size;
    }
}
