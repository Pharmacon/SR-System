/**
 * 
 */
package by.ostis.common.sctpclient.transport;

import by.ostis.common.sctpclient.exception.SctpClientException;

/**
 * @author Andrew Nepogoda Mar 21, 2015
 */
@SuppressWarnings("serial")
public class ElementNotFoundException extends SctpClientException {

    /**
     * 
     */
    public ElementNotFoundException(Exception e) {

        super(e);
    }

    /**
     * @param message
     */
    public ElementNotFoundException(String message) {

        super(message);
    }

}
