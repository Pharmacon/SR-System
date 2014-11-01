package model.component;

import constatnt.ComponentType;

public class Panel implements Component {

	public String getComponentType() {
		return ComponentType.PANEL.getValue();
	}

}
