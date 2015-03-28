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

        ByteBuffer buffer = ByteBuffer.allocate(content.getBytes().length);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.put(content.getBytes());
        return buffer.array();
    }

    @Override
    public int getByteSize() {

        return content.getBytes().length;
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ScString other = (ScString) obj;
        if (content == null) {
            if (other.content != null)
                return false;
        } else if (!content.equals(other.content))
            return false;
        return true;
    }

}
