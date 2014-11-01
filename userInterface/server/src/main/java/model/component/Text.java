package model.component;

import constatnt.ComponentType;

public class Text implements Component {

	private String innerText;

	public Text(String innerText) {
		super();
		this.innerText = innerText;
	}

	public String getInnerText() {
		return innerText;
	}

	public void setInnerText(String innerText) {
		this.innerText = innerText;
	}

	public String getComponentType() {
		return ComponentType.TEXT.getValue();
	}

}
