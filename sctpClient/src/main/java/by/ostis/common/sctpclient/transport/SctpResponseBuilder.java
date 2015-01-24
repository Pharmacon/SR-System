package by.ostis.common.sctpclient.transport;


import java.io.InputStream;

import by.ostis.common.sctpclient.exception.TransportException;
import by.ostis.common.sctpclient.model.response.SctpResponse;

interface SctpResponseBuilder {

	SctpResponse build(InputStream source) throws TransportException;

}
