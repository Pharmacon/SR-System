package by.ostis.common.sctpclient.transport;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import by.ostis.common.sctpclient.model.ScAddress;
import by.ostis.common.sctpclient.utils.constants.ScParameterSize;

class TypeBuilder {

    private static final int OFFSET_BEGIN_INDEX  = 2;

    private static final int SEGMENT_BEGIN_INDEX = 0;

    public static ScAddress buildScAddress(byte[] bytes) {

        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        short segment = buffer.getShort(SEGMENT_BEGIN_INDEX);
        short offset = buffer.getShort(OFFSET_BEGIN_INDEX);
        return new ScAddress(segment, offset);
    }

    public static ScAddress buildScAddress(byte[] bytes, int begin) {

        ByteBuffer buffer = ByteBuffer.wrap(bytes, begin,
                ScParameterSize.SC_ADDRESS.getSize());
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        short segment = buffer.getShort(SEGMENT_BEGIN_INDEX);
        short offset = buffer.getShort(OFFSET_BEGIN_INDEX);
        return new ScAddress(segment, offset);

    }
    
    public static ScAddress buildScAddress(InputStream source) throws IOException{
        byte[] scAddressBytes = new byte[4];
        source.read(scAddressBytes);
        ByteBuffer byteBuffer = ByteBuffer.wrap(scAddressBytes);
        short segment = byteBuffer.getShort(SEGMENT_BEGIN_INDEX);
        short offset = byteBuffer.getShort(OFFSET_BEGIN_INDEX);
        return new ScAddress(segment, offset);
    }

}
