package by.ostis.common.sctpclient.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;


public class InputStreamReader {
    
    public static final int INTEGER_BYTE_SIZE = 4;

    public static int readInt(InputStream source) throws IOException {
        byte[] intBytes = new byte[INTEGER_BYTE_SIZE];
        source.read(intBytes);
        ByteBuffer tempBuffer = ByteBuffer.allocate(INTEGER_BYTE_SIZE);
        tempBuffer.order(ByteOrder.LITTLE_ENDIAN);
        tempBuffer.put(intBytes);
        return tempBuffer.getInt();
    }


}
