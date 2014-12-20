package by.ostis.common.sctpclient.responsebuilder.commandsbuilders;

import java.util.ArrayList;
import java.util.List;

import by.ostis.common.sctpclient.model.ScContent;
import by.ostis.common.sctpclient.model.response.SctpResponseHeader;
import by.ostis.common.sctpclient.responsebuilder.ResponseBodyBuilder;


/**
 *  
 *
 */
public class LinkContentBuilder  implements ResponseBodyBuilder{

	@Override
	public List<Object> getBody(byte[] bytes, SctpResponseHeader responseHeader) {
		List<Object> list=new ArrayList<Object>();
		ScContent content=new ScContent();
		content.setBytes(bytes);
		list.add(content);
		return list;
	}

}
