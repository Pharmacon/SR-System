package by.ostis.common.sctpclient.exception;

@SuppressWarnings("serial")
public class ShutdownException extends Exception{

	public ShutdownException(String message, Throwable cause) {
		super(message, cause);
	}

	public ShutdownException(String message) {
		super(message);
	}
	
	

}
