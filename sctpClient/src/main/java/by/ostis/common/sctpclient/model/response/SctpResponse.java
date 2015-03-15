package by.ostis.common.sctpclient.model.response;

public class SctpResponse<T> {

    private SctpResponseHeader header;

    private T answer;

    public SctpResponseHeader getHeader() {

        return header;
    }

    public void setHeader(SctpResponseHeader header) {

        this.header = header;
    }

    public T getAnswer() {

        return answer;
    }

    public void setAnswer(T answer) {

        this.answer = answer;
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + ((answer == null) ? 0 : answer.hashCode());
        result = prime * result + ((header == null) ? 0 : header.hashCode());
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
        SctpResponse<?> other = (SctpResponse<?>) obj;
        if (answer == null) {
            if (other.answer != null)
                return false;
        } else if (!answer.equals(other.answer))
            return false;
        if (header == null) {
            if (other.header != null)
                return false;
        } else if (!header.equals(other.header))
            return false;
        return true;
    }

}
