package by.ostis.common.sctpclient.exception;

@SuppressWarnings("serial")
public class TransportException extends Exception {

    public TransportException(String message, Throwable cause) {

        super(message, cause);
    }

    public TransportException(String message) {

        super(message);
    }

}
