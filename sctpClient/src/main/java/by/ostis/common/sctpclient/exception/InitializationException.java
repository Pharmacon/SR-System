package by.ostis.common.sctpclient.exception;

@SuppressWarnings("serial")
public class InitializationException extends Exception {

	public InitializationException(String message, Throwable cause) {
		super(message, cause);
	}

	public InitializationException(String message) {
		super(message);
	}

}
