/**
 * 
 */
package by.ostis.common.sctpclient.model;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import by.ostis.common.sctpclient.utils.constants.ScParameterSize;

/**
 * @author Andrew Nepogoda Mar 7, 2015
 */
public class ScContentSize implements ScParameter {

    private int size;

    public ScContentSize(int size) {

        this.size = size;
    }

    @Override
    public byte[] getBytes() {

        ByteBuffer buffer = ByteBuffer.allocate(ScParameterSize.SC_CAPACITY
                .getSize());
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.putInt(size);
        return buffer.array();
    }

    @Override
    public int getByteSize() {

        return ScParameterSize.SC_CAPACITY.getSize();
    }

}
