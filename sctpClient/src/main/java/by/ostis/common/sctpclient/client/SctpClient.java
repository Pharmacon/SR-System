package by.ostis.common.sctpclient.client;

import by.ostis.common.sctpclient.model.ScAddress;
import by.ostis.common.sctpclient.model.ScString;
import by.ostis.common.sctpclient.model.response.SctpResponse;
import by.ostis.common.sctpclient.utils.constants.ScArcType;
import by.ostis.common.sctpclient.utils.constants.ScElementType;

public interface SctpClient {

	public void init(String host, int port);

	public void shutdown();

	public ScString getScLinkContent(ScAddress address);

	public SctpResponse checkElementExistence(ScAddress address);

	public SctpResponse searchElement(ScString identifier);

	public SctpResponse deleteElement(ScAddress address);

	public SctpResponse createElement(ScElementType type);

	public SctpResponse createScLink();

	public SctpResponse createScArc(ScArcType type, ScAddress begAddress,
			ScAddress endAddress);

	public SctpResponse getArcBeginAndEnd(ScAddress arcAddress);

	public SctpResponse searchScLinks(ScString content);

	public SctpResponse setScRefContent(ScAddress address, ScString content);

	public SctpResponse setSystemIdentifier(ScAddress address,
			ScString identifier);

}
