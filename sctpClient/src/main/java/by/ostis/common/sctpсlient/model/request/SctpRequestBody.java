package by.ostis.common.sctpсlient.model.request;

import java.util.List;

import by.ostis.common.sctpсlient.model.ScParameter;


public class SctpRequestBody {

	private List<ScParameter> body;
	
	public void addParameter(ScParameter parameter){
		body.add(parameter);
	}
	
	public void addParameters(ScParameter ...parameters){
		for (ScParameter scParameter : parameters) {
			body.add(scParameter);
		}
	}	
	
	public int getSize(){
		int size = 0;
		for (ScParameter scParameter : body) {
			size += scParameter.getSize();
		}
		return size;
	}
	
	
}
