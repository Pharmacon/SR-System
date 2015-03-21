package by.ostis.common.sctpclient.exception;

@SuppressWarnings("serial")
public class IllegalResultCodeException extends SctpClientException {

    public IllegalResultCodeException(Exception e) {

        super(e);
    }

    public IllegalResultCodeException(String message) {

        super(message);
    }

}
