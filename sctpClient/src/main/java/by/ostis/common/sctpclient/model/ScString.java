package by.ostis.common.sctpclient.model;

public class ScString implements ScParameter {

    private String content;

    public String getContent() {

        return content;
    }

    public void setContent(String content) {

        this.content = content;
    }

    @Override
    public byte[] getBytes() {

        return content.getBytes();
    }

    @Override
    public int getByteSize() {

        return content.getBytes().length;
    }

}
