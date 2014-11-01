package model.component;

import model.Content;
import constatnt.ComponentType;

public class Tile implements Component {

	private Text header;

	private Content tileContent;

	public Text getHeader() {
		return header;
	}

	public void setHeader(Text header) {
		this.header = header;
	}

	public Content getTileContent() {
		return tileContent;
	}

	public void setTileContent(Content content) {
		this.tileContent = content;
	}

	public String getComponentType() {
		return ComponentType.TILE.getValue();
	}

}
