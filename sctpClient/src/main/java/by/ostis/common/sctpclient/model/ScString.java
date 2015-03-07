package by.ostis.common.sctpclient.model;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ScString implements ScParameter {

    private String content;

    public ScString(String content) {

        this.content = content;
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {

        this.content = content;
    }

    @Override
    public byte[] getBytes() {

        ByteBuffer buffer = ByteBuffer.allocate(content.length());
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.put(content.getBytes());
        return buffer.array();
    }

    @Override
    public int getByteSize() {

        return content.getBytes().length;
    }

}
